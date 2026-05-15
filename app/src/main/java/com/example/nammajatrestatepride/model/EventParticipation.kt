package com.example.nammajatrestatepride.model

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class EventParticipation(
    val id: String = "",
    val eventId: String = "",
    val userId: String = "",
    val details: String = "",
    val status: String = "pending"
)