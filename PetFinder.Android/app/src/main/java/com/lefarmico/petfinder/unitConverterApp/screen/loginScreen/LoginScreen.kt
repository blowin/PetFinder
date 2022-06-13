package com.lefarmico.petfinder.unitConverterApp.screen.loginScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.lefarmico.petfinder.R

@Preview(showBackground = true)
@Composable
fun LoginScreenDemo() {
    val userName = remember { mutableStateOf("") }
    val passwordString = remember { mutableStateOf("") }
    val isPasswordShown = remember { mutableStateOf(false) }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        val (topbar, description, login, password, signInButton) = createRefs()
        Text(
            text = stringResource(id = R.string.login_screen_title),
            style = MaterialTheme.typography.h2,
            modifier = Modifier.constrainAs(topbar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = stringResource(id = R.string.login_screen_description),
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.constrainAs(description) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(topbar.bottom)
            }
        )
        LoginField(
            userName = userName,
            modifier = Modifier.constrainAs(login) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(description.bottom)
            }
        )
        PasswordField(
            password = passwordString,
            isPasswordShown = isPasswordShown,
            modifier = Modifier.constrainAs(password) {
                top.linkTo(login.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(signInButton) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text(text = stringResource(id = R.string.sign_in_button))
        }
    }
}

@Composable
fun LoginField(
    userName: State<String>,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    onActionClick: () -> Unit = {}
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.username),
            style = MaterialTheme.typography.body1
        )
        LoginTextField(
            userName = userName,
            onValueChange = onValueChange,
            onActionClick = onActionClick
        )
    }
}

@Composable
fun PasswordField(
    password: State<String>,
    isPasswordShown: State<Boolean>,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    onActionClick: () -> Unit = {}
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.password),
            style = MaterialTheme.typography.body1
        )
        PasswordTextField(
            password = password,
            isPasswordShown = isPasswordShown,
            onValueChange = onValueChange,
            onActionClick = onActionClick
        )
    }
}

@Composable
fun LoginTextField(
    userName: State<String>,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    onActionClick: () -> Unit = {}
) {
    OutlinedTextField(
        modifier = modifier,
        value = userName.value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = stringResource(id = R.string.username)) },
        placeholder = { Text(text = stringResource(id = R.string.username_field)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Face,
                contentDescription = stringResource(id = R.string.username)
            )
        },
        singleLine = true,
        keyboardActions = KeyboardActions(onAny = { onActionClick() }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        )
    )
}

@Composable
fun PasswordTextField(
    password: State<String>,
    isPasswordShown: State<Boolean>,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
    onActionClick: () -> Unit = {}
) {
    val passwordHider = if (isPasswordShown.value) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }
    OutlinedTextField(
        modifier = modifier,
        value = password.value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = stringResource(id = R.string.password)) },
        placeholder = { Text(text = stringResource(id = R.string.password_field)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = stringResource(id = R.string.password)
            )
        },
        singleLine = true,
        keyboardActions = KeyboardActions(onAny = { onActionClick() }),
        visualTransformation = passwordHider,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        )
    )
}
