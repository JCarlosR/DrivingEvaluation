package com.programacionymas.drivingevaluation.drivers

import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
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
fun DriversScreen(viewModel: DriversViewModel?, onNavigationEvent: (DriversScreenEvent) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                topAppBarText = stringResource(R.string.drivers_list)
            )
        },
        content = { contentPadding ->
            if (viewModel == null) return@Scaffold

            val driversState = viewModel.driversData.observeAsState()
            val driversList = driversState.value ?: return@Scaffold

            DriverList(
                contentPadding = contentPadding,
                drivers = driversList,
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