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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lefarmico.petfinder.R
import com.lefarmico.petfinder.presentation.screen.login.LoginViewModel
import com.lefarmico.petfinder.presentation.screen.login.model.LoginEvent
import com.lefarmico.petfinder.presentation.screen.login.model.LoginState
import com.lefarmico.petfinder.presentation.screen.login.view.component.PasswordTextField
import com.lefarmico.petfinder.presentation.screen.login.view.component.ShowPasswordCheckBox
import com.lefarmico.petfinder.presentation.screen.login.view.component.SignUpTextClickable
import com.lefarmico.petfinder.presentation.screen.login.view.component.UsernameTextField
import com.lefarmico.petfinder.ui.view.ShowToast

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
    viewState: LoginState
) {
    val focusManager = LocalFocusManager.current
    val login = viewState.loginField
    val password = viewState.passwordField
    val passwordCheckBox = viewState.showPasswordCheckBox
    val toast = viewState.toast

    if (toast != null) {
        ShowToast(message = toast.message)
        viewModel.onTriggerEvent(LoginEvent.ToastShowed)
    }

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
            password = password,
            isPasswordShown = passwordCheckBox,
            modifier = Modifier
                .padding(top = verticalPadding)
                .constrainAs(passwordField) {
                    start.linkTo(usernameField.start)
                    end.linkTo(usernameField.end)
                },
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
            onClick = { viewModel.onTriggerEvent(LoginEvent.SingInPressed) },
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
