package ir.alishayanpoor.hangmancalculator.data.repo

import ir.alishayanpoor.hangmancalculator.domain.repo.HangmanRepo
import ir.alishayanpoor.hangmancalculator.exception.AppException
import ir.alishayanpoor.hangmancalculator.ui.view.hangman.HangmanState
import ir.alishayanpoor.hangmancalculator.utils.exhaustive

class HangmanRepoLocal : HangmanRepo {
    @Throws(AppException::class)
    override fun nextState(currentState: HangmanState): HangmanState {
        return when (currentState) {
            HangmanState.S1Init -> HangmanState.S2Rope
            HangmanState.S2Rope -> HangmanState.S3Head
            HangmanState.S3Head -> HangmanState.S4Body
            HangmanState.S4Body -> HangmanState.S5RightHand
            HangmanState.S5RightHand -> HangmanState.S6LeftHand
            HangmanState.S6LeftHand -> HangmanState.S7RightFoot
            HangmanState.S7RightFoot -> HangmanState.S8End
            HangmanState.S8End -> throw AppException("Cannot go any further")
        }.exhaustive
    }

    override fun getStateImageResourceByCurrentState(currentState: HangmanState): HangmanState {
        TODO("Not yet implemented")
    }
}