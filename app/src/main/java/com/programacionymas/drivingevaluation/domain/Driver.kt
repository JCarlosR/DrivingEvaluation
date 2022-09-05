package com.programacionymas.drivingevaluation.domain

import com.google.gson.annotations.SerializedName

data class Driver (
    val id: String,
    val code: String,

    @SerializedName("identity_card")
    val identityCard: String,

    @SerializedName("full_name")
    val fullName: String,

    var evaluated: String = EVALUATED_NOT_YET
) {
    fun alreadyEvaluated(): Boolean {
        return evaluated == EVALUATED_ALREADY
    }

    companion object {
        const val EVALUATED_ALREADY = "1"
        const val EVALUATED_NOT_YET = "0"
    }
}