package com.programacionymas.drivingevaluation.evaluation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.programacionymas.drivingevaluation.data.QuestionRepository
import com.programacionymas.drivingevaluation.domain.Question

@Composable
fun QuestionList(
    contentPadding: PaddingValues = PaddingValues(),
    questions: List<Question>
) {
    LazyColumn(
        contentPadding = contentPadding
    ) {
        items(questions) { question ->
           QuestionCard(
               question = question
           )
        }
    }
}

@Preview
@Composable
fun QuestionListPreview() {
    QuestionList(questions = QuestionRepository.getDummyData())
}