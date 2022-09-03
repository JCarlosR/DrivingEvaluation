package com.programacionymas.drivingevaluation.drivers

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.programacionymas.drivingevaluation.R
import com.programacionymas.drivingevaluation.data.DriverRepository
import com.programacionymas.drivingevaluation.domain.Driver
import com.programacionymas.drivingevaluation.signin.top.TopAppBar
import com.programacionymas.drivingevaluation.theme.DrivingEvaluationTheme


sealed class DriversScreenEvent {
    data class Evaluate(val driver: Driver) : DriversScreenEvent()
}

@Composable
fun DriversScreen(viewModel: DriversViewModel?, onNavigationEvent: (DriversScreenEvent) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                topAppBarText = stringResource(R.string.drivers_list)
            )
        },
        content = { contentPadding ->
            Column {
                TopSearch()

                val driversState = viewModel?.driversData?.observeAsState()
                val driversList = driversState?.value

                if (driversList != null) {
                    DriverList(
                        contentPadding = contentPadding,
                        drivers = driversList,
                        onEvaluateDriver = { driver ->
                            onNavigationEvent(DriversScreenEvent.Evaluate(driver))
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun TopSearch() {
    val dateText = remember { mutableStateOf("22/08/2022") }

    Row(
        modifier = Modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = dateText.value,
            label = {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = stringResource(id = R.string.input_date_label),
                        style = MaterialTheme.typography.body2
                    )
                }
            },
            onValueChange = {
                dateText.value = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
            textStyle = MaterialTheme.typography.body2
        )

        Spacer(modifier = Modifier.width(10.dp))

        Button(
            onClick = {
                DriverRepository.fetchDrivers(dateText.value)
            }
        ) {
            Text(
                text = stringResource(id = R.string.search_action)
            )
        }
    }
}

@Preview(name = "Sign in light theme")
@Composable
fun DriverScreenPreview() {
    DrivingEvaluationTheme {
        DriversScreen(viewModel = null) {}
    }
}

@Preview(name = "Sign in dark theme")
@Composable
fun SignInPreviewDark() {
    DrivingEvaluationTheme(darkTheme = true) {
        DriversScreen(viewModel = null) {}
    }
}