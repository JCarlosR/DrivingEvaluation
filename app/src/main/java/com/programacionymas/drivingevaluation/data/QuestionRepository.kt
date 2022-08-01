package com.programacionymas.drivingevaluation.data

import com.programacionymas.drivingevaluation.domain.Question

object QuestionRepository {
    fun getDummyData(): List<Question> {
        return listOf(
            Question(1, "Is the driver able to do ABC?", "Very important (4 pts)"),
            Question(2, "Is the driver able to do CYZ?", "Less important (2 pts)"),
        )
    }
}