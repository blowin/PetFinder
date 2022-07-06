package com.lefarmico.petfinder.presentation.screen.login.model

sealed class LoginEvent {
    data class SetLoginField(val value: String) : LoginEvent()
    data class SetPasswordField(val value: String) : LoginEvent()
    data class SetPasswordVisibility(val value: Boolean) : LoginEvent()
    data class SingInPressed(val loginField: String, val passwordField: String) : LoginEvent()
    object SingUpPressed : LoginEvent()
    object ForgotPassPressed : LoginEvent()
    object ErrorMessageShown : LoginEvent()
    object CheckForSignedIn : LoginEvent()
}
