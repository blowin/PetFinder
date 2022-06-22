package com.lefarmico.petfinder.presentation.screen.login.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lefarmico.petfinder.R
import com.lefarmico.petfinder.presentation.screen.login.LoginViewModel
import com.lefarmico.petfinder.presentation.screen.login.model.LoginEvent
import com.lefarmico.petfinder.presentation.screen.login.model.LoginState

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel(modelClass = LoginViewModel::class.java),
    viewState: LoginState
) {
    val focusManager = LocalFocusManager.current
    val login = viewState.loginField
    val password = viewState.passwordField
    val passwordCheckBox = viewState.showPasswordCheckBox
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        val verticalPadding = 8.dp
        val (
            topBar, description, usernameField, passwordField,
            signInButton, signUpPassword, showPassword
        ) = createRefs()
        val chain = createVerticalChain(
            usernameField, passwordField, showPassword, signUpPassword,
            chainStyle = ChainStyle.Packed
        )
        constrain(chain) {
            top.linkTo(description.bottom)
            bottom.linkTo(signInButton.top)
        }
        Text(
            text = stringResource(id = R.string.login_screen_title),
            style = MaterialTheme.typography.h2,
            modifier = Modifier.constrainAs(topBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = stringResource(id = R.string.login_screen_description),
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.constrainAs(description) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(topBar.bottom)
            }
        )
        UsernameTextField(
            userName = login,
            modifier = Modifier
                .constrainAs(usernameField) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onValueChange = { value ->
                viewModel.setLoginField(value)
            },
            onActionClick = {
                focusManager.clearFocus()
            }
        )
        PasswordTextField(
            password = password,
            isPasswordShown = passwordCheckBox,
            modifier = Modifier
                .padding(top = verticalPadding)
                .constrainAs(passwordField) {
                    start.linkTo(usernameField.start)
                    end.linkTo(usernameField.end)
                },
            onValueChange = { value ->
                viewModel.setPasswordField(value)
            },
            onActionClick = {
                focusManager.clearFocus()
            }
        )
        ShowPasswordCheckBox(
            modifier = Modifier.constrainAs(showPassword) {
                start.linkTo(usernameField.start)
            },
            isChecked = passwordCheckBox,
            onCheckChange = {
                viewModel.showPassword(it)
            }
        )
        SignUpTextClickable(
            modifier = Modifier
                .padding(top = verticalPadding)
                .constrainAs(signUpPassword) {
                    start.linkTo(usernameField.start)
                    end.linkTo(usernameField.end)
                }
        )
        Button(
            onClick = { viewModel.onTriggerEvent(LoginEvent.SingIn) },
            modifier = Modifier.constrainAs(signInButton) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text(text = stringResource(id = R.string.sign_in_button))
        }
    }
}
