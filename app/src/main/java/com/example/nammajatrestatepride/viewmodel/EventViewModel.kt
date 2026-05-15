package com.example.nammajatrestatepride.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nammajatrestatepride.data.FirestoreRepository
import com.example.nammajatrestatepride.model.Event
import com.example.nammajatrestatepride.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val repository: FirestoreRepository
) : ViewModel() {

    private val _eventState = MutableStateFlow<UiState<List<Event>>>(UiState.Loading)
    val eventState: StateFlow<UiState<List<Event>>> = _eventState.asStateFlow()

    private val _currentEvent = MutableStateFlow<Event?>(null)
    val currentEvent: StateFlow<Event?> = _currentEvent.asStateFlow()

    init {
        fetchEvents()
    }

    fun fetchEvents() {
        viewModelScope.launch {
            repository.getEvents().collect { state ->
                _eventState.value = state
                // Find currently ongoing event
                if (state is UiState.Success) {
                    updateCurrentEvent(state.data)
                }
            }
        }
    }

    private fun updateCurrentEvent(events: List<Event>) {
        // Logic to find current event based on time
        _currentEvent.value = events.firstOrNull()
    }

    fun addEvent(event: Event) {
        viewModelScope.launch {
            repository.addEvent(event).onSuccess {
                fetchEvents()
            }
        }
    }
}