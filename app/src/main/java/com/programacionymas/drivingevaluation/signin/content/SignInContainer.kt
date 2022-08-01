package com.programacionymas.drivingevaluation.signin.content

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.programacionymas.drivingevaluation.R

@Composable
fun SignInContainer(
    onSignedUp: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    content: @Composable () -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        item {
            Spacer(modifier = Modifier.height(44.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                content()
            }

            Spacer(modifier = Modifier.height(16.dp))

            OrSignUp(
                onSignedUp = onSignedUp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
        }
    }
}

@Composable
fun OrSignUp(
    onSignedUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = stringResource(id = R.string.or),
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.paddingFromBaseline(top = 25.dp)
                )
            }
        }
        OutlinedButton(
            onClick = onSignedUp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 24.dp)
        ) {
            Text(text = stringResource(id = R.string.sign_up))
        }
    }
}

@Preview
@Composable
fun SignInContainerPreview() {
    SignInContainer(
        onSignedUp = {}
    ) {
        Text(
            text = "Content goes here",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}