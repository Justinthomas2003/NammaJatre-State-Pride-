package com.example.nammajatrestatepride.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nammajatrestatepride.data.FirestoreRepository
import com.example.nammajatrestatepride.model.CulturalStory
import com.example.nammajatrestatepride.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoriesViewModel @Inject constructor(
    private val repository: FirestoreRepository
) : ViewModel() {

    private val _storiesState = MutableStateFlow<UiState<List<CulturalStory>>>(UiState.Loading)
    val storiesState: StateFlow<UiState<List<CulturalStory>>> = _storiesState.asStateFlow()

    init {
        fetchStories()
    }

    fun fetchStories() {
        viewModelScope.launch {
            repository.getCulturalStories().collect { state ->
                _storiesState.value = state
            }
        }
    }
}