package com.example.freshstart.view.loginRegistration

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freshstart.R
import com.example.freshstart.model.ErrorMessage
import com.example.freshstart.ui.theme.*
import com.example.freshstart.util.getActivity
import com.example.freshstart.view.customView.ActionButton
import com.example.freshstart.view.customView.CustomTextField
import com.example.freshstart.view.customView.CustomTopAppBar
import com.example.freshstart.view.customView.NavigationItem
import com.example.freshstart.viewModels.LoginViewModel
import timber.log.Timber

@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {
    val loginInputState by loginViewModel.loginInputState.collectAsState()
    val errorMessage by loginViewModel.inputError.collectAsState()
    LoginLayout(
        loginInputState = loginInputState,
        onValueChange = loginViewModel::onValueChange,
        onClickBack = loginViewModel::onBackClicked,
        onClickLogin = loginViewModel::onLoginClicked,
        errorMessage = errorMessage
    )
}

@Composable
fun LoginLayout(
    loginInputState: LoginViewModel.LoginInputState,
    onValueChange: (String, LoginViewModel.LoginInputModel) -> Unit,
    onClickBack: () -> Unit,
    onClickLogin: (Activity) -> Unit,
    errorMessage: ErrorMessage?
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTopAppBar(
            navigationItem = NavigationItem({
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow),
                    contentDescription = null
                )
            }, action = onClickBack)
        )
        Image(
            modifier = Modifier.size(256.dp).padding(vertical = 16.dp),
            painter = painterResource(id = R.drawable.lumiere_tired_girl_in_pajamas),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            text = stringResource(R.string.hello_again),
            style = MaterialTheme.typography.h2,
            color = Blue
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            text = stringResource(R.string.welcome_back),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        loginInputState.inputs.forEach { state ->
            CustomTextField(
                modifier = Modifier
                    .background(color = MaterialTheme.colors.background)
                    .padding(vertical = 4.dp, horizontal = 20.dp),
                label = state.label,
                onChange = { onValueChange(it, state) },
                value = state.value,
                isPassword = state is LoginViewModel.LoginInputModel.Password
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        errorMessage?.let {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                text = stringResource(it.message),
                style = MaterialTheme.typography.body1,
                color = Red,
                textAlign = TextAlign.Center
            )
        }
        ActionButton(
            modifier = Modifier
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Green,
                disabledBackgroundColor = Disabled
            ),
            isEnabled = loginInputState.isButtonEnabled,
            text = R.string.login,
            onClick = {
                context.getActivity()?.let { activity ->
                    onClickLogin(activity)
                } ?: Timber.w("Activity is null")
            }
        )
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
private fun WelcomeLayoutPreview() {
    FreshStartTheme {
        LoginLayout(
            loginInputState = LoginViewModel.LoginInputState(),
            onClickLogin = {},
            onClickBack = {},
            onValueChange = { _, _ -> },
            errorMessage = ErrorMessage(R.string.login_fail)
        )
    }
}