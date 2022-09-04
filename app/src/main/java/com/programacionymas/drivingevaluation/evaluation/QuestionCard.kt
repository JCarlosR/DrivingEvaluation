package com.programacionymas.drivingevaluation.evaluation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.programacionymas.drivingevaluation.R
import com.programacionymas.drivingevaluation.domain.Answer
import com.programacionymas.drivingevaluation.domain.Question

@Composable
fun QuestionCard(
    question: Question,
    answer: Int = Answer.NO_ANSWER,
    onOptionChanged: (newOptionSelected: Int)->(Unit) = {}
) {

    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "#${question.id}",
                    color = MaterialTheme.colors.primaryVariant
                )
                Text(
                    text = question.severity,
                    fontStyle = FontStyle.Italic
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = question.title,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                RadioButton(
                    selected = answer == Answer.DOES_COMPLY,
                    onClick = {
                        onOptionChanged(Answer.DOES_COMPLY)
                    }
                )
                Text(text = stringResource(R.string.competent_yes))

                Spacer(modifier = Modifier.width(12.dp))

                RadioButton(
                    selected = answer == Answer.DOES_NOT_COMPLY,
                    onClick = {
                        onOptionChanged(Answer.DOES_NOT_COMPLY)
                    }
                )
                Text(text = stringResource(R.string.competent_no))
            }

        }
    }
}

@Preview
@Composable
fun QuestionCardPreview() {
    QuestionCard(
        Question(
            id = 3,
            title = "Is the driver able to _?",
            severity = "Very important"
        )
    )
}