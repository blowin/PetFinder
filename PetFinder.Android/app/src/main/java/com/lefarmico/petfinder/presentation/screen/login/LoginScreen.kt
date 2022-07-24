package com.lefarmico.petfinder.presentation.screen.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.lefarmico.petfinder.R
import com.lefarmico.petfinder.presentation.navigation.NavigationActions
import com.lefarmico.petfinder.presentation.navigation.NavigationActionsDemoImpl
import com.lefarmico.petfinder.presentation.screen.login.model.LoginEvent
import com.lefarmico.petfinder.presentation.screen.login.view.SignUpTextClickable
import com.lefarmico.petfinder.ui.theme.petfinder.PF_PasswordTextField
import com.lefarmico.petfinder.ui.theme.petfinder.PF_TextField
import com.lefarmico.petfinder.ui.theme.petfinder.PF_TextWithCheckBox
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
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.constrainAs(topBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Text(
            text = stringResource(id = R.string.login_screen_description),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.constrainAs(description) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(topBar.bottom)
            }
        )
        // UserName
        PF_TextField(
            modifier = Modifier
                .constrainAs(usernameField) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            value = login,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Face,
                    contentDescription = stringResource(id = R.string.username)
                )
            },
            label = stringResource(id = R.string.username),
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
        // Password field
        PF_PasswordTextField(
            modifier = Modifier
                .padding(top = verticalPadding)
                .constrainAs(passwordField) {
                    start.linkTo(usernameField.start)
                    end.linkTo(usernameField.end)
                },
            value = password,
            showPassword = passwordCheckBox,
            onValueChange = { value ->
                viewModel.onTriggerEvent(
                    LoginEvent.SetPasswordField(
                        value = value
                    )
                )
            },
            label = stringResource(id = R.string.password),
            onActionClick = {
                focusManager.clearFocus()
            }
        )
        // Show password check box
        PF_TextWithCheckBox(
            modifier = Modifier.constrainAs(showPassword) {
                start.linkTo(usernameField.start)
            },
            text = stringResource(id = R.string.show_password),
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
                navigationActions.navigateToHome()
            },
            onSignUpClick = {
                navigationActions.navigateToRegistration()
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

    if (errorMessage != null) {
        ShowToast(message = errorMessage) {
            viewModel.onTriggerEvent(LoginEvent.ErrorMessageShown)
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

@Preview(showBackground = true)
@Composable
fun LoginScreenDemo() {
    LoginScreen(navigationActions = NavigationActionsDemoImpl())
}
