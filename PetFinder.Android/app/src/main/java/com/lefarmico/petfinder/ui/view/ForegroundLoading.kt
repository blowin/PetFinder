package com.lefarmico.petfinder.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * @param loaderView - Compose view which is used for showing progress bar
 */
@Composable
fun ForegroundLoading(
    modifier: Modifier = Modifier,
    loaderView: @Composable () -> Unit = { CircularProgressIndicator() }
) {
    /**
     * Must be top layer of the screen.
     */
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White.copy(alpha = 0.5f))
            .clickable(
                enabled = false,
                onClick = {}
            ),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
