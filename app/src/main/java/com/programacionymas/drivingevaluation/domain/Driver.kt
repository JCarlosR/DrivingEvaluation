package com.programacionymas.drivingevaluation.domain

import com.google.gson.annotations.SerializedName

data class Driver (
    val id: String,
    val code: String,

    @SerializedName("identity_card")
    val identityCard: String,

    @SerializedName("full_name")
    val fullName: String
)