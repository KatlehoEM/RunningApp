package com.example.a2020102527_main_project_2.ui.utils
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

/*
    The bottomBorder extension function for Modifier draws a bottom border with specified width and color.
     It utilizes the drawBehind composable to draw a line at the bottom of the composable's bounds.

    The conditional extension function applies a modifier conditionally based on a boolean condition,
    allowing for flexible modification of composables.
    If the condition is true, it applies the ifTrue modifier; otherwise, it applies the ifFalse modifier.
 */
fun Modifier.bottomBorder(strokeWidth: Dp, color: Color) = drawBehind {
    val width = size.width
    val height = size.height

    drawLine(
        color = color,
        start = Offset(x = 0f, y = height),
        end = Offset(x = width, y = height),
        strokeWidth = strokeWidth.toPx()
    )
}

fun Modifier.conditional(
    condition: Boolean,
    ifFalse: () -> Modifier = { Modifier },
    ifTrue: () -> Modifier
): Modifier = run {
    if (condition) then(ifTrue())
    else then(ifFalse())
}
