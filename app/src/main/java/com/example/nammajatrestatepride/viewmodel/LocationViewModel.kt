package com.example.nammajatrestatepride.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nammajatrestatepride.data.FirestoreRepository
import com.example.nammajatrestatepride.model.Location
import com.example.nammajatrestatepride.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repository: FirestoreRepository
) : ViewModel() {

    private val _locationsState = MutableStateFlow<UiState<List<Location>>>(UiState.Loading)
    val locationsState: StateFlow<UiState<List<Location>>> = _locationsState.asStateFlow()

    fun getLocationsByType(type: String) {
        viewModelScope.launch {
            repository.getLocationsByType(type).collect { state ->
                _locationsState.value = state
            }
        }
    }
}