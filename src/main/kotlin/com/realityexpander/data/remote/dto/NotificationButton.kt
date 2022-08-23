package com.realityexpander.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class NotificationButton(
    val id: String,
    val text: String,
    val icon: String,
)