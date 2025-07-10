//package com.alexeybondarenko.picsearch.ui.imagedetails
//
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.wrapContentHeight
//import androidx.compose.foundation.layout.wrapContentSize
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Card
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.window.Dialog
//import androidx.compose.ui.window.DialogProperties
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
//import coil3.compose.AsyncImage
//import com.alexeybondarenko.picsearch.ui.imagedetails.data.ImageDetailedCard
//import org.koin.androidx.compose.koinViewModel
//
//@Composable
//fun ImageDetailsRoute(
//    id: String,
//    viewModel: ImageDetailsViewModel = koinViewModel()
//) {
//    viewModel.setId(id)
//    viewModel.loadPhoto()
//
//    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//
//    ImageDetailsScreen(
//        uiState = uiState,
//        onSaveClick = viewModel::savePhoto,
//        onDeleteClick = viewModel::deletePhoto,
//        onDismissRequest = {}
//    )
//}
//
//@Composable
//fun ImageDetailsScreen(
//    uiState: ImageDetailsUiState,
//    onSaveClick: () -> Unit,
//    onDeleteClick: () -> Unit,
//    onDismissRequest: () -> Unit,
//) {
//    Dialog(
//        onDismissRequest = onDismissRequest,
//        properties = DialogProperties(
//            usePlatformDefaultWidth = false,
//        )
//    ) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
////                .fillMaxHeight(.5f)
//                .padding(16.dp),
//            shape = RoundedCornerShape(16.dp)
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//            ) {
//                when (uiState) {
//                    ImageDetailsUiState.ImageDetailsLoading -> {
//                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//                    }
//
//                    is ImageDetailsUiState.ImageDetailsLoaded -> {
//                        Column {
//                            AsyncImage(
//                                model = uiState.imageInfo?.url,
//                                contentDescription = null,
//                                modifier = Modifier.fillMaxSize(),
//                                contentScale = ContentScale.FillBounds
//                            )
//
//                            Text(
//                                text = "This is a minimal dialog",
//                                modifier = Modifier
//                                    .wrapContentSize(Alignment.Center),
//                                textAlign = TextAlign.Center,
//                            )
//                        }
//                    }
//
//                    is ImageDetailsUiState.ImageDetailsLoadingError -> {
//                        Text(
//                            text = "error: ${uiState.errorMessage?.message}",
//                            modifier = Modifier.align(Alignment.Center)
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Preview(showSystemUi = true)
//@Composable
//private fun ImageDetailsScreenPreview() {
//    ImageDetailsScreen(
//        uiState = ImageDetailsUiState.ImageDetailsLoaded(
//            imageInfo = ImageDetailedCard(
//                id = "1",
//                url = "asd",
//                author = "author",
//                aspectRatio = .5f,
//            )
//        ),
//        onSaveClick = {},
//        onDeleteClick = {},
//        onDismissRequest = {},
//    )
//}