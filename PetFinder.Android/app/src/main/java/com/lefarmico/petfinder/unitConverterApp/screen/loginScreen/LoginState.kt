package com.lefarmico.petfinder.unitConverterApp.screen.loginScreen

data class LoginState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val loginField: String = "",
    val passwordField: String = ""
)
