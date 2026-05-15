package com.example.nammajatrestatepride.data

import com.example.nammajatrestatepride.model.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) {
    // ============ USERS ============
    suspend fun createUserProfile(user: User): Result<Unit> = try {
        firestore.collection("users").document(user.uid).set(user).await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    fun getUserProfile(uid: String): Flow<UiState<User>> = flow {
        emit(UiState.Loading)
        try {
            val user = firestore.collection("users").document(uid).get().await().toObject(User::class.java)
            if (user != null) emit(UiState.Success(user)) else emit(UiState.Error("User not found"))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown error"))
        }
    }

    // ============ TICKETS ============
    suspend fun bookTicket(ticket: Ticket): Result<String> = try {
        val docRef = firestore.collection("tickets").document()
        firestore.collection("tickets").document(docRef.id).set(ticket.copy(id = docRef.id)).await()
        Result.success(docRef.id)
    } catch (e: Exception) {
        Result.failure(e)
    }

    fun getUserTickets(uid: String): Flow<UiState<List<Ticket>>> = flow {
        emit(UiState.Loading)
        try {
            val tickets = firestore.collection("tickets").whereEqualTo("userId", uid).get().await()
                .mapNotNull { it.toObject(Ticket::class.java) }
            emit(UiState.Success(tickets))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown error"))
        }
    }

    // ============ GALLERY ============
    fun getGalleryPhotos(): Flow<UiState<List<GalleryPhoto>>> = flow {
        emit(UiState.Loading)
        try {
            val photos = firestore.collection("gallery").get().await()
                .mapNotNull { it.toObject(GalleryPhoto::class.java) }
            emit(UiState.Success(photos))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown error"))
        }
    }

    // ============ CULTURAL STORIES ============
    fun getCulturalStories(): Flow<UiState<List<CulturalStory>>> = flow {
        emit(UiState.Loading)
        try {
            val stories = firestore.collection("stories").get().await()
                .mapNotNull { it.toObject(CulturalStory::class.java) }
            emit(UiState.Success(stories))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown error"))
        }
    }

    // ============ EVENTS ============
    fun getEvents(): Flow<UiState<List<Event>>> = flow {
        emit(UiState.Loading)
        try {
            val events = firestore.collection("events").get().await()
                .mapNotNull { it.toObject(Event::class.java) }
            emit(UiState.Success(events))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown error"))
        }
    }

    suspend fun addEvent(event: Event): Result<String> = try {
        val docRef = firestore.collection("events").document()
        firestore.collection("events").document(docRef.id).set(event.copy(id = docRef.id)).await()
        Result.success(docRef.id)
    } catch (e: Exception) {
        Result.failure(e)
    }

    // ============ LOCATIONS ============
    fun getLocationsByType(type: String): Flow<UiState<List<Location>>> = flow {
        emit(UiState.Loading)
        try {
            val locations = firestore.collection("locations").whereEqualTo("type", type).get().await()
                .mapNotNull { it.toObject(Location::class.java) }
            emit(UiState.Success(locations))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown error"))
        }
    }

    // ============ LOST & FOUND ============
    fun getLostFoundPosts(): Flow<UiState<List<LostFoundPost>>> = flow {
        emit(UiState.Loading)
        try {
            val posts = firestore.collection("lost_found").get().await()
                .mapNotNull { it.toObject(LostFoundPost::class.java) }
            emit(UiState.Success(posts))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown error"))
        }
    }

    suspend fun addLostFoundPost(post: LostFoundPost): Result<String> = try {
        val docRef = firestore.collection("lost_found").document()
        firestore.collection("lost_found").document(docRef.id).set(post.copy(id = docRef.id)).await()
        Result.success(docRef.id)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun updatePostStatus(postId: String, status: String): Result<Unit> = try {
        firestore.collection("lost_found").document(postId).update("status", status).await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    // ============ FEEDBACK ============
    suspend fun submitFeedback(feedback: Feedback): Result<Unit> = try {
        firestore.collection("feedback").document().set(feedback).await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}