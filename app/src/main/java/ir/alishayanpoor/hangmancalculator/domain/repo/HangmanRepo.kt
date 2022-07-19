package ir.alishayanpoor.hangmancalculator.domain.repo

import ir.alishayanpoor.hangmancalculator.ui.view.hangman.HangmanState

interface HangmanRepo {
    fun nextState(currentState: HangmanState): HangmanState
    fun getStateImageResourceByCurrentState(currentState: HangmanState): HangmanState
}