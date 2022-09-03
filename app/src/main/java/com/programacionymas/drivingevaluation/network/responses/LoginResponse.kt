package com.programacionymas.drivingevaluation.network.responses

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("access_token")
    val accessToken: String
)