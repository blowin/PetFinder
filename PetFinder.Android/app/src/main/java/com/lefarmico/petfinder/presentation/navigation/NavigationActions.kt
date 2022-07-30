package com.lefarmico.petfinder.presentation.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

object NavigationDestinations {
    const val LOGIN = "login"
    const val SPLASH = "splash"
    const val REGISTRATION = "registration"
    const val ROOT = "root"
}

class NavigationActionsImpl(
    private val navController: NavHostController
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
    override val navigateToRoot: () -> Unit = {
        navController.navigate(NavigationDestinations.ROOT) {
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
    override val navigateToSettings: () -> Unit
        get() = TODO("Not yet implemented")
    override val navigateToCheckNumber: () -> Unit
        get() = TODO("Not yet implemented")
    override val navigateToProfile: () -> Unit
        get() = TODO("Not yet implemented")
    override val navigateToNewPost: () -> Unit
        get() = TODO("Not yet implemented")

    override fun getCurrentScreen(): String? {
        return navController.currentBackStackEntry?.destination?.route
    }
}

interface NavigationActions {
    val navigateToLogin: () -> Unit
    val navigateToSplash: () -> Unit
    val navigateToRoot: () -> Unit
    val navigateToRegistration: () -> Unit
    val navigateToSettings: () -> Unit
    val navigateToCheckNumber: () -> Unit
    val navigateToProfile: () -> Unit
    val navigateToNewPost: () -> Unit

    fun getCurrentScreen(): String?
}

class NavigationActionsDemoImpl : NavigationActions {
    override val navigateToLogin: () -> Unit = {}
    override val navigateToSplash: () -> Unit = {}
    override val navigateToRoot: () -> Unit = {}
    override val navigateToRegistration: () -> Unit = {}
    override val navigateToSettings: () -> Unit = {}
    override val navigateToCheckNumber: () -> Unit = {}
    override val navigateToProfile: () -> Unit = {}
    override val navigateToNewPost: () -> Unit = {}
    override fun getCurrentScreen(): String? = null
}
