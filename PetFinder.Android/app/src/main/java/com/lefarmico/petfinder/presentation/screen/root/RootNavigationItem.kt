package com.lefarmico.petfinder.presentation.screen.root

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.lefarmico.petfinder.R

object RootNavigationDestinations {
    const val HOME = "home"
    const val SETTINGS = "settings"
    const val CHECK_NUMBER = "checkNumber"
    const val PROFILE = "profile"
    const val NEW_POST = "new_post"
}

sealed class RootNavigationItem(
    val route: String,
    @StringRes val label: Int,
    val iconSelected: ImageVector,
    val iconUnselected: ImageVector,
) {

    companion object {
        fun getItems(): List<RootNavigationItem> {
            return listOf(
                Home, CheckNumber, Settings, NewPost
            )
        }
    }

    object Home : RootNavigationItem(
        RootNavigationDestinations.HOME,
        R.string.home_screen,
        Icons.Default.Home,
        Icons.Outlined.Home
    )
    object Settings : RootNavigationItem(
        RootNavigationDestinations.SETTINGS,
        R.string.settings_screen,
        Icons.Default.Settings,
        Icons.Outlined.Settings
    )
    object CheckNumber : RootNavigationItem(
        RootNavigationDestinations.CHECK_NUMBER,
        R.string.check_number_screen,
        Icons.Default.CheckCircle,
        Icons.Outlined.CheckCircle
    )
    object NewPost : RootNavigationItem(
        RootNavigationDestinations.NEW_POST,
        R.string.new_post_screen,
        Icons.Default.AddCircle,
        Icons.Outlined.Add
    )
}
