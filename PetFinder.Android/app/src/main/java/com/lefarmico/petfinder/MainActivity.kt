package com.lefarmico.petfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.lefarmico.petfinder.presentation.navigation.NavigationActionsImpl
import com.lefarmico.petfinder.presentation.navigation.PetFinderNavGraph
import com.lefarmico.petfinder.ui.theme.PetFinderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetFinderTheme {
                val navController = rememberNavController()
                val navigationActions = NavigationActionsImpl(navController)
                PetFinderNavGraph(
                    navController = navController,
                    navigationActions = navigationActions
                )
            }
        }
    }
}
