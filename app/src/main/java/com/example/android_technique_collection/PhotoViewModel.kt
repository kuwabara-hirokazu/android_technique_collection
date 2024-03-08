package com.example.android_technique_collection

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
) : ViewModel() {
    fun getData() = "viewmodel"

}
