package com.realityexpander.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// from: https://documentation.onesignal.com/reference/create-notification

@Serializable
data class Notification(
    // @SerialName("include_external_user_ids")  // send to a specific user Id
    // val includeExternalUserIds: List<String>,

    @SerialName("included_segments")
    val includedSegments: List<String>,

    val contents: NotificationMessage,
    val headings: NotificationMessage,

    val buttons: List<NotificationButton> = listOf(),
    val content_available: String = "",

    val big_picture: String = "",
    val large_icon: String = "",


    @SerialName("app_id")
    val appId: String,
)