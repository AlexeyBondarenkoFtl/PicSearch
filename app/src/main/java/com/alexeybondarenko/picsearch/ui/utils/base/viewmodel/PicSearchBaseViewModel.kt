package com.alexeybondarenko.picsearch.ui.utils.base.viewmodel

import androidx.lifecycle.ViewModel

abstract class PicSearchBaseViewModel : ViewModel() {
    abstract val tag: String

    open fun handleError(e: Exception) {
        e.printStackTrace()
    }
}