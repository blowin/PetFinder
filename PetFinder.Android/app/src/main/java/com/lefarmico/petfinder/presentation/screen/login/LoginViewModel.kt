package com.lefarmico.petfinder.presentation.screen.login

import com.lefarmico.core.base.mvi.BaseState
import com.lefarmico.core.base.mvi.MviViewModel
import com.lefarmico.core.extension.cast
import com.lefarmico.petfinder.presentation.screen.login.model.LoginEvent
import com.lefarmico.petfinder.presentation.screen.login.model.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : MviViewModel<BaseState<LoginState>, LoginEvent>() {

    init {
        setState(BaseState.Data(LoginState()))
    }

    override fun onTriggerEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.SetLoginField -> setLoginField(event.value)
            is LoginEvent.SetPasswordField -> setPasswordField(event.value)
            is LoginEvent.SetPasswordVisibility -> showPassword(event.value)
            LoginEvent.SingInPressed -> setToast("SignIn pressed")
            LoginEvent.ForgotPassPressed -> setToast("ForgotPass pressed")
            LoginEvent.SingUpPressed -> setToast("SignUp pressed")
            LoginEvent.ToastShowed -> resetToast()
        }
    }

    private fun resetToast() = safeLaunch {
        tryUpdateState {
            it.copy(toast = null)
        }
    }

    private fun setToast(message: String) = safeLaunch {
        tryUpdateState {
            it.copy(toast = LoginState.Toast(message))
        }
    }

    private fun setLoginField(value: String) = safeLaunch {
        tryUpdateState {
            it.copy(loginField = value)
        }
    }

    private fun setPasswordField(value: String) = safeLaunch {
        tryUpdateState {
            it.copy(passwordField = value)
        }
    }

    private fun showPassword(value: Boolean) = safeLaunch {
        tryUpdateState {
            it.copy(showPasswordCheckBox = value)
        }
    }

    private fun tryUpdateState(newState: (LoginState) -> LoginState) = safeLaunch {
        val currentState = when (uiState.value) {
            is BaseState.Data -> uiState.value.cast<BaseState.Data<LoginState>>().value
            BaseState.Empty -> LoginState()
            is BaseState.Error -> LoginState()
            BaseState.Loading -> LoginState()
        }
        setState(BaseState.Data(newState(currentState)))
    }
}
