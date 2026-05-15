package com.example.nammajatrestatepride.model

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Feedback(
    val id: String = "",
    val userId: String = "",
    val message: String = "",
    val type: String = "general"
)