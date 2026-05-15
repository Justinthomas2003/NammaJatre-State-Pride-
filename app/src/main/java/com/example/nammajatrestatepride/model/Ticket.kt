package com.example.nammajatrestatepride.model

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Ticket(
    val id: String = "",
    val userId: String = "",
    val type: String = "", // "entry" or "participation"
    val eventId: String? = null,
    val visitorCount: Int = 1,
    val date: String = "",
    val qrCode: String = "",
    val status: String = "active"
)