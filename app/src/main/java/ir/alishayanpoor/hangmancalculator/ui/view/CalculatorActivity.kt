package ir.alishayanpoor.hangmancalculator.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ir.alishayanpoor.hangmancalculator.ui.components.CalculatorButton
import ir.alishayanpoor.hangmancalculator.utils.Constants
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow

@AndroidEntryPoint
class CalculatorActivity : ComponentActivity() {
    private val viewModel by viewModels<CalculatorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            subscribeObservers()
            CalculatorScreen()
        }
    }

    private fun subscribeObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.event.receiveAsFlow().collectLatest {
                when (it) {
                    is CalculatorUiEvent.Error -> {
                        Toast.makeText(this@CalculatorActivity, it.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                    is CalculatorUiEvent.StartHangman -> {
                        Toast.makeText(this@CalculatorActivity, it.result, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    @Composable
    fun CalculatorScreen() {
        Column(Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(12.dp)
        ) {
            Box(Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .weight(1f)
                .padding(12.dp),
                contentAlignment = Alignment.BottomEnd) {
                Text(
                    text = viewModel.state.rawText,
                    maxLines = 1,
                    fontSize = 20.sp
                )
            }
            // 7, 8, 9, /
            Row(Modifier
                .fillMaxWidth()
                .weight(1f)) {
                CalculatorButton(modifier = Modifier.weight(1f),
                    text = Constants.CALC_BUTTON_7,
                    onClick = { viewModel.onCalculatorButtonClicked(Constants.CALC_BUTTON_7) }
                )
                CalculatorButton(modifier = Modifier.weight(1f),
                    text = Constants.CALC_BUTTON_8,
                    onClick = { viewModel.onCalculatorButtonClicked(Constants.CALC_BUTTON_8) }
                )
                CalculatorButton(modifier = Modifier.weight(1f),
                    text = Constants.CALC_BUTTON_9,
                    onClick = { viewModel.onCalculatorButtonClicked(Constants.CALC_BUTTON_9) }
                )
                CalculatorButton(modifier = Modifier.weight(1f),
                    text = Constants.CALC_BUTTON_DIV,
                    onClick = { viewModel.onCalculatorButtonClicked(Constants.CALC_BUTTON_DIV) }
                )
            }
            // 4, 5, 6, *
            Row(Modifier
                .fillMaxWidth()
                .weight(1f)) {
                CalculatorButton(modifier = Modifier.weight(1f),
                    text = Constants.CALC_BUTTON_4,
                    onClick = { viewModel.onCalculatorButtonClicked(Constants.CALC_BUTTON_4) }
                )
                CalculatorButton(modifier = Modifier.weight(1f),
                    text = Constants.CALC_BUTTON_5,
                    onClick = { viewModel.onCalculatorButtonClicked(Constants.CALC_BUTTON_5) }
                )
                CalculatorButton(modifier = Modifier.weight(1f),
                    text = Constants.CALC_BUTTON_6,
                    onClick = { viewModel.onCalculatorButtonClicked(Constants.CALC_BUTTON_6) }
                )
                CalculatorButton(modifier = Modifier.weight(1f),
                    text = Constants.CALC_BUTTON_MUL,
                    onClick = { viewModel.onCalculatorButtonClicked(Constants.CALC_BUTTON_MUL) }
                )
            }
            // 1, 2, 3, -
            Row(Modifier
                .fillMaxWidth()
                .weight(1f)) {
                CalculatorButton(modifier = Modifier.weight(1f),
                    text = Constants.CALC_BUTTON_1,
                    onClick = { viewModel.onCalculatorButtonClicked(Constants.CALC_BUTTON_1) }
                )
                CalculatorButton(modifier = Modifier.weight(1f),
                    text = Constants.CALC_BUTTON_2,
                    onClick = { viewModel.onCalculatorButtonClicked(Constants.CALC_BUTTON_2) }
                )
                CalculatorButton(modifier = Modifier.weight(1f),
                    text = Constants.CALC_BUTTON_3,
                    onClick = { viewModel.onCalculatorButtonClicked(Constants.CALC_BUTTON_3) }
                )
                CalculatorButton(modifier = Modifier.weight(1f),
                    text = Constants.CALC_BUTTON_SUB,
                    onClick = { viewModel.onCalculatorButtonClicked(Constants.CALC_BUTTON_SUB) }
                )
            }
            // 0, =, +
            Row(Modifier
                .fillMaxWidth()
                .weight(1f), horizontalArrangement = Arrangement.End) {
                CalculatorButton(modifier = Modifier.weight(1f),
                    text = Constants.CALC_BUTTON_0,
                    onClick = { viewModel.onCalculatorButtonClicked(Constants.CALC_BUTTON_0) }
                )
                CalculatorButton(modifier = Modifier.weight(1f),
                    text = Constants.CALC_BUTTON_CALC,
                    onClick = { viewModel.calculate() }
                )
                CalculatorButton(modifier = Modifier.weight(1f),
                    text = Constants.CALC_BUTTON_SUM,
                    onClick = { viewModel.onCalculatorButtonClicked(Constants.CALC_BUTTON_SUM) }
                )
                CalculatorButton(
                    modifier = Modifier.weight(1f),
                    text = Constants.CALC_BUTTON_DEL,
                    onClick = { viewModel.deleteChar() },
                )
            }
        }
    }
}