package com.programacionymas.drivingevaluation.signin.error

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.programacionymas.drivingevaluation.R


@Composable
fun ErrorSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {}
) {
    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = { data ->
            StyledSnackbar(data, onDismiss)
        },
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Bottom)
    )
}


@Composable
fun StyledSnackbar(data: SnackbarData, onDismiss: () -> Unit) {
    Snackbar(
        modifier = Modifier.padding(16.dp),
        content = {
            Text(
                text = data.message,
                style = MaterialTheme.typography.body2
            )
        },
        action = {
            data.actionLabel?.let {
                TextButton(onClick = onDismiss) {
                    Text(
                        text = stringResource(id = R.string.dismiss),
                        color = MaterialTheme.colors.secondaryVariant // snackbarAction
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun ErrorSnackbarPreview() {
    val snackbarData = object : SnackbarData {
        override val actionLabel = "Ok"
        override val duration: SnackbarDuration = SnackbarDuration.Indefinite
        override val message = "A simple Snackbar message"

        override fun dismiss() {}
        override fun performAction() {}
    }

    StyledSnackbar(
        data = snackbarData,
        onDismiss = {}
    )
}