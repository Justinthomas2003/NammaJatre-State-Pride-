package com.example.nammajatrestatepride.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nammajatrestatepride.data.FirestoreRepository
import com.example.nammajatrestatepride.model.Ticket
import com.example.nammajatrestatepride.model.UiState
import com.example.nammajatrestatepride.model.User
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: FirestoreRepository,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _userState =
        MutableStateFlow<UiState<User>>(UiState.Loading)

    val userState: StateFlow<UiState<User>> = _userState

    private val _ticketsState =
        MutableStateFlow<UiState<List<Ticket>>>(UiState.Loading)

    val ticketsState: StateFlow<UiState<List<Ticket>>> = _ticketsState

    init {
        loadProfile()
    }

    private fun loadProfile() {

        val uid = firebaseAuth.currentUser?.uid ?: return

        viewModelScope.launch {

            repository.getUserProfile(uid).collect {
                _userState.value = it
            }

            repository.getUserTickets(uid).collect {
                _ticketsState.value = it
            }
        }
    }

    fun logout() {
        firebaseAuth.signOut()
    }
}