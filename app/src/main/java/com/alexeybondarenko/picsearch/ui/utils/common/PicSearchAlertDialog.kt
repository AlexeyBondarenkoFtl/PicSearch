package com.alexeybondarenko.picsearch.ui.utils.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Composable
fun PicSearchAlertDialog(
    modifier: Modifier = Modifier,
    error: PicSearchErrorWithAction,
) {
    AlertDialog(
        onDismissRequest = error.confirmAction,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        title = {
            Text(text = "Error")
        },
        text = {
            Text(text = error.message ?: "blank error message")
        },
        confirmButton = {
            Button(
                onClick = error.confirmAction
            ) {
                Text("OK")
            }
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        )
    )
}