package com.lefarmico.petfinder.presentation.screen.root

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lefarmico.petfinder.R
import com.lefarmico.petfinder.presentation.screen.home.HomeScreen
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen(modifier: Modifier = Modifier) {
    val navController: NavHostController = rememberNavController()
    val startMenuItem = RootNavigationItem.Home
    val currentMenuItem = remember { mutableStateOf<RootNavigationItem?>(startMenuItem) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            RootScreenTopBar(
                currentMenuItem.value,
                "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fsteamcdn-a.akamaihd.net%2Fsteamcommunity%2Fpublic%2Fimages%2Favatars%2F92%2F929b316f1adb43c47289032b62213c82f9c0b8e0_full.jpg&f=1&nofb=1",
            )
        },
        bottomBar = {
            RootScreenBottomBar(navController = navController) {
                currentMenuItem.value = it
            }
        }
    ) {
        RootScreenNavHost(navController = navController, startMenuItem = startMenuItem)
        it
    }
}

@Composable
fun RootScreenTopBar(
    menuItem: RootNavigationItem? = null,
    userImageUrl: String? = null
) {
    when (menuItem) {
        RootNavigationItem.Home -> {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.home_screen))
                },
                navigationIcon = {
                    Box(modifier = Modifier.padding(start = 16.dp)) {
                        Icon(
                            modifier = Modifier
                                .size(24.dp),
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                },
                actions = {
                    GlideImage(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .size(30.dp)
                            .clickable(
                                onClick = { },
                                interactionSource = remember { MutableInteractionSource() },
                                role = Role.Button,
                                indication = rememberRipple(bounded = true)
                            ),
                        imageModel = userImageUrl,
                        previewPlaceholder = R.drawable.beagle,
                        failure = {
                            Icon(
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .size(30.dp)
                                    .background(MaterialTheme.colorScheme.surface),
                                imageVector = Icons.Filled.Person,
                                contentDescription = "User"
                            )
                        }
                    )
                }
            )
        }
        null -> {
            SmallTopAppBar(
                title = {
                    Text(text = "Unknown route")
                }
            )
        }
        else -> {
            SmallTopAppBar(
                title = {
                    Text(text = stringResource(id = menuItem.label))
                }
            )
        }
    }
}

@Composable
fun RootScreenBottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    currentMenuItem: (RootNavigationItem) -> Unit = {}
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar(modifier = modifier) {
        RootNavigationItem.getItems().forEach { item ->
            val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
            if (selected) {
                currentMenuItem(item)
            }
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (selected) item.iconSelected else item.iconUnselected,
                        contentDescription = stringResource(id = item.label)
                    )
                },
                label = {
                    Text(text = stringResource(id = item.label))
                }
            )
        }
    }
}

@Composable
fun RootScreenNavHost(
    navController: NavHostController,
    startMenuItem: RootNavigationItem
) {
    NavHost(
        navController = navController,
        startDestination = startMenuItem.route
    ) {
        composable(RootNavigationItem.Home.route) {
            HomeScreen()
        }
        composable(RootNavigationItem.NewPost.route) {
        }
        composable(RootNavigationItem.CheckNumber.route) {
        }
        composable(RootNavigationItem.Settings.route) {
        }
    }
}
