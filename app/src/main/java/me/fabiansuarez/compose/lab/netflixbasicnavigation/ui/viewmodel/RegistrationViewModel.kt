package me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegistrationViewModel : ViewModel() {
    // Step 1: Account Creation
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var confirmPassword by mutableStateOf("")
        private set
    var passwordVisible by mutableStateOf(false)
        private set
    var confirmPasswordVisible by mutableStateOf(false)
        private set
    var promoEmails by mutableStateOf(false)
        private set

    // Step 2: Plan Selection
    var selectedPlanId by mutableStateOf(3)
        private set

    // Step 3: Payment
    var cardNumber by mutableStateOf("")
        private set
    var cardName by mutableStateOf("")
        private set
    var cardExpiry by mutableStateOf("")
        private set
    var cardCvv by mutableStateOf("")
        private set
    var saveCard by mutableStateOf(true)
        private set

    // Step 1 Actions
    fun onEmailChange(newValue: String) { email = newValue }
    fun onPasswordChange(newValue: String) { password = newValue }
    fun onConfirmPasswordChange(newValue: String) { confirmPassword = newValue }
    fun togglePasswordVisibility() { passwordVisible = !passwordVisible }
    fun toggleConfirmPasswordVisibility() { confirmPasswordVisible = !confirmPasswordVisible }
    fun onPromoEmailsChange(newValue: Boolean) { promoEmails = newValue }

    // Step 2 Actions
    fun onPlanSelect(planId: Int) { selectedPlanId = planId }

    // Step 3 Actions
    fun onCardNumberChange(newValue: String) { cardNumber = newValue }
    fun onCardNameChange(newValue: String) { cardName = newValue }
    fun onCardExpiryChange(newValue: String) { cardExpiry = newValue }
    fun onCardCvvChange(newValue: String) { cardCvv = newValue }
    fun onSaveCardChange(newValue: Boolean) { saveCard = newValue }
}
