package com.lefarmico.petfinder.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lefarmico.petfinder.presentation.screen.login.LoginScreen
import com.lefarmico.petfinder.presentation.screen.registration.RegistrationScreen
import com.lefarmico.petfinder.presentation.screen.root.RootScreen
import com.lefarmico.petfinder.presentation.screen.splash.SplashScreen

@Composable
fun PetFinderStartNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navigationActions: NavigationActions,
    startDestination: String = NavigationDestinations.SPLASH
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationDestinations.SPLASH) {
            SplashScreen(navigationActions = navigationActions)
        }
        composable(NavigationDestinations.LOGIN) {
            LoginScreen(navigationActions = navigationActions)
        }
        composable(NavigationDestinations.ROOT) {
            RootScreen()
        }
        composable(NavigationDestinations.REGISTRATION) {
            RegistrationScreen(navigationActions = navigationActions)
        }
    }
}
