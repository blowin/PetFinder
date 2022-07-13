package com.lefarmico.petfinder.presentation.screen.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lefarmico.petfinder.R
import com.lefarmico.petfinder.presentation.navigation.NavigationActions

@Composable
fun SplashScreen(
    navigationActions: NavigationActions
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(128.dp),
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        }
    }
    LaunchedEffect(key1 = Unit, block = {
        navigationActions.navigateToLogin()
    })
}
