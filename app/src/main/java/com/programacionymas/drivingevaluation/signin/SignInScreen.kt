package com.programacionymas.drivingevaluation.signin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.programacionymas.drivingevaluation.R
import com.programacionymas.drivingevaluation.signin.content.SignInContainer
import com.programacionymas.drivingevaluation.signin.content.SignInForm
import com.programacionymas.drivingevaluation.signin.error.ErrorSnackbar
import com.programacionymas.drivingevaluation.signin.top.TopAppBar
import com.programacionymas.drivingevaluation.theme.DrivingEvaluationTheme

sealed class SignInEvent {
    data class SignIn(val email: String, val password: String) : SignInEvent()
    object SignUp : SignInEvent()
}

@Composable
fun SignIn(onNavigationEvent: (SignInEvent) -> Unit) {

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            TopAppBar(
                topAppBarText = stringResource(id = R.string.sign_in)
            )
        },
        content = { contentPadding ->
            SignInContainer(
                contentPadding = contentPadding,
                onSignedUp = { onNavigationEvent(SignInEvent.SignUp) }
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    SignInForm(
                        onSignInSubmitted = { email, password ->
                            onNavigationEvent(SignInEvent.SignIn(email, password))
                        }
                    )
                }
            }
        }
    )

    Box(modifier = Modifier.fillMaxSize()) {
        ErrorSnackbar(
            snackbarHostState = snackbarHostState,
            onDismiss = { snackbarHostState.currentSnackbarData?.dismiss() },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview(name = "Sign in light theme")
@Composable
fun SignInPreview() {
    DrivingEvaluationTheme {
        SignIn {}
    }
}

@Preview(name = "Sign in dark theme")
@Composable
fun SignInPreviewDark() {
    DrivingEvaluationTheme(darkTheme = true) {
        SignIn {}
    }
}