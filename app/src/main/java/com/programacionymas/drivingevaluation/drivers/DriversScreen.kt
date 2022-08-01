package com.programacionymas.drivingevaluation.drivers

import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.programacionymas.drivingevaluation.R
import com.programacionymas.drivingevaluation.data.DriverRepository
import com.programacionymas.drivingevaluation.domain.Driver
import com.programacionymas.drivingevaluation.signin.top.TopAppBar
import com.programacionymas.drivingevaluation.theme.DrivingEvaluationTheme

sealed class DriversScreenEvent {
    data class Evaluate(val driver: Driver) : DriversScreenEvent()
}

@Composable
fun DriversScreen(onNavigationEvent: (DriversScreenEvent) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                topAppBarText = stringResource(R.string.drivers_list)
            )
        },
        content = { contentPadding ->
            DriverList(
                contentPadding = contentPadding,
                drivers = DriverRepository.getDummyData(),
                onEvaluateDriver = { driver ->
                    onNavigationEvent(DriversScreenEvent.Evaluate(driver))
                }
            )
        }
    )
}

@Preview(name = "Sign in light theme")
@Composable
fun DriverScreenPreview() {
    DrivingEvaluationTheme {
        DriversScreen {}
    }
}

@Preview(name = "Sign in dark theme")
@Composable
fun SignInPreviewDark() {
    DrivingEvaluationTheme(darkTheme = true) {
        DriversScreen {}
    }
}