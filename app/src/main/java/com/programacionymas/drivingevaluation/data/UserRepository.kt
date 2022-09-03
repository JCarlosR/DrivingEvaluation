package com.programacionymas.drivingevaluation.data

import androidx.compose.runtime.Immutable
import com.programacionymas.drivingevaluation.network.DriverApiAdapter
import com.programacionymas.drivingevaluation.network.requests.LoginRequest
import com.programacionymas.drivingevaluation.network.responses.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Drivers are not users, instead authorized staff will input their responses to the test.
 */
sealed class User {
    @Immutable
    data class LoggedInUser(val email: String, val accessToken: String) : User()
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
    fun signIn(email: String, password: String, callback: ()->(Unit)) {
        DriverApiAdapter.getApiService().postLogin(
            LoginRequest(email, password)
        ).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _user = User.LoggedInUser(email, it.accessToken)
                        callback()
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
            }
        })
    }

}