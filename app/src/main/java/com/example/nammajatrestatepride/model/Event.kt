package com.example.nammajatrestatepride.model

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Event(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val location: String = "",
    val category: String = "", // Rathotsava, Wrestling, Drama, Cattle Fair
    val imageUrl: String = "",
    val timestamp: Long = 0L
)

@IgnoreExtraProperties
data class LostFoundPost(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val type: String = "", // "lost" or "found"
    val contactName: String = "",
    val contactPhone: String = "",
    val status: String = "active", // "active" or "resolved"
    val timestamp: Long = System.currentTimeMillis(),
    val userId: String = ""
)

@IgnoreExtraProperties
data class Location(
    val id: String = "",
    val name: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val type: String = "", // "food", "toy", "parking", "firstaid", "police"
    val address: String = "",
    val phone: String = ""
)

@IgnoreExtraProperties
data class CulturalStory(
    val id: String = "",
    val title: String = "",
    val content: String = "",
    val imageUrl: String = "",
    val category: String = "", // "history", "legend", "tradition"
    val timestamp: Long = 0L
)