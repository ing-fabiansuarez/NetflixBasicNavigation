package me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
        private set
    
    var password by mutableStateOf("")
        private set
    
    var passwordVisible by mutableStateOf(false)
        private set
    
    var rememberMe by mutableStateOf(false)
        private set

    fun onEmailChange(newValue: String) {
        email = newValue
    }

    fun onPasswordChange(newValue: String) {
        password = newValue
    }

    fun togglePasswordVisibility() {
        passwordVisible = !passwordVisible
    }

    fun onRememberMeChange(newValue: Boolean) {
        rememberMe = newValue
    }
}
