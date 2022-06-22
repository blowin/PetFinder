package com.lefarmico.petfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lefarmico.petfinder.presentation.screen.login.LoginScreen
import com.lefarmico.petfinder.ui.theme.PetFinderTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetFinderTheme {
                LoginScreen()
            }
        }
    }
}
