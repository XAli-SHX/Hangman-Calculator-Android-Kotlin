package ir.alishayanpoor.hangmancalculator.ui.view.hangman

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ir.alishayanpoor.hangmancalculator.utils.Navigator
import ir.alishayanpoor.hangmancalculator.utils.exhaustive
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HangmanActivity : ComponentActivity() {
    companion object {
        const val NAV_KEY_CALC_RESULT = "calc_result"
    }

    private val viewModel by viewModels<HangmanViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.calcResult = Navigator.getNavigationStringData(this, NAV_KEY_CALC_RESULT)
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
                    is HangmanUiEvent.Win -> win()
                }.exhaustive
            }
        }
    }

    private fun win() {
        Toast.makeText(this@HangmanActivity,
            "You have earned the calculation result!",
            Toast.LENGTH_LONG).show()
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
        Column(Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painterResource(id = viewModel.getResourceByState()),
                viewModel.getHangmanContentDescriptionByState())
            Text(text = "The answer is the calculation. Good luck!")
//            NextStateButton()
            NumbersToFind()
            NumbersGrid()
        }
    }

    @Composable
    private fun NextStateButton() {
        Button(onClick = { viewModel.nextState() }) {
            Text(text = "Next state")
        }
    }

    @Composable
    private fun NumbersToFind() {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            for (i in 0 until viewModel.calcResult.length) {
                val text = if (viewModel.state.foundedIndexes.any { it == i })
                    " ${viewModel.calcResult[i]} "
                else
                    " _ "
                Text(text = text)
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun NumbersGrid() {
        LazyVerticalGrid(cells = GridCells.Adaptive(64.dp)) {
            items(viewModel.state.toSelectNumbers.size) { index ->
                Button(modifier = Modifier.padding(5.dp),
                    onClick = { viewModel.onNumberClicked(viewModel.state.toSelectNumbers[index]) }) {
                    Text(text = viewModel.state.toSelectNumbers[index])
                }
            }
        }
    }

    override fun onBackPressed() {
        if (!viewModel.state.lockBackButton)
            super.onBackPressed()
        else
            Toast.makeText(this, "You cannot scape!", Toast.LENGTH_SHORT).show()
    }
}