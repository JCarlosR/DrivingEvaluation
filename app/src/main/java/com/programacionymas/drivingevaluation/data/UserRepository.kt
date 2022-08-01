package com.programacionymas.drivingevaluation.data

import androidx.compose.runtime.Immutable

/**
 * Drivers are not users, instead authorized staff will input their responses to the test.
 */
sealed class User {
    @Immutable
    data class LoggedInUser(val email: String) : User()
    object NoUserLoggedIn : User()
}

/**
 * Repository that holds the logged in staff user.
 *
 * This class will also handle the communication with the backend for sign in.
 */
object UserRepository {

    private var _user: User = User.NoUserLoggedIn

    val user: User
        get() = _user

    @Suppress("UNUSED_PARAMETER")
    fun signIn(email: String, password: String) {
        _user = User.LoggedInUser(email)
    }

}