package ir.alishayanpoor.hangmancalculator.domain.use_case

import ir.alishayanpoor.hangmancalculator.exception.AppException
import ir.alishayanpoor.hangmancalculator.ui.view.hangman.HangmanState

class HangmanUseCase {

    @Throws(AppException::class)
    fun nextState(currentState: HangmanState): HangmanState {
        return when (currentState) {
            HangmanState.S1Init -> HangmanState.S2Rope
            HangmanState.S2Rope -> HangmanState.S3Head
            HangmanState.S3Head -> HangmanState.S4Body
            HangmanState.S4Body -> HangmanState.S5RightHand
            HangmanState.S5RightHand -> HangmanState.S6LeftHand
            HangmanState.S6LeftHand -> HangmanState.S7RightFoot
            HangmanState.S7RightFoot -> HangmanState.S8End
            HangmanState.S8End -> throw AppException("Cannot go any further")
        }
    }
}