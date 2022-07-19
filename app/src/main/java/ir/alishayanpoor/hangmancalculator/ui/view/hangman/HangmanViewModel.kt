package ir.alishayanpoor.hangmancalculator.ui.view.hangman

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.alishayanpoor.hangmancalculator.domain.repo.HangmanRepo
import ir.alishayanpoor.hangmancalculator.domain.use_case.HangmanUseCase
import ir.alishayanpoor.hangmancalculator.exception.AppException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HangmanViewModel @Inject constructor(
    private val hangmanUseCase: HangmanUseCase,
    private val repo: HangmanRepo,
) : ViewModel() {
    var state by mutableStateOf(HangmanUiState())
        private set
    val event = Channel<HangmanUiEvent>()

    fun nextState() {
        try {
            state = state.copy(
                currentState = repo.nextState(state.currentState)
            )
            if (state.currentState == HangmanState.S8End)
                viewModelScope.launch {
                    event.send(HangmanUiEvent.GameOver)
                }
        } catch (e: AppException) {
            viewModelScope.launch { event.send(HangmanUiEvent.Error(e.localizedMessage)) }
        }
    }

    fun getResourceByState() = repo.getStateImageResourceByCurrentState(state.currentState)

}