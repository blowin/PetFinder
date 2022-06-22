package com.lefarmico.petfinder.presentation.screen.login.view

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.lefarmico.petfinder.R

@Composable
fun ShowPasswordCheckBox(
    modifier: Modifier = Modifier,
    isChecked: Boolean = false,
    onCheckChange: (Boolean) -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = isChecked, onCheckedChange = { onCheckChange(it) })
        Text(text = stringResource(id = R.string.show_password))
    }
}
