package com.lefarmico.petfinder.presentation.screen.registration

import com.lefarmico.core.base.mvi.MviViewModel
import com.lefarmico.petfinder.presentation.screen.registration.model.RegistrationEvent
import com.lefarmico.petfinder.presentation.screen.registration.model.RegistrationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegistrationViewModel : MviViewModel<RegistrationState, RegistrationEvent>() {

    override val _state: MutableStateFlow<RegistrationState> = MutableStateFlow(RegistrationState())
    override val state: StateFlow<RegistrationState> = _state.asStateFlow()

    override fun onTriggerEvent(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.SetConfirmPasswordField -> setConfirmPassowrdField(event.value)
            is RegistrationEvent.SetEmailField -> setEmailField(event.value)
            is RegistrationEvent.SetPasswordField -> setPasswordField(event.value)
            is RegistrationEvent.SetShowPasswordCheckBox -> setShowPasswordCheckBox(event.value)
        }
    }

    private fun setEmailField(value: String) {
        _state.value = _state.value.copy(emailField = value)
    }
    private fun setPasswordField(value: String) {
        _state.value = _state.value.copy(passwordField = value)
    }
    private fun setConfirmPassowrdField(value: String) {
        _state.value = _state.value.copy(confirmPasswordField = value)
    }
    private fun setShowPasswordCheckBox(value: Boolean) {
        _state.value = _state.value.copy(showPasswordBox = value)
    }
}
