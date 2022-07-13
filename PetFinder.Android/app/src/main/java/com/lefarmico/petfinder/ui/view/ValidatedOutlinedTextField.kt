package com.lefarmico.petfinder.ui.view

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import com.lefarmico.petfinder.ui.view.validator.TextValidationHandler
import com.lefarmico.petfinder.ui.view.validator.ValidatorController

@Composable
fun ValidatedOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: String = "",
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
    validators: (ValidatorController.() -> Unit)? = null
) {
    val textValidatorHandler = TextValidationHandler()
    if (validators != null) {
        validators(textValidatorHandler)
    }

    val isErrorState = remember { mutableStateOf(false) }
    val labelState = remember { mutableStateOf(label) }
    OutlinedTextField(
        value = value,
        onValueChange = {
            val response = textValidatorHandler.validate(it)
            if (response != null) {
                labelState.value = response.response
                isErrorState.value = true
            } else {
                labelState.value = label
                isErrorState.value = false
            }
            onValueChange(it)
        },
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        label = {
            FadedText(text = labelState.value)
        },
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isError = isErrorState.value,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        interactionSource = interactionSource,
        colors = colors
    )
}
