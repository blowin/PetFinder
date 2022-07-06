package com.lefarmico.petfinder.presentation.screen.login.model

data class LoginState(
    val isLoading: Boolean = false,
    val errorMsg: String? = null, // TODO : Change to ErrorMassage
    val loginField: String = "",
    val passwordField: String = "",
    val showPasswordCheckBox: Boolean = false,
    val isSignedIn: Boolean = false
)
