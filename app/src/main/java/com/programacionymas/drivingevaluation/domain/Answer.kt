package com.programacionymas.drivingevaluation.domain

import com.google.gson.annotations.SerializedName

data class Answer (
    @SerializedName("question_id")
    val questionId: Int,

    @SerializedName("competent")
    val competent: Int
)