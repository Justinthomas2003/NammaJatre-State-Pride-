package com.example.nammajatrestatepride.support

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nammajatrestatepride.data.FirestoreRepository
import com.example.nammajatrestatepride.model.Feedback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SupportViewModel @Inject constructor(private val repository: FirestoreRepository) : ViewModel() {
    fun submitFeedback(message: String) {
        viewModelScope.launch {
            repository.submitFeedback(Feedback(message = message))
        }
    }
}