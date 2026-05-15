package com.example.nammajatrestatepride.model

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class GalleryPhoto(
    val id: String = "",
    val url: String = "",
    val description: String = "",
    val eventId: String? = null
)