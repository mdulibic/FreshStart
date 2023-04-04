package com.example.freshstart.view.registration

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.freshstart.view.customView.*
import com.example.freshstart.viewModels.RegistrationViewModel
import timber.log.Timber

@Composable
fun BasicInfoRegistrationScreen(registrationViewModel: RegistrationViewModel) {
    val registrationInputState by registrationViewModel.registrationInputState.collectAsState()
    val errorMessage by registrationViewModel.inputError.collectAsState()
    BasicInfoRegistrationLayout(
        registrationInputState = registrationInputState,
        onValueChange = registrationViewModel::onValueChange,
        onClickLogin = registrationViewModel::onRegistrationClicked,
        errorMessage = errorMessage
    )
}

@Composable
fun BasicInfoRegistrationLayout(
    registrationInputState: RegistrationViewModel.RegistrationInputState,
    onValueChange: (String, RegistrationViewModel.RegistrationInputModel) -> Unit,
    onClickLogin: (Activity) -> Unit,
    errorMessage: ErrorMessage?
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()).weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                for (i in 0..2) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Step(modifier = Modifier.weight(1f), isActive = i == 0)
                }
            }
            Image(
                modifier = Modifier.size(256.dp).padding(vertical = 16.dp),
                painter = painterResource(id = R.drawable.lumiere_laptop_with_presentation_and_an_open_book),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                text = stringResource(R.string.basic_information),
                style = MaterialTheme.typography.h2,
                color = Blue,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            registrationInputState.inputs.forEach { state ->
                CustomTextField(
                    modifier = Modifier
                        .background(color = MaterialTheme.colors.background)
                        .padding(vertical = 4.dp, horizontal = 20.dp),
                    label = state.label,
                    onChange = { onValueChange(it, state) },
                    value = state.value,
                    isPassword = state is RegistrationViewModel.RegistrationInputModel.Password || state is RegistrationViewModel.RegistrationInputModel.RepeatPassword
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
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
            isEnabled = registrationInputState.isButtonEnabled,
            text = R.string.proceed,
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
private fun ChooseTagsRegistrationLayoutPreview() {
    FreshStartTheme {
        BasicInfoRegistrationLayout(
            registrationInputState = RegistrationViewModel.RegistrationInputState(),
            onClickLogin = {},
            onValueChange = { _, _ -> },
            errorMessage = ErrorMessage(R.string.login_fail)
        )
    }
}