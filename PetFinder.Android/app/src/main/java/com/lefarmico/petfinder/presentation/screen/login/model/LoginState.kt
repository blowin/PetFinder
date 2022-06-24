package com.lefarmico.petfinder.presentation.screen.login.model

data class LoginState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val loginField: String = "",
    val passwordField: String = "",
    val showPasswordCheckBox: Boolean = false,
    val toast: Toast? = null
) {
    data class Toast(val message: String)
}
