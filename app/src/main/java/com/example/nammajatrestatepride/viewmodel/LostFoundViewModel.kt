package com.example.nammajatrestatepride.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nammajatrestatepride.data.FirestoreRepository
import com.example.nammajatrestatepride.model.LostFoundPost
import com.example.nammajatrestatepride.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LostFoundViewModel @Inject constructor(
    private val repository: FirestoreRepository
) : ViewModel() {

    private val _postsState = MutableStateFlow<UiState<List<LostFoundPost>>>(UiState.Loading)
    val postsState: StateFlow<UiState<List<LostFoundPost>>> = _postsState.asStateFlow()

    init {
        fetchPosts()
    }

    fun fetchPosts() {
        viewModelScope.launch {
            repository.getLostFoundPosts().collect { state ->
                _postsState.value = state
            }
        }
    }

    fun addPost(post: LostFoundPost) {
        viewModelScope.launch {
            repository.addLostFoundPost(post).onSuccess {
                fetchPosts()
            }
        }
    }

    fun markAsResolved(postId: String) {
        viewModelScope.launch {
            repository.updatePostStatus(postId, "resolved").onSuccess {
                fetchPosts()
            }
        }
    }
}