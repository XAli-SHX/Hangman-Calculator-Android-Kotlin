package ir.alishayanpoor.hangmancalculator.ui.view.hangman

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ir.alishayanpoor.hangmancalculator.utils.exhaustive
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HangmanActivity : ComponentActivity() {
    private val viewModel by viewModels<HangmanViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
        setContent {
            HangmanScreen()
        }
    }

    @Suppress("IMPLICIT_CAST_TO_ANY")
    private fun subscribeObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.event.receiveAsFlow().collectLatest {
                when (it) {
                    is HangmanUiEvent.Error -> showError(it)
                    is HangmanUiEvent.GameOver -> gameOver()
                }.exhaustive
            }
        }
    }

    private fun gameOver() {
        Toast.makeText(this@HangmanActivity,
            "You missed the calculation!",
            Toast.LENGTH_SHORT).show()
        Toast.makeText(this@HangmanActivity,
            "BYE!",
            Toast.LENGTH_SHORT).show()
        lifecycleScope.launch {
            delay(5000)
            finishAffinity()
        }
    }

    private fun showError(it: HangmanUiEvent.Error) {
        Toast.makeText(this@HangmanActivity,
            it.message,
            Toast.LENGTH_SHORT).show()
    }

    @Composable
    private fun HangmanScreen() {
        Column(Modifier.fillMaxSize()) {
            Image(painterResource(id = viewModel.getResourceByState()),
                "state1 - Initialize of hangman")
            Button(onClick = { viewModel.nextState() }) {
                Text(text = "Next state")
            }
        }
    }
}