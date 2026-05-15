package com.example.nammajatrestatepride.tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nammajatrestatepride.data.FirestoreRepository
import com.example.nammajatrestatepride.model.Ticket
import com.example.nammajatrestatepride.model.UiState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TicketViewModel @Inject constructor(
    private val repository: FirestoreRepository,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _bookingState =
        MutableStateFlow<UiState<String>>(UiState.Idle)

    val bookingState: StateFlow<UiState<String>> = _bookingState

    fun bookFairEntry(count: Int, date: String) {

        val uid = firebaseAuth.currentUser?.uid ?: run {
            _bookingState.value = UiState.Error("User not logged in")
            return
        }

        val ticket = Ticket(
            userId = uid,
            type = "entry",
            visitorCount = count,
            date = date,
            qrCode = UUID.randomUUID().toString()
        )

        viewModelScope.launch {

            _bookingState.value = UiState.Loading

            val result = repository.bookTicket(ticket)

            _bookingState.value =
                if (result.isSuccess) {
                    UiState.Success(result.getOrNull() ?: "")
                } else {
                    UiState.Error(
                        result.exceptionOrNull()?.message ?: "Failed"
                    )
                }
        }
    }

    fun bookParticipation(eventId: String) { // Removed unused 'details' parameter

        val uid = firebaseAuth.currentUser?.uid ?: run {
            _bookingState.value = UiState.Error("User not logged in")
            return
        }

        val ticket = Ticket(
            userId = uid,
            type = "participation",
            eventId = eventId,
            qrCode = UUID.randomUUID().toString()
        )

        viewModelScope.launch {

            _bookingState.value = UiState.Loading

            val result = repository.bookTicket(ticket)

            _bookingState.value =
                if (result.isSuccess) {
                    UiState.Success(result.getOrNull() ?: "")
                } else {
                    UiState.Error(
                        result.exceptionOrNull()?.message ?: "Failed"
                    )
                }
        }
    }
}