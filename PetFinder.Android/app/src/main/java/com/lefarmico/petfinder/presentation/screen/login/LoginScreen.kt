package com.lefarmico.petfinder.presentation.screen.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.lefarmico.petfinder.R
import com.lefarmico.petfinder.presentation.navigation.NavigationActions
import com.lefarmico.petfinder.presentation.screen.login.model.LoginEvent
import com.lefarmico.petfinder.presentation.screen.login.view.component.PasswordTextField
import com.lefarmico.petfinder.presentation.screen.login.view.component.ShowPasswordCheckBox
import com.lefarmico.petfinder.presentation.screen.login.view.component.SignUpTextClickable
import com.lefarmico.petfinder.presentation.screen.login.view.component.UsernameTextField
import com.lefarmico.petfinder.ui.view.ForegroundLoading
import com.lefarmico.petfinder.ui.view.ShowToast

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    navigationActions: NavigationActions
) {
    val state by viewModel.state.collectAsState()

    val focusManager = LocalFocusManager.current
    val login = state.loginField
    val password = state.passwordField
    val passwordCheckBox = state.showPasswordCheckBox
    val errorMessage = state.errorMsg
    val isLoading = state.isLoading
    val isSignedIn = state.isSignedIn

    viewModel.onTriggerEvent(LoginEvent.CheckForSignedIn)

    if (errorMessage != null) {
        ShowToast(message = errorMessage)
        viewModel.onTriggerEvent(LoginEvent.ErrorMessageShown)
    }

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
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
            modifier = Modifier
                .constrainAs(usernameField) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            userName = login,
            onValueChange = { value ->
                viewModel.onTriggerEvent(
                    LoginEvent.SetLoginField(
                        value = value
                    )
                )
            },
            onActionClick = {
                focusManager.clearFocus()
            }
        )
        PasswordTextField(
            modifier = Modifier
                .padding(top = verticalPadding)
                .constrainAs(passwordField) {
                    start.linkTo(usernameField.start)
                    end.linkTo(usernameField.end)
                },
            password = password,
            isPasswordShown = passwordCheckBox,
            isEnabled = !isLoading,
            onValueChange = { value ->
                viewModel.onTriggerEvent(
                    LoginEvent.SetPasswordField(
                        value = value
                    )
                )
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
            onCheckChange = { isChecked ->
                viewModel.onTriggerEvent(
                    LoginEvent.SetPasswordVisibility(
                        value = isChecked
                    )
                )
            }
        )
        SignUpTextClickable(
            modifier = Modifier
                .padding(top = verticalPadding)
                .constrainAs(signUpPassword) {
                    start.linkTo(usernameField.start)
                    end.linkTo(usernameField.end)
                },
            onForgotPasswordClick = {
                viewModel.onTriggerEvent(
                    LoginEvent.ForgotPassPressed
                )
            },
            onSignUpClick = {
                viewModel.onTriggerEvent(
                    LoginEvent.SingUpPressed
                )
            }
        )
        Button(
            modifier = Modifier.constrainAs(signInButton) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            onClick = {
                viewModel.onTriggerEvent(
                    LoginEvent.SingInPressed(
                        loginField = login,
                        passwordField = password
                    )
                )
            },
        ) {
            Text(text = stringResource(id = R.string.sign_in_button))
        }
    }

    if (isSignedIn) {
        navigationActions.navigateToHome()
    }
    if (isLoading) {
        ForegroundLoading()
        focusManager.clearFocus()
    }
}
