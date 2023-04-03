package com.example.freshstart.viewModels

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freshstart.R
import com.example.freshstart.model.ErrorMessage
import com.example.freshstart.navigation.IAppNavigator
import com.example.freshstart.util.hasValidPasswordLength
import com.example.freshstart.util.isValidEmail
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val appNavigator: IAppNavigator
) : ViewModel() {

    sealed class LoginInputModel {
        abstract val label: Int
        abstract val value: String

        data class Email(override val value: String = "") : LoginInputModel() {
            override val label: Int
                get() = R.string.email
        }

        data class Password(override val value: String = "") : LoginInputModel() {
            override val label: Int
                get() = R.string.password
        }
    }

    data class LoginInputState(
        val inputs: List<LoginInputModel> = listOf(
            LoginInputModel.Email(),
            LoginInputModel.Password()
        ),
        val isButtonEnabled: Boolean = false
    )

    private val _loginInputState =
        MutableStateFlow(LoginInputState())
    val loginInputState: StateFlow<LoginInputState>
        get() = _loginInputState

    private var state = LoginInputState()
        set(value) {
            field = value
            _loginInputState.value = value
        }

    private val _inputError = MutableStateFlow<ErrorMessage?>(null)
    val inputError: StateFlow<ErrorMessage?>
        get() = _inputError

    private val auth: FirebaseAuth = Firebase.auth

    fun onBackClicked() {
        viewModelScope.launch {
            appNavigator.navigateBack()
        }
    }

    fun onValueChange(input: String, model: LoginInputModel) {
        val password = state.inputs.filterIsInstance<LoginInputModel.Password>().first().value
        val email = state.inputs.filterIsInstance<LoginInputModel.Email>().first().value
        when (model) {
            is LoginInputModel.Password -> {
                state = LoginInputState(
                    inputs = listOf(
                        LoginInputModel.Email(email),
                        LoginInputModel.Password(input)
                    )
                )
            }
            is LoginInputModel.Email -> {
                state = LoginInputState(
                    inputs = listOf(
                        LoginInputModel.Email(input),
                        LoginInputModel.Password(password)
                    )
                )
            }
        }
        isButtonEnabled()
    }

    private fun isButtonEnabled() {
        val password = state.inputs.filterIsInstance<LoginInputModel.Password>().first().value
        val email = state.inputs.filterIsInstance<LoginInputModel.Email>().first().value
        val isButtonEnabled = email.isValidEmail() && password.hasValidPasswordLength()
        state = state.copy(isButtonEnabled = isButtonEnabled)
    }

    fun onLoginClicked(context: Activity) {
        firebaseLogin(context = context)
    }

    private fun firebaseLogin(context: Activity) {
        val password = state.inputs.filterIsInstance<LoginInputModel.Password>().first().value
        val email = state.inputs.filterIsInstance<LoginInputModel.Email>().first().value
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(context) { task ->
                if (task.isSuccessful) {
                    Timber.d("Firebase sign in success")
                    viewModelScope.launch {
                        // TODO: Navigate to home screen
                    }
                    _inputError.value = null
                } else {
                    Timber.d("Firebase sign in fail")
                    _inputError.value = ErrorMessage(message = R.string.login_fail)
                }
            }
    }
}