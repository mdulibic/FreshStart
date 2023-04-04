package com.example.freshstart.viewModels

import android.app.Activity
import androidx.lifecycle.viewModelScope
import com.example.freshstart.R
import com.example.freshstart.model.ErrorMessage
import com.example.freshstart.navigation.IAppNavigator
import com.example.freshstart.navigation.destination.NoArgsDestination
import com.example.freshstart.util.hasValidPasswordLength
import com.example.freshstart.util.isSameAs
import com.example.freshstart.util.isValidEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val appNavigator: IAppNavigator
) : BaseViewModel() {

    sealed class RegistrationInputModel {
        abstract val label: Int
        abstract val value: String

        data class Username(override val value: String = "") : RegistrationInputModel() {
            override val label: Int
                get() = R.string.username
        }

        data class Email(override val value: String = "") : RegistrationInputModel() {
            override val label: Int
                get() = R.string.email
        }

        data class Password(override val value: String = "") : RegistrationInputModel() {
            override val label: Int
                get() = R.string.password
        }

        data class RepeatPassword(override val value: String = "") : RegistrationInputModel() {
            override val label: Int
                get() = R.string.repeat_password
        }
    }

    data class RegistrationInputState(
        val inputs: List<RegistrationInputModel> = listOf(
            RegistrationInputModel.Email(),
            RegistrationInputModel.Username(),
            RegistrationInputModel.Password(),
            RegistrationInputModel.RepeatPassword()
        ),
        val isButtonEnabled: Boolean = false
    )

    private val _registrationInputState =
        MutableStateFlow(RegistrationInputState())
    val registrationInputState: StateFlow<RegistrationInputState>
        get() = _registrationInputState

    private var state = RegistrationInputState()
        set(value) {
            field = value
            _registrationInputState.value = value
        }

    private val _inputError = MutableStateFlow<ErrorMessage?>(null)
    val inputError: StateFlow<ErrorMessage?>
        get() = _inputError

    val tags = listOf<String>(
        "Sport",
        "Health",
        "Lifestyle",
        "Anxiety",
        "Habits",
        "Support",
        "Health",
        "Friendship",
        "Addiction"
    )

    fun onProceedToAboutStep() {
        viewModelScope.launch {
            appNavigator.navigateTo(NoArgsDestination.AboutRegistrationScreen())
        }
    }

    fun onStartJourneyClicked() {
        viewModelScope.launch {
            appNavigator.navigateTo(NoArgsDestination.HomeScreen())
        }
    }

    private fun onProceedToTagsStep() {
        viewModelScope.launch {
            appNavigator.navigateTo(NoArgsDestination.AboutRegistrationScreen())
        }
    }

    fun onValueChange(input: String, model: RegistrationInputModel) {
        val password =
            state.inputs.filterIsInstance<RegistrationInputModel.Password>().first().value
        val repeatPassword =
            state.inputs.filterIsInstance<RegistrationInputModel.RepeatPassword>().first().value
        val email = state.inputs.filterIsInstance<RegistrationInputModel.Email>().first().value
        val username =
            state.inputs.filterIsInstance<RegistrationInputModel.Username>().first().value

        when (model) {
            is RegistrationInputModel.Email -> state = RegistrationInputState(
                inputs = listOf(
                    RegistrationInputModel.Email(input),
                    RegistrationInputModel.Username(username),
                    RegistrationInputModel.Password(password),
                    RegistrationInputModel.RepeatPassword(repeatPassword)
                )
            )
            is RegistrationInputModel.Password -> state = RegistrationInputState(
                inputs = listOf(
                    RegistrationInputModel.Email(email),
                    RegistrationInputModel.Username(username),
                    RegistrationInputModel.Password(input),
                    RegistrationInputModel.RepeatPassword(repeatPassword)
                )
            )
            is RegistrationInputModel.RepeatPassword -> state = RegistrationInputState(
                inputs = listOf(
                    RegistrationInputModel.Email(email),
                    RegistrationInputModel.Username(username),
                    RegistrationInputModel.Password(password),
                    RegistrationInputModel.RepeatPassword(input)
                )
            )
            is RegistrationInputModel.Username -> state = RegistrationInputState(
                inputs = listOf(
                    RegistrationInputModel.Email(email),
                    RegistrationInputModel.Username(input),
                    RegistrationInputModel.Password(password),
                    RegistrationInputModel.RepeatPassword(repeatPassword)
                )
            )
        }
        isButtonEnabled()
    }

    private fun isButtonEnabled() {
        val password =
            state.inputs.filterIsInstance<RegistrationInputModel.Password>().first().value
        val repeatPassword =
            state.inputs.filterIsInstance<RegistrationInputModel.RepeatPassword>().first().value
        val email = state.inputs.filterIsInstance<RegistrationInputModel.Email>().first().value
        val username =
            state.inputs.filterIsInstance<RegistrationInputModel.Username>().first().value

        val isButtonEnabled =
            email.isValidEmail() && password.hasValidPasswordLength() && password.isSameAs(
                repeatPassword
            ) && username.isNotBlank()

        state = state.copy(isButtonEnabled = isButtonEnabled)
    }

    fun onRegistrationClicked(context: Activity) {
        firebaseRegister(context = context)
    }

    private fun firebaseRegister(context: Activity) {
        val password =
            state.inputs.filterIsInstance<RegistrationInputModel.Password>().first().value
        val email = state.inputs.filterIsInstance<RegistrationInputModel.Email>().first().value
        val username =
            state.inputs.filterIsInstance<RegistrationInputModel.Username>().first().value

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(context) { task ->
                if (task.isSuccessful) {
                    Timber.d("createUserWithEmail:success")
                    onProceedToTagsStep()
                } else {
                    Timber.w("createUserWithEmail:failure", task.exception)
                    _inputError.value = ErrorMessage(message = R.string.registration_fail)
                }
            }
    }
}