package com.example.nammajatrestatepride.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val auth: FirebaseAuth) : ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    // Default demo credentials
    companion object {
        private const val DEMO_EMAIL = "demo@nammajatrea.in"
        private const val DEMO_PASSWORD = "Demo@12345"
    }

    sealed class AuthState {
        object Idle : AuthState()
        object Loading : AuthState()
        data class Success(val user: FirebaseUser) : AuthState()
        data class Error(val message: String) : AuthState()
    }

    init {
        // Auto-login with demo account on app start
        autoLoginWithDemo()
    }

    fun autoLoginWithDemo() {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            try {
                // First check if user already logged in
                val currentUser = auth.currentUser
                if (currentUser != null) {
                    _authState.value = AuthState.Success(currentUser)
                } else {
                    // Try to login with demo credentials
                    val result = auth.signInWithEmailAndPassword(DEMO_EMAIL, DEMO_PASSWORD).await()
                    _authState.value = AuthState.Success(result.user!!)
                }
            } catch (e: Exception) {
                // If demo login fails, try to register demo account
                viewModelScope.launch {
                    try {
                        val result = auth.createUserWithEmailAndPassword(DEMO_EMAIL, DEMO_PASSWORD).await()
                        result.user?.updateProfile(
                            com.google.firebase.auth.UserProfileChangeRequest.Builder()
                                .setDisplayName("Demo User")
                                .build()
                        )?.await()
                        _authState.value = AuthState.Success(result.user!!)
                    } catch (registerError: Exception) {
                        // If registration also fails, just set as logged in (offline mode)
                        _authState.value = AuthState.Error(registerError.message ?: "Auto-login failed, but proceeding offline")
                    }
                }
            }
        }
    }

    fun signIn(email: String, password: String) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            try {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                _authState.value = AuthState.Success(result.user!!)
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Sign in failed")
            }
        }
    }

    fun register(email: String, password: String, name: String) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                result.user?.updateProfile(
                    com.google.firebase.auth.UserProfileChangeRequest.Builder()
                        .setDisplayName(name)
                        .build()
                )?.await()
                _authState.value = AuthState.Success(result.user!!)
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Registration failed")
            }
        }
    }

    fun logout() {
        auth.signOut()
        _authState.value = AuthState.Idle
    }

    fun checkCurrentUser(): FirebaseUser? = auth.currentUser
}