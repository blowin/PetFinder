package com.lefarmico.petfinder.presentation.screen.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lefarmico.core.base.mvi.BaseState
import com.lefarmico.core.extension.cast
import com.lefarmico.petfinder.presentation.screen.login.model.LoginState
import com.lefarmico.petfinder.presentation.screen.login.view.LoginScreenContent
import com.lefarmico.petfinder.presentation.screen.login.view.LoginScreenError
import com.lefarmico.petfinder.presentation.screen.login.view.LoginScreenLoading

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.uiState.collectAsState()

    when (state) {
        is BaseState.Data -> LoginScreenContent(
            modifier = modifier,
            navController = navController,
            viewState = state.cast<BaseState.Data<LoginState>>().value
        )
        BaseState.Empty -> LoginScreenError(modifier = modifier)
        BaseState.Loading -> LoginScreenLoading(modifier = modifier)
        is BaseState.Error -> LoginScreenError(modifier = modifier)
    }
}

// @Preview(showBackground = true, group = "login")
// @Composable
// fun LoginScreenDemo() {
//    PetFinderTheme {
//        LoginScreen()
//    }
// }
