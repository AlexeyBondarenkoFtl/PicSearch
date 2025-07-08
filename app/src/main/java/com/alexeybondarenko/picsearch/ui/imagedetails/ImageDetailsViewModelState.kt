//package com.alexeybondarenko.picsearch.ui.imagedetails
//
//import com.alexeybondarenko.picsearch.ui.imagedetails.data.ImageDetailedCard
//import com.alexeybondarenko.picsearch.ui.utils.base.PicSearchSimpleError
//import com.alexeybondarenko.picsearch.ui.utils.base.UiState
//import com.alexeybondarenko.picsearch.ui.utils.base.ViewModelState
//
//data class ImageDetailsViewModelState(
//    override val errorMessage: PicSearchSimpleError? = null,
//    override val isLoading: Boolean = false,
//    override val data: ImageDetailedCard? = null,
//    override val operationErrorMessage: PicSearchSimpleError?
//) : ViewModelState<ImageDetailedCard, PicSearchSimpleError> {
//    override fun toUiState(): UiState<ImageDetailedCard, PicSearchSimpleError> = when {
//        isLoading -> UiState.Loading
//        !isLoading && errorMessage != null -> UiState.LoadingError(errorMessage)
//        else -> UiState.Loaded(data)
//    }
//}
