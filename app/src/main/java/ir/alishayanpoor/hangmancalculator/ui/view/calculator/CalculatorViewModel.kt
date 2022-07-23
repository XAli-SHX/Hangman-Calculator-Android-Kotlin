package ir.alishayanpoor.hangmancalculator.ui.view.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.alishayanpoor.hangmancalculator.domain.use_case.ArithmeticUseCase
import ir.alishayanpoor.hangmancalculator.exception.AppException
import ir.alishayanpoor.hangmancalculator.utils.Calculator
import ir.alishayanpoor.hangmancalculator.utils.Constants
import ir.alishayanpoor.hangmancalculator.utils.thousandSeparate
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
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
                state.rawText.replace(Constants.CHAR_THOUSAND_SEPARATOR, ""),
                value,
                ARITHMETIC_MAX_CHAR_SIZE
            )
            state = state.copy(
                rawText = thousandSeparatorWithOperation(newText)
            )
        } catch (e: AppException) {
            when (e) {
                is ArithmeticUseCase.FirstCharOperationException -> {

                }
                else -> {
                    viewModelScope.launch {
                        event.send(CalculatorUiEvent.Error(e.localizedMessage))
                    }
                }
            }
        }
    }

    private fun thousandSeparatorWithOperation(rawText: String): String {
        val op = Calculator.getArithmeticList().firstOrNull { rawText.indexOf(it) != -1 }
            ?: return rawText.thousandSeparate()
        val numbersData = rawText.split(op)
        return buildString {
            append(numbersData[0].thousandSeparate())
            append(op)
            if (numbersData.size == 2)
                append(numbersData[1].thousandSeparate())
        }
    }

    fun calculate() {
        viewModelScope.launch {
            try {
                val res = arithmeticUseCase.calculateResult(
                    state.rawText.replace(Constants.CHAR_THOUSAND_SEPARATOR, "")
                )
                event.send(CalculatorUiEvent.StartHangman(res))
                delay(1000)
                state = state.copy(
                    rawText = res.thousandSeparate()
                )
            } catch (e: AppException) {
                when (e) {
                    is Calculator.NullOperationException -> {}
                    else ->
                        event.send(CalculatorUiEvent.Error(e.localizedMessage))
                }
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