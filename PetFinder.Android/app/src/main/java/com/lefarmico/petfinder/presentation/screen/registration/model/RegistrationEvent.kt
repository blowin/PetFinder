package com.lefarmico.petfinder.presentation.screen.registration.model

sealed class RegistrationEvent {
    data class SetEmailField(val value: String) : RegistrationEvent()
    data class SetPasswordField(val value: String) : RegistrationEvent()
    data class SetConfirmPasswordField(val value: String) : RegistrationEvent()
    data class SetShowPasswordCheckBox(val value: Boolean) : RegistrationEvent()
}
