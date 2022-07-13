package com.lefarmico.petfinder.presentation.screen.registration.model

data class RegistrationState(
    val emailField: String = "",
    val passwordField: String = "",
    val confirmPasswordField: String = "",
    val showPasswordBox: Boolean = false,
    val isLoading: Boolean = false,
    val errorMsg: String? = null, // TODO : Change to ErrorMassage
)
