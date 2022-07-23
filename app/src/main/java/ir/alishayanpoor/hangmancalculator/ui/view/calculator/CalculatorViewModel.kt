package ir.alishayanpoor.hangmancalculator.ui.view.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.alishayanpoor.hangmancalculator.domain.use_case.ArithmeticUseCase
import ir.alishayanpoor.hangmancalculator.exception.AppException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val arithmeticUseCase: ArithmeticUseCase,
) : ViewModel() {

    companion object {
        const val ARITHMETIC_MAX_CHAR_SIZE = 20
    }

    var state by mutableStateOf(CalculatorUiState())
        private set

    val event = Channel<CalculatorUiEvent>()

    fun onCalculatorButtonClicked(value: String) {
        try {
            val newText = arithmeticUseCase.enterNewChar(
                state.rawText,
                value,
                ARITHMETIC_MAX_CHAR_SIZE)
            state = state.copy(
                rawText = newText
            )
        } catch (e: AppException) {
            viewModelScope.launch {
                event.send(CalculatorUiEvent.Error(e.localizedMessage))
            }
        }
    }

    fun calculate() {
        viewModelScope.launch {
            try {
                val res = arithmeticUseCase.calculateResult(state.rawText)
                event.send(CalculatorUiEvent.StartHangman(res))
            } catch (e: AppException) {
                event.send(CalculatorUiEvent.Error(e.localizedMessage))
            }
        }
    }

    fun deleteChar() {
        try {
            state = state.copy(
                rawText = arithmeticUseCase.delete(state.rawText)
            )
        } catch (e: AppException) {
            /*viewModelScope.launch {
                event.send(CalculatorUiEvent.Error(e.localizedMessage))
            }*/
        }
    }
}