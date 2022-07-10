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
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    val valueState = remember { mutableStateOf("") }
//                    ValidatedOutlinedTextField(
//                        value = valueState.value,
//                        label = "Valid",
//                        validators = {
//                            addValidator(TextValidator.EmptyText)
//                            addValidator(TextValidator.DefPassword)
//                        },
//                        onValueChange = {
//                            valueState.value = it
//                        }
//                    )
//                }
            }
        }
    }
}
