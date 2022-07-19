package ir.alishayanpoor.hangmancalculator.ui.view.hangman

sealed class HangmanUiEvent {
    data class Error(val message: String) : HangmanUiEvent()
    object GameOver : HangmanUiEvent()
    object Win : HangmanUiEvent()
}