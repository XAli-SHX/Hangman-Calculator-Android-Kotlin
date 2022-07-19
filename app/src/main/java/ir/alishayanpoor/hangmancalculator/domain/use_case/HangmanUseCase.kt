package ir.alishayanpoor.hangmancalculator.domain.use_case

import ir.alishayanpoor.hangmancalculator.domain.repo.HangmanRepo
import ir.alishayanpoor.hangmancalculator.exception.AppException
import ir.alishayanpoor.hangmancalculator.ui.view.hangman.HangmanState
import javax.inject.Inject

class HangmanUseCase @Inject constructor(
    private val repo: HangmanRepo,
) {
    fun nextState(currentState: HangmanState): HangmanState {
        return repo.nextState(currentState)
    }

    fun getStateImageResourceByCurrentState(currentState: HangmanState): Int {
        return repo.getStateImageResourceByCurrentState(currentState)
    }

    fun getStateImageDescriptionByCurrentState(currentState: HangmanState): String {
        return repo.getStateImageDescriptionByCurrentState(currentState)
    }

    fun checkEnteredNumber(calcResult: String, number: String): List<Int> {
        var hasBeenFound = false
        val indexList = mutableListOf<Int>()
        calcResult.forEachIndexed { index, c ->
            if (c.toString() == number) {
                hasBeenFound = true
                indexList.add(index)
            }
        }
        if (!hasBeenFound)
            throw AppException("No number found in calculation")
        return indexList.toList()
    }
}