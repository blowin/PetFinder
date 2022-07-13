package com.lefarmico.petfinder.presentation.screen.registration

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.lefarmico.petfinder.R
import com.lefarmico.petfinder.presentation.navigation.NavigationActions
import com.lefarmico.petfinder.presentation.navigation.NavigationActionsDemoImpl
import com.lefarmico.petfinder.presentation.screen.registration.model.RegistrationEvent
import com.lefarmico.petfinder.ui.theme.petfinder.PF_PasswordTextField
import com.lefarmico.petfinder.ui.theme.petfinder.PF_TextField
import com.lefarmico.petfinder.ui.theme.petfinder.PF_TextWithCheckBox
import com.lefarmico.petfinder.ui.view.validator.TextValidator
import com.lefarmico.petfinder.ui.view.validator.ValidationResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    navigationActions: NavigationActions,
    viewModel: RegistrationViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    val email = state.emailField
    val password = state.passwordField
    val confirmPassword = state.confirmPasswordField
    val showPassword = state.showPasswordBox

    val paddingTop = 8.dp

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.registration_screen_name))
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = stringResource(
                                id = R.string.pop_up
                            )
                        )
                    }
                }
            )
        }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val (
                emailField, passwordField,
                confirmPasswordField, showPasswordBox,
                signUpButton
            ) = createRefs()

            val chain = createVerticalChain(
                emailField, passwordField,
                confirmPasswordField, showPasswordBox,
                chainStyle = ChainStyle.Packed
            )

            // Email text field
            PF_TextField(
                Modifier.constrainAs(emailField) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                value = email,
                validators = {
                    addValidator(TextValidator.EmptyText)
                    addValidator(TextValidator.Email)
                },
                label = stringResource(id = R.string.email),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = stringResource(id = R.string.cd_email)
                    )
                },
                onValueChange = { value ->
                    viewModel.onTriggerEvent(
                        RegistrationEvent.SetEmailField(value)
                    )
                }
            )
            // Password text field
            PF_PasswordTextField(
                Modifier
                    .padding(top = paddingTop)
                    .constrainAs(passwordField) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                value = password,
                label = stringResource(id = R.string.password),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = stringResource(id = R.string.password)
                    )
                },
                onValueChange = { value ->
                    viewModel.onTriggerEvent(
                        RegistrationEvent.SetPasswordField(value)
                    )
                },
                validators = {
                    addValidator(TextValidator.DefPassword)
                }
            )
            // Confirm password text field
            PF_PasswordTextField(
                Modifier
                    .padding(top = paddingTop)
                    .constrainAs(confirmPasswordField) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                value = confirmPassword,
                label = stringResource(id = R.string.confirm_password),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = stringResource(id = R.string.confirm_password)
                    )
                },
                onValueChange = { value ->
                    viewModel.onTriggerEvent(
                        RegistrationEvent.SetConfirmPasswordField(value)
                    )
                },
                validators = {
                    addValidator(
                        object : TextValidator {
                            override fun validate(text: String): ValidationResponse? {
                                return if (text != state.passwordField) {
                                    ValidationResponse("Fields are not the same")
                                } else {
                                    null
                                }
                            }
                        }
                    )
                }
            )
            // Show password check box
            PF_TextWithCheckBox(
                modifier = Modifier
                    .constrainAs(showPasswordBox) {
                        start.linkTo(confirmPasswordField.start)
                    },
                text = stringResource(id = R.string.show_password),
                isChecked = showPassword,
                onCheckChange = { isChecked ->
                    viewModel.onTriggerEvent(
                        RegistrationEvent.SetShowPasswordCheckBox(isChecked)
                    )
                }
            )
            // SignUp button
            Button(
                modifier = Modifier.constrainAs(signUpButton) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
                onClick = { /*TODO*/ }
            ) {
                Text(text = stringResource(id = R.string.sign_up_text_button))
            }
        }
        it
    }
}
@Preview(showBackground = true)
@Composable
fun RegistrationScreenDemo() {
    RegistrationScreen(navigationActions = NavigationActionsDemoImpl())
}
