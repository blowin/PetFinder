package com.lefarmico.petfinder.ui.view

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
inline fun ShowToast(message: String, onHideCallback: () -> Unit = {}) {
    Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
    onHideCallback()
}
