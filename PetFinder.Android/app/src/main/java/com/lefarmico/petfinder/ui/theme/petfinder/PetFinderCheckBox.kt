package com.lefarmico.petfinder.ui.theme.petfinder

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lefarmico.petfinder.ui.view.TextWithCheckBox

@Composable
fun PF_TextWithCheckBox(
    modifier: Modifier = Modifier,
    text: String = "",
    isChecked: Boolean = false,
    onCheckChange: (Boolean) -> Unit = {},
    isEnabled: Boolean = true
) {
    TextWithCheckBox(
        modifier = modifier,
        text = text,
        isChecked = isChecked,
        onCheckChange = onCheckChange,
        isEnabled = isEnabled
    )
}
