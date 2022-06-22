package com.lefarmico.petfinder.presentation.screen.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lefarmico.core.base.mvi.BaseState
import com.lefarmico.core.extension.cast
import com.lefarmico.petfinder.presentation.screen.login.model.LoginState
import com.lefarmico.petfinder.presentation.screen.login.view.LoginScreenContent
import com.lefarmico.petfinder.ui.theme.PetFinderTheme

@Composable
fun LoginScreen() {
    val viewModel = viewModel(modelClass = LoginViewModel::class.java)
    val state by viewModel.uiState.collectAsState()

    when (state) {
        is BaseState.Data -> LoginScreenContent(
            viewState = state.cast<BaseState.Data<LoginState>>().value
        )
        BaseState.Empty -> TODO()
        is BaseState.Error -> LoginScreenContent(
            viewState = LoginState()
        )
        BaseState.Loading -> TODO()
    }
}

@Preview(showBackground = true, group = "login")
@Composable
fun LoginScreenDemo() {
    PetFinderTheme {
        LoginScreen()
    }
}
