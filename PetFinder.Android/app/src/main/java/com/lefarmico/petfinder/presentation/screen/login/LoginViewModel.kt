package com.lefarmico.petfinder.presentation.screen.login

import com.lefarmico.core.base.mvi.BaseState
import com.lefarmico.core.base.mvi.MviViewModel
import com.lefarmico.core.extension.cast
import com.lefarmico.petfinder.presentation.screen.login.model.LoginEvent
import com.lefarmico.petfinder.presentation.screen.login.model.LoginState

class LoginViewModel : MviViewModel<BaseState<LoginState>, LoginEvent>() {

    init {
        setState(BaseState.Data(LoginState()))
    }

    override fun onTriggerEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.SetLoginField -> setLoginField(event.value)
            is LoginEvent.SetPasswordField -> setPasswordField(event.value)
            is LoginEvent.SetPasswordVisibility -> showPassword(event.value)
            LoginEvent.SingIn -> setState(BaseState.Data(LoginState()))
        }
    }

    fun setLoginField(value: String) = safeLaunch {
        tryUpdateState {
            it.copy(loginField = value)
        }
    }

    fun setPasswordField(value: String) = safeLaunch {
        tryUpdateState {
            it.copy(passwordField = value)
        }
    }

    fun showPassword(value: Boolean) = safeLaunch {
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
