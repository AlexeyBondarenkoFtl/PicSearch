//package com.alexeybondarenko.picsearch.ui.imagedetails
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.alexeybondarenko.domain.model.ImageEntity
//import com.alexeybondarenko.domain.usecase.imagestorageservice.DeleteImageByIdFromStorageUseCase
//import com.alexeybondarenko.domain.usecase.imagestorageservice.SaveImageToStorageUseCase
//import com.alexeybondarenko.domain.usecase.photoservice.GetPhotoByIdUseCase
//import com.alexeybondarenko.picsearch.ui.imagedetails.data.ImageDetailedCard
//import com.alexeybondarenko.picsearch.ui.utils.ImageUtils.calculateAspectRatio
//import com.alexeybondarenko.picsearch.ui.utils.base.PicSearchSimpleError
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.SharingStarted
//import kotlinx.coroutines.flow.map
//import kotlinx.coroutines.flow.stateIn
//import kotlinx.coroutines.flow.update
//import kotlinx.coroutines.launch
//
//class ImageDetailsViewModel(
//    private val getPhotoByIdUseCase: GetPhotoByIdUseCase,
//    private val saveImageToStorageUseCase: SaveImageToStorageUseCase,
//    private val deleteImageByIdFromStorageUseCase: DeleteImageByIdFromStorageUseCase,
//) : ViewModel() {
//    private val viewModelState = MutableStateFlow(
//        ImageDetailsViewModelState()
//    )
//
//    val uiState = viewModelState
//        .map(ImageDetailsViewModelState::toUiState)
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000),
//            initialValue = viewModelState.value.toUiState()
//        )
//
//    private var id: String? = null
//
//    fun setId(id: String) {
//        this.id = id
//    }
//
//    fun loadPhoto() {
//        viewModelScope.launch {
//            try {
//                viewModelState.update { it.copy(isLoading = true) }
//
//                loadPhotoByIdInternal()?.let { imageEntity ->
//                    viewModelState.update {
//                        it.copy(
//                            data = mapImageEntityToImageDetailedCard(imageEntity)
//                        )
//                    }
//                }
//            } catch (e: Exception) {
//                handleException(e)
//            } finally {
//                viewModelState.update {
//                    it.copy(isLoading = false)
//                }
//            }
//        }
//    }
//
//    fun savePhoto() {
//        viewModelScope.launch {
//            try {
//                savePhotoInternal()
//            } catch (e: Exception) {
//                handleException(e)
//            }
//        }
//    }
//
//    fun deletePhoto() {
//        viewModelScope.launch {
//            try {
//                deletePhotoInternal()
//            } catch (e: Exception) {
//                handleException(e)
//            }
//        }
//    }
//
//    private suspend fun loadPhotoByIdInternal(): ImageEntity? {
//        val currentId = id ?: return null
//        return getPhotoByIdUseCase.execute(currentId)
//    }
//
//    private suspend fun savePhotoInternal() {
//        loadPhotoByIdInternal()?.let {
//            saveImageToStorageUseCase.execute(it)
//        }
//    }
//
//    private suspend fun deletePhotoInternal() {
//        val currentId = id ?: return
//
//        deleteImageByIdFromStorageUseCase.execute(currentId)
//    }
//
//    private fun mapImageEntityToImageDetailedCard(from: ImageEntity): ImageDetailedCard {
//        return ImageDetailedCard(
//            id = from.id,
//            url = from.urls?.full,
//            author = from.user?.name,
//            aspectRatio = calculateAspectRatio(
//                width = from.width,
//                height = from.height,
//            )
//        )
//    }
//
//    private fun handleException(e: Exception) {
//        viewModelState.update {
//            it.copy(
//                errorMessage = PicSearchSimpleError(e.message)
//            )
//        }
//    }
//
//    companion object {
//        const val TAG = "ImageDetailsViewModel"
//    }
//}