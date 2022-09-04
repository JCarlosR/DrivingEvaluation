package com.programacionymas.drivingevaluation.evaluation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.programacionymas.drivingevaluation.R
import com.programacionymas.drivingevaluation.data.EvaluationRepository
import com.programacionymas.drivingevaluation.data.QuestionRepository
import com.programacionymas.drivingevaluation.domain.Answer
import com.programacionymas.drivingevaluation.domain.Question
import com.programacionymas.drivingevaluation.signin.top.TopAppBar
import com.programacionymas.drivingevaluation.theme.DrivingEvaluationTheme

sealed class TestScreenEvent {
    data class SubmitTest(val answers: List<Answer>) : TestScreenEvent()
    object NavigateBack : TestScreenEvent()
}

@Composable
fun TestScreen(onNavigationEvent: (TestScreenEvent) -> Unit) {
    val questions = remember { mutableStateListOf<Question>() }
    questions.addAll(QuestionRepository.questions)

    Scaffold(
        topBar = {
            TopAppBar(
                topAppBarText = stringResource(
                    R.string.driver_test_evaluation_title,
                    EvaluationRepository.driver?.fullName ?: "Driver"
                )
            )
        },
        content = { contentPadding ->
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement= Arrangement.SpaceBetween
            ) {
                LazyColumn(
                    contentPadding = contentPadding,
                    modifier = Modifier.weight(1f)
                ) {
                    itemsIndexed(questions) { index, question ->
                        QuestionCard(
                            question = question,
                            answer = question.selectedOption
                        ) { newOption ->
                            val updatedQuestion = question.copy()
                            updatedQuestion.selectedOption = newOption

                            // Post changes to the list
                            questions[index] = updatedQuestion
                        }
                    }
                }

                Button(
                    onClick = {
                        val testAnswers = questions.map { q -> Answer.fromQuestion(q) }
                        onNavigationEvent(TestScreenEvent.SubmitTest(testAnswers))
                    }
                ) {
                    Text(text = stringResource(R.string.submit_test))
                }
            }
        }
    )
}

@Preview(name = "Sign in light theme")
@Composable
fun DriverScreenPreview() {
    QuestionRepository.questions = QuestionRepository.getDummyData()

    DrivingEvaluationTheme {
        TestScreen {}
    }
}

@Preview(name = "Sign in dark theme")
@Composable
fun SignInPreviewDark() {
    QuestionRepository.questions = QuestionRepository.getDummyData()

    DrivingEvaluationTheme(darkTheme = true) {
        TestScreen {}
    }
}