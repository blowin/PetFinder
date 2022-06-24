package com.lefarmico.petfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lefarmico.petfinder.presentation.screen.login.LoginScreen
import com.lefarmico.petfinder.ui.theme.PetFinderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetFinderTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "login") {
                    composable(route = "login") {
                        LoginScreen(navController = navController)
                    }
                }
            }
        }
    }
}
