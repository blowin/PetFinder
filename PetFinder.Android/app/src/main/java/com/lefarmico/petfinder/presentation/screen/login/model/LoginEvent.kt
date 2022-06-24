package com.lefarmico.petfinder.presentation.screen.login.model

sealed class LoginEvent {
    data class SetLoginField(val value: String) : LoginEvent()
    data class SetPasswordField(val value: String) : LoginEvent()
    data class SetPasswordVisibility(val value: Boolean) : LoginEvent()
    object SingInPressed : LoginEvent()
    object SingUpPressed : LoginEvent()
    object ForgotPassPressed : LoginEvent()
    object ToastShowed : LoginEvent()
}
