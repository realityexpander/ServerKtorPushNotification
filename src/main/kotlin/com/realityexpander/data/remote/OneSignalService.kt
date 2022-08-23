package com.realityexpander.data.remote

import com.realityexpander.data.remote.dto.Notification

interface OneSignalService {

    suspend fun sendNotification(notification: Notification): Boolean

    companion object {
        const val ONESIGNAL_APP_ID = "01206360-f412-41b8-b4ed-830f026b4660" // be sure to generate your own app id

        const val SEND_NOTIFICATION_URL = "https://onesignal.com/api/v1/notifications"
    }
}