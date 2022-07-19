package ir.alishayanpoor.hangmancalculator.data.repo

import ir.alishayanpoor.hangmancalculator.domain.repo.HangmanRepo
import ir.alishayanpoor.hangmancalculator.ui.view.hangman.HangmanState

class HangmanRepoLocal : HangmanRepo {
    override fun nextState(currentState: HangmanState): HangmanState {
        TODO("Not yet implemented")
    }

    override fun getStateImageResourceByCurrentState(currentState: HangmanState): HangmanState {
        TODO("Not yet implemented")
    }
}