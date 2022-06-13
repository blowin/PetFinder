package com.lefarmico.petfinder.unitConverterApp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lefarmico.petfinder.Repository
import com.lefarmico.petfinder.unitConverterApp.screen.ComposeUnitConverterScreen
import com.lefarmico.petfinder.unitConverterApp.screen.DistancesConverter
import com.lefarmico.petfinder.unitConverterApp.screen.LaunchedEffectDemo
import com.lefarmico.petfinder.unitConverterApp.screen.TemperatureConverter
import com.lefarmico.petfinder.unitConverterApp.screen.loginScreen.LoginScreenDemo
import com.lefarmico.petfinder.unitConverterApp.viewModel.ViewModelFactory
import kotlinx.coroutines.launch

class ComposeUnitConverterActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ViewModelFactory(Repository(applicationContext))
        setContent {
            ComposeUnitConverterTheme() {
                LoginScreenDemo()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ComposeUnitConverter(factory: ViewModelFactory) {
    val navController = rememberNavController()
    val menuItems = listOf("Item #1", "Item #2")
    val scaffoldState = rememberScaffoldState()
    val snackbarCoroutinesScope = rememberCoroutineScope()
    ComposeUnitConverterTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                ComposeUnitConverterTopBar(menuItems) { s ->
                    snackbarCoroutinesScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(s)
                    }
                }
            },
            bottomBar = {
                ComposeUnitConverterBottomBar(navController = navController)
            }
        ) {
            ComposeUnitConverterNavHost(
                navController = navController,
                factory = factory
            )
        }
    }
}

@Composable
fun ComposeUnitConverterTopBar(menuItems: List<String>, onClick: (String) -> Unit) {
    var menuOpened by remember { mutableStateOf(false) }
    TopAppBar(title = {
        Text(text = "Petfinder")
    }, actions = {
            Box {
                IconButton(onClick = { menuOpened = true }) {
                    Icon(Icons.Default.MoreVert, "")
                }
                DropdownMenu(expanded = menuOpened, onDismissRequest = { menuOpened = false }) {
                    menuItems.forEachIndexed { index, s ->
                        if (index > 0) Divider()
                        DropdownMenuItem(onClick = {
                            menuOpened = false
                            onClick(s)
                        }) {
                            Text(s)
                        }
                    }
                }
            }
        })
}

@Composable
fun ComposeUnitConverterBottomBar(navController: NavHostController) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        ComposeUnitConverterScreen.sceens.forEach { screen ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        launchSingleTop = true
                    }
                },
                label = {
                    Text(text = stringResource(id = screen.label))
                },
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = stringResource(id = screen.label)
                    )
                },
                alwaysShowLabel = true
            )
        }
    }
}

@Composable
fun ComposeUnitConverterNavHost(
    navController: NavHostController,
    factory: ViewModelProvider.Factory?
) {
    NavHost(
        navController = navController,
        startDestination = ComposeUnitConverterScreen.route_temperature
    ) {
        composable(ComposeUnitConverterScreen.route_temperature) {
            TemperatureConverter(viewModel = viewModel(factory = factory))
        }
        composable(ComposeUnitConverterScreen.route_distances) {
            DistancesConverter(viewModel = viewModel(factory = factory))
        }
    }
}
