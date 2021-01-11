package com.example.chatapp

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.setErrorMessage(errorMessage: String) {
    error = errorMessage
}

fun TextInputLayout.clearError() {
    error = null
    isErrorEnabled = false
}