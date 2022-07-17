package com.lefarmico.petfinder.ui.theme.petfinder

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PF_OutlinedCard(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke = CardDefaults.outlinedCardBorder(true),
    elevation: CardElevation = CardDefaults.outlinedCardElevation(),
    colors: CardColors = CardDefaults.outlinedCardColors(),
    content: @Composable ColumnScope.() -> Unit
) = Card(
    modifier = modifier,
    shape = shape,
    border = border,
    elevation = elevation,
    colors = colors,
    content = content
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PF_Card(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    border: BorderStroke? = null,
    elevation: CardElevation = CardDefaults.cardElevation(),
    colors: CardColors = CardDefaults.cardColors(),
    content: @Composable ColumnScope.() -> Unit
) = Card(
    modifier = modifier,
    shape = shape,
    border = border,
    elevation = elevation,
    colors = colors,
    content = content
)
