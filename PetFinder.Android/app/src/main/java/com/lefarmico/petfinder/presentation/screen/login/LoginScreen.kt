package com.lefarmico.petfinder.presentation.screen.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lefarmico.core.base.mvi.BaseState
import com.lefarmico.core.extension.cast
import com.lefarmico.petfinder.presentation.screen.login.model.LoginState
import com.lefarmico.petfinder.presentation.screen.login.view.LoginScreenContent
import com.lefarmico.petfinder.ui.theme.PetFinderTheme

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
        BaseState.Empty -> TODO()
        is BaseState.Error -> LoginScreenContent(
            modifier = modifier,
            navController = navController,
            viewState = LoginState()
        )
        BaseState.Loading -> TODO()
    }
}

//@Preview(showBackground = true, group = "login")
//@Composable
//fun LoginScreenDemo() {
//    PetFinderTheme {
//        LoginScreen()
//    }
//}
