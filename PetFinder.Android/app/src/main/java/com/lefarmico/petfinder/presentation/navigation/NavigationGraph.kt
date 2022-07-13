package com.lefarmico.petfinder.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lefarmico.petfinder.presentation.screen.home.HomeScreen
import com.lefarmico.petfinder.presentation.screen.login.LoginScreen
import com.lefarmico.petfinder.presentation.screen.registration.RegistrationScreen
import com.lefarmico.petfinder.presentation.screen.splash.SplashScreen

@Composable
fun PetFinderNavGraph(
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
        composable(NavigationDestinations.HOME) {
            HomeScreen(navigationActions = navigationActions)
        }
        composable(NavigationDestinations.REGISTRATION) {
            RegistrationScreen(navigationActions = navigationActions)
        }
    }
}
