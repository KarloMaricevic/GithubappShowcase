package com.example.githubapp.core.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.githubapp.designSystem.theme.GithubAppTheme

@Composable
fun SimpleDialog(model: SimpleDialogUI) {
    Dialog(onDismissRequest = {}) {
        Card {
            Column(
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = model.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp),
                )
                Row(Modifier.align(Alignment.End)) {
                    if (!model.dismissText.isNullOrBlank()) {
                        Button(
                            onClick = { model.onDismiss?.invoke() },
                            modifier = Modifier.padding(end = 16.dp),
                        ) {
                            Text(
                                text = model.dismissText,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }
                    Button(onClick = { model.onConfirmation.invoke() }) {
                        Text(
                            text = model.confirmationText,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SimpleDialogPreview() {
    GithubAppTheme {
        SimpleDialog(
            SimpleDialogUI(
                title = "Title",
                confirmationText = "Confirm",
                onConfirmation = {},
                dismissText = "Cancel",
                onDismiss = {},
            )
        )
    }
}

data class SimpleDialogUI(
    val title: String,
    val confirmationText: String,
    val onConfirmation: () -> Unit,
    val dismissText: String? = null,
    val onDismiss: (() -> Unit)? = null
)
