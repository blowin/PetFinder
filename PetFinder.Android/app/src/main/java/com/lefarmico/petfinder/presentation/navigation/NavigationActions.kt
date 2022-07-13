package com.lefarmico.petfinder.presentation.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

object NavigationDestinations {
    const val LOGIN = "login"
    const val SPLASH = "splash"
    const val HOME = "home"
    const val REGISTRATION = "registration"
}

class NavigationActionsImpl(
    navController: NavHostController
) : NavigationActions {
    override val navigateToLogin: () -> Unit = {
        navController.navigate(NavigationDestinations.LOGIN) {
            launchSingleTop = true
            restoreState = true
        }
    }
    override val navigateToSplash: () -> Unit = {
        navController.navigate(NavigationDestinations.SPLASH) {
            launchSingleTop = true
            restoreState = true
        }
    }
    override val navigateToHome: () -> Unit = {
        navController.navigate(NavigationDestinations.HOME) {
            launchSingleTop = true
            restoreState = true
        }
    }
    override val navigateToRegistration: () -> Unit = {
        navController.navigate(NavigationDestinations.REGISTRATION) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

interface NavigationActions {
    val navigateToLogin: () -> Unit
    val navigateToSplash: () -> Unit
    val navigateToHome: () -> Unit
    val navigateToRegistration: () -> Unit
}

class NavigationActionsDemoImpl : NavigationActions {
    override val navigateToLogin: () -> Unit = {}
    override val navigateToSplash: () -> Unit = {}
    override val navigateToHome: () -> Unit = {}
    override val navigateToRegistration: () -> Unit = {}
}
