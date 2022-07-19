package ir.alishayanpoor.hangmancalculator.data.repo

import ir.alishayanpoor.hangmancalculator.R
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

    override fun getStateImageResourceByCurrentState(currentState: HangmanState): Int {
        return when (currentState) {
            HangmanState.S1Init -> R.drawable.s1_init
            HangmanState.S2Rope -> R.drawable.s2_rope
            HangmanState.S3Head -> R.drawable.s3_head
            HangmanState.S4Body -> R.drawable.s4_body
            HangmanState.S5RightHand -> R.drawable.s5_right_hand
            HangmanState.S6LeftHand -> R.drawable.s6_left_hand
            HangmanState.S7RightFoot -> R.drawable.s7_right_foot
            HangmanState.S8End -> R.drawable.s8_end
        }
    }

    override fun getStateImageDescriptionByCurrentState(currentState: HangmanState): String {
        return when (currentState) {
            HangmanState.S1Init -> "State 1: Hangman init"
            HangmanState.S2Rope -> "State 2: Hangman rope"
            HangmanState.S3Head -> "State 3: Hangman head"
            HangmanState.S4Body -> "State 4: Hangman body"
            HangmanState.S5RightHand -> "State 5: Hangman right hand"
            HangmanState.S6LeftHand -> "State 6: Hangman left hand"
            HangmanState.S7RightFoot -> "State 7: Hangman right foot"
            HangmanState.S8End -> "State 8: Hangman left foot and, theEnd"
        }
    }
}