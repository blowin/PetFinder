package com.lefarmico.petfinder.presentation.screen.login.view

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.lefarmico.petfinder.R

@Composable
fun PasswordTextField(
    password: String,
    isPasswordShown: Boolean,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    onActionClick: () -> Unit = {}
) {
    val passwordHider = if (!isPasswordShown) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }
    OutlinedTextField(
        modifier = modifier,
        value = password,
        onValueChange = { onValueChange(it) },
        label = { Text(text = stringResource(id = R.string.password)) },
        placeholder = { Text(text = stringResource(id = R.string.password_field)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = stringResource(id = R.string.password)
            )
        },
        singleLine = true,
        keyboardActions = KeyboardActions(onAny = { onActionClick() }),
        visualTransformation = passwordHider,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        )
    )
}