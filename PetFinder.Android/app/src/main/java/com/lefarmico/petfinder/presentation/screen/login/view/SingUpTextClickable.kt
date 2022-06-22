package com.lefarmico.petfinder.presentation.screen.login.view

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.lefarmico.petfinder.R

@Preview(group = "sign_up_and_password", showBackground = true)
@Composable
fun SignUpTextClickable(
    modifier: Modifier = Modifier,
    onSignUpClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {}
) {
    val primaryColorId = MaterialTheme.colors.primary
    val signUpString = stringResource(R.string.sign_up_text_button)
    val orString = stringResource(R.string.or_text)
    val forgotPasswordString = stringResource(R.string.forgot_password_text_button)

    val textWithLinks = buildAnnotatedString {
        pushStringAnnotation("sign_in", signUpString)
        withStyle(SpanStyle(color = primaryColorId)) {
            append(signUpString)
        }
        pop()
//      ----------------------------
        append(" $orString ")
//      ----------------------------
        pushStringAnnotation("forgot_password", forgotPasswordString)
        withStyle(SpanStyle(color = primaryColorId)) {
            append(forgotPasswordString)
        }
        pop()
    }
    ClickableText(
        modifier = modifier,
        text = textWithLinks,
        onClick = { offset ->
            textWithLinks.getStringAnnotations(
                tag = "sign_in",
                start = offset,
                end = offset
            ).firstOrNull()?.let {
                onSignUpClick()
            }
            textWithLinks.getStringAnnotations(
                tag = "forgot_password",
                start = offset,
                end = offset
            ).firstOrNull()?.let {
                onForgotPasswordClick()
            }
        }
    )
}
