package com.programacionymas.drivingevaluation.domain

import com.google.gson.annotations.SerializedName

class Answer (
    @SerializedName("question_id")
    val questionId: Int,

    @SerializedName("competent")
    var competent: Int
) {
    override fun toString(): String {
        return "(questionId=$questionId, competent=$competent)"
    }

    companion object {
        const val NO_ANSWER = 0
        const val DOES_COMPLY = 1
        const val DOES_NOT_COMPLY = 2

        fun fromQuestion(question: Question): Answer {
            return Answer(questionId = question.id, competent = question.selectedOption)
        }
    }
}