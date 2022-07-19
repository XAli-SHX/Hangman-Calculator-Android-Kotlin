package ir.alishayanpoor.hangmancalculator.ui.view

sealed class CalculatorUiEvent {
    data class StartHangman(val result: String) : CalculatorUiEvent()
    data class Error(val message: String) : CalculatorUiEvent()
}