package com.lefarmico.petfinder.unitConverterApp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lefarmico.petfinder.R

val AndroidGreen = Color(0xFF3DDC84)
val AndroidGreenDark = Color(0xFF20B261)
val Orange = Color(0xFFFFA500)
val OrangeDark = Color(0xFFCC8400)

val DarkColorPalette = darkColors(
    primary = AndroidGreenDark,
    primaryVariant = AndroidGreenDark,
    secondary = OrangeDark,
    secondaryVariant = OrangeDark
)
val LightColorPalette = lightColors(
    primary = AndroidGreen,
    primaryVariant = AndroidGreen,
    secondary = Orange,
    secondaryVariant = Orange
)
val CutShapes = Shapes(small = CutCornerShape(8.dp))
val Typography = Typography(button = TextStyle(fontSize = 24.sp))

@Composable
fun ComposeUnitConverterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette.copy(secondary = colorResource(id = R.color.orange_dark))
    }
    MaterialTheme(
        colors = colors,
        content = content,
        shapes = CutShapes,
        typography = Typography
    )
}
