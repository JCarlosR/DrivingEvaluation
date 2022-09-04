package com.programacionymas.drivingevaluation.domain

data class Question (
    val id: Int,
    val title: String,
    val severity: String,
    var selectedOption: Int = Answer.NO_ANSWER
)