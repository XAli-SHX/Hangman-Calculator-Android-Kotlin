package ir.alishayanpoor.hangmancalculator.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CalculatorButtons(
    modifier: Modifier,
    onClick: () -> Unit,
    text: String = "",
    contentColor: Color = Color.Blue,
    borderStroke: Dp = 1.dp,
    borderColor: Color = Color.Blue,
    content: @Composable (RowScope.() -> Unit)? = null,
) {
    content?.let {
        OutlinedButton(onClick = onClick,
            modifier = modifier.size(50.dp),
            shape = CircleShape,
            border = BorderStroke(borderStroke, borderColor),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = contentColor),
            content = content
        )
    } ?: run {
        OutlinedButton(onClick = onClick,
            modifier = modifier.size(50.dp),
            shape = CircleShape,
            border = BorderStroke(borderStroke, borderColor),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = contentColor)
        ) {
            Text(text = text)
        }
    }
}
