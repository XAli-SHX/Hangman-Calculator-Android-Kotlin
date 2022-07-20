package ir.alishayanpoor.hangmancalculator.ui.view.hangman

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.alishayanpoor.hangmancalculator.domain.use_case.HangmanUseCase
import ir.alishayanpoor.hangmancalculator.exception.AppException
import ir.alishayanpoor.hangmancalculator.utils.remove
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HangmanViewModel @Inject constructor(
    private val hangmanUseCase: HangmanUseCase,
) : ViewModel() {
    var state by mutableStateOf(HangmanUiState())
        private set
    val event = Channel<HangmanUiEvent>()

    lateinit var calcResult: String

    fun nextState() {
        try {
            state = state.copy(
                currentState = hangmanUseCase.nextState(state.currentState)
            )
            if (state.currentState == HangmanState.S8End)
                viewModelScope.launch {
                    event.send(HangmanUiEvent.GameOver)
                }
        } catch (e: AppException) {
            viewModelScope.launch { event.send(HangmanUiEvent.Error(e.localizedMessage)) }
        }
    }

    fun getResourceByState() =
        hangmanUseCase.getStateImageResourceByCurrentState(state.currentState)

    fun getHangmanContentDescriptionByState() =
        hangmanUseCase.getStateImageDescriptionByCurrentState(state.currentState)

    @Throws(AppException::class)
    fun onNumberClicked(number: String) {
        try {
            state = state.copy(
                toSelectNumbers = state.toSelectNumbers.remove(number),
            )
            val indexes = hangmanUseCase.checkEnteredNumber(calcResult, number)
            val newFoundIndexes = state.foundedIndexes.toMutableList()
            indexes.forEach {
                newFoundIndexes.add(it)
            }
            state = state.copy(
                foundedIndexes = newFoundIndexes
            )
            if (newFoundIndexes.size == calcResult.length)
                viewModelScope.launch {
                    event.send(HangmanUiEvent.Win)
                    state = state.copy(
                        lockBackButton = false
                    )
                }
        } catch (e: AppException) {
            nextState()
        }
    }
}