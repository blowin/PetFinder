package com.lefarmico.petfinder.presentation.screen.login.view

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.lefarmico.petfinder.R

@Composable
fun UsernameTextField(
    userName: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    onActionClick: () -> Unit = {}
) {
    OutlinedTextField(
        modifier = modifier,
        value = userName,
        onValueChange = { onValueChange(it) },
        label = { Text(text = stringResource(id = R.string.username)) },
        placeholder = { Text(text = stringResource(id = R.string.username_field)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Face,
                contentDescription = stringResource(id = R.string.username)
            )
        },
        singleLine = true,
        keyboardActions = KeyboardActions(onAny = { onActionClick() }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        )
    )
}