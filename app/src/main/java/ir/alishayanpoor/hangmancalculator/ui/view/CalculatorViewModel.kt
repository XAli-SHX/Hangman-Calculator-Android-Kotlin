package ir.alishayanpoor.hangmancalculator.ui.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.alishayanpoor.hangmancalculator.domain.use_case.ArithmeticUseCase
import ir.alishayanpoor.hangmancalculator.exception.AppException
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

    fun onCalculatorButtonClicked(value: String) {
        try {
            state = state.copy(
                rawText = arithmeticUseCase.enterNewChar(
                    state.rawText,
                    value,
                    ARITHMETIC_MAX_CHAR_SIZE)
            )
        } catch (e: AppException) {
        }
    }

    fun calculate() {
        val res = arithmeticUseCase.calculateResult(state.rawText)
        TODO("go to next activity")
    }

    fun deleteChar() {
        try {
            state = state.copy(
                rawText = arithmeticUseCase.delete(state.rawText)
            )
        } catch (e: AppException) {
        }
    }

}