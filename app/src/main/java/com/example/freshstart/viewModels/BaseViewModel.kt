package com.example.freshstart.viewModels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

open class BaseViewModel : ViewModel() {
    val auth: FirebaseAuth = Firebase.auth

    init {
        auth.firebaseAuthSettings.setAppVerificationDisabledForTesting(true)
    }
}