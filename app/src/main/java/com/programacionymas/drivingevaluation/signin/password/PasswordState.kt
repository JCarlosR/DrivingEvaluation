package com.programacionymas.drivingevaluation.signin.password

import com.programacionymas.drivingevaluation.signin.text.TextFieldState

class PasswordState :
    TextFieldState(validator = ::isPasswordValid, errorFor = ::passwordValidationError)

private fun isPasswordValid(password: String): Boolean {
    return password.length > 3
}

@Suppress("UNUSED_PARAMETER")
private fun passwordValidationError(password: String): String {
    return "Invalid password"
}
