package ir.alishayanpoor.hangmancalculator.ui.view.hangman

data class HangmanUiState(
    val currentState: HangmanState = HangmanState.S1Init,
    val toSelectNumbers: List<String> = listOf(
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"
    ),
    val foundedIndexes: List<Int> = emptyList(),
    val lockBackButton: Boolean = true,
    val gameInProcess: Boolean = true,
    val isWinner: Boolean = false,
)

enum class HangmanState {
    S1Init, S2Rope, S3Head, S4Body, S5RightHand, S6LeftHand, S7RightFoot, S8End
}