package com.lefarmico.petfinder.ui.theme.petfinder

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.lefarmico.petfinder.R
import com.lefarmico.petfinder.ui.view.ValidatedOutlinedTextField
import com.lefarmico.petfinder.ui.view.validator.ValidatorController

@Composable
fun PF_TextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    enabled: Boolean = true,
    label: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    onActionClick: () -> Unit = {},
    validators: (ValidatorController.() -> Unit)? = null
) {
    ValidatedOutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        label = label,
        singleLine = true,
        leadingIcon = leadingIcon,
        validators = validators,
        keyboardActions = KeyboardActions(onAny = { onActionClick() })
    )
}

@Composable
fun PF_PasswordTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    enabled: Boolean = true,
    showPassword: Boolean = false,
    label: String = "",
    leadingIcon: @Composable (() -> Unit)? = {
        Icon(
            imageVector = Icons.Filled.Lock,
            contentDescription = stringResource(id = R.string.password)
        )
    },
    onActionClick: () -> Unit = {},
    validators: (ValidatorController.() -> Unit)? = null,
) {
    val passwordHider = if (!showPassword) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    ValidatedOutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        label = label,
        singleLine = true,
        leadingIcon = leadingIcon,
        validators = validators,
        visualTransformation = passwordHider,
        keyboardActions = KeyboardActions(onAny = { onActionClick() })
    )
}
