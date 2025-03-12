package com.alexeybondarenko.picsearch.ui.imagesearch

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ImageSearchScreen(
    uiState: ImageSearchUiState,
    onIncreaseClick: () -> Unit,
    onGetFirstPostClick: () -> Unit,
    onLoadPhotoClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            ImageSearchUiState.ImageSearchLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is ImageSearchUiState.ImageSearchLoaded -> {
                Column(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(
                        text = uiState.counter.toString(),
                    )

                    Button(
                        onClick = onIncreaseClick,
                    ) {
                        Text("+1")
                    }

                    Button(
                        onClick = onGetFirstPostClick,
                    ) {
                        Text("GetFirstPost")
                    }

                    Button(
                        onClick = onLoadPhotoClick,
                    ) {
                        Text("load photo")
                    }
                }
            }

            is ImageSearchUiState.ImageSearchLoadingError -> {
                //todo ???
                val errorText = if (uiState.errorMessages.isEmpty()) {
                    "Empty error"
                } else {
                    uiState.errorMessages.first()
                }

                Text(
                    text = errorText,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ImageSearchScreenPreview() {
    ImageSearchScreen(
        uiState = ImageSearchUiState.ImageSearchLoaded(
            counter = 1,
            errorMessages = listOf()
        ),
        onIncreaseClick = {},
        onGetFirstPostClick = {},
        onLoadPhotoClick = {},
    )
}