package com.mpg.presentation.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp


fun Modifier.bottomBorder(thickness: Dp, color: Color) = drawBehind {
    val strokeWidthPx = thickness.toPx()
    val y = size.height - strokeWidthPx / 2
    drawLine(
        color = color,
        start = Offset(0f, y),
        end = Offset(size.width, y),
        strokeWidth = strokeWidthPx
    )
}
