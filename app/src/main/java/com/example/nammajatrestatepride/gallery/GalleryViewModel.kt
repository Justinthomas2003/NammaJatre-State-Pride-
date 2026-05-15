package com.example.nammajatrestatepride.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nammajatrestatepride.data.FirestoreRepository
import com.example.nammajatrestatepride.model.GalleryPhoto
import com.example.nammajatrestatepride.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val repository: FirestoreRepository) : ViewModel() {
    private val _photosState = MutableStateFlow<UiState<List<GalleryPhoto>>>(UiState.Loading)
    val photosState: StateFlow<UiState<List<GalleryPhoto>>> = _photosState

    init {
        loadPhotos()
    }

    private fun loadPhotos() {
        viewModelScope.launch {
            repository.getGalleryPhotos().collect { _photosState.value = it }
        }
    }
}