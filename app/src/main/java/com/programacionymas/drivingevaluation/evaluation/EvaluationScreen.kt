package com.programacionymas.drivingevaluation.evaluation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.programacionymas.drivingevaluation.R
import com.programacionymas.drivingevaluation.data.EvaluationRepository
import com.programacionymas.drivingevaluation.data.QuestionRepository
import com.programacionymas.drivingevaluation.domain.Answer
import com.programacionymas.drivingevaluation.signin.top.TopAppBar
import com.programacionymas.drivingevaluation.theme.DrivingEvaluationTheme

sealed class TestScreenEvent {
    data class SubmitTest(val answers: List<Answer>) : TestScreenEvent()
    object NavigateBack : TestScreenEvent()
}

@Composable
fun TestScreen(onNavigationEvent: (TestScreenEvent) -> Unit) {
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
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                QuestionList(
                    contentPadding = contentPadding,
                    questions = QuestionRepository.questions
                )

                Button(
                    onClick = {
                        onNavigationEvent(TestScreenEvent.SubmitTest(listOf()))
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