package com.programacionymas.drivingevaluation.drivers

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.programacionymas.drivingevaluation.R
import com.programacionymas.drivingevaluation.domain.Driver

@Composable
fun DriverCard(
    driver: Driver,
    onEvaluateDriver: (driver: Driver)->Unit = {}
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
            Text(
                text = driver.fullName
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.driver_code),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = driver.code,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.driver_identity_card),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = driver.identityCard,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
            }

            Button(
                onClick = {
                    onEvaluateDriver(driver)
                }
            ) {
                Text(text = stringResource(R.string.driver_action_evaluate))
            }
        }
    }
}

@Preview
@Composable
fun DriverCardPreview() {
    DriverCard(
        Driver(
            id = "111",
            code = "CEP-2022-5080",
            identityCard = "41084592",
            fullName = "DRIVER'S FULL NAME"
        )
    )
}