package ir.alishayanpoor.hangmancalculator.ui.view

sealed class CalculatorUiEvent {
    object StartHangman : CalculatorUiEvent()
    data class Error(val message: String) : CalculatorUiEvent()
}