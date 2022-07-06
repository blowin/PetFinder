package com.lefarmico.petfinder.presentation.screen.login

import com.lefarmico.core.base.mvi.MviViewModel
import com.lefarmico.petfinder.presentation.screen.login.model.LoginEvent
import com.lefarmico.petfinder.presentation.screen.login.model.LoginState
import com.lefarmico.petfinder.utils.combine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : MviViewModel<LoginState, LoginEvent>() {

    override val _state = MutableStateFlow(LoginState())
    override val state = _state.asStateFlow()

    private val loginField = MutableStateFlow("")
    private val passwordField = MutableStateFlow("")
    private val showPasswordCheckBox = MutableStateFlow(false)
    private val loading = MutableStateFlow(false)
    private val errorMessage = MutableStateFlow<String?>(null)
    private val isSignedIn = MutableStateFlow(false)

    init {
        safeLaunch {
            combine(
                loginField,
                passwordField,
                showPasswordCheckBox,
                loading,
                errorMessage,
                isSignedIn
            ) { login, password, showPassword, loading, error, signedIn ->
                LoginState(
                    loginField = login,
                    passwordField = password,
                    showPasswordCheckBox = showPassword,
                    isLoading = loading,
                    errorMsg = error,
                    isSignedIn = signedIn
                )
            }.catch { throwable ->
                throw throwable
            }.collect {
                _state.value = it
            }
        }
    }

    override fun onTriggerEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.SetLoginField -> onLoginFieldChanged(event.value)
            is LoginEvent.SetPasswordField -> onPasswordFieldChanged(event.value)
            is LoginEvent.SetPasswordVisibility -> onShowPasswordSwitchChanged(event.value)
            is LoginEvent.SingInPressed -> onSignInPressed()
            LoginEvent.ForgotPassPressed -> {}
            LoginEvent.SingUpPressed -> test()
            LoginEvent.ErrorMessageShown -> onErrorMessageShown()
            LoginEvent.CheckForSignedIn -> checkForSignedIn()
        }
    }

    private fun checkForSignedIn() {
        isSignedIn.value = false
    }

    private fun onSignInPressed() = safeLaunch {
        loading.value = true
        delay(3000)
        loading.value = false
        errorMessage.value = "Login failed"
    }

    private fun onErrorMessageShown() = safeLaunch {
        errorMessage.value = null
    }

    private fun onLoginFieldChanged(value: String) = safeLaunch {
        loginField.value = value
    }

    private fun onPasswordFieldChanged(value: String) = safeLaunch {
        passwordField.value = value
    }

    private fun onShowPasswordSwitchChanged(value: Boolean) = safeLaunch {
        showPasswordCheckBox.value = value
    }

    private fun test() {
        isSignedIn.value = true
    }
}
