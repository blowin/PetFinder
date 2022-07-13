package com.lefarmico.petfinder.ui.view

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextWithCheckBox(
    modifier: Modifier = Modifier,
    text: String = "",
    isChecked: Boolean = false,
    onCheckChange: (Boolean) -> Unit = {},
    isEnabled: Boolean = true
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { onCheckChange(it) },
            enabled = isEnabled
        )
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun ShowPasswordCheckBoxDemo() {
    TextWithCheckBox(text = "show password")
}
