package com.realityexpander.data.remote

import com.realityexpander.data.remote.dto.Notification
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class OneSignalServiceImpl(
    private val client: HttpClient,
    private val apiKey: String
): OneSignalService {

    override suspend fun sendNotification(notification: Notification): Boolean {
        return try {
            client.post<String> {
                url(OneSignalService.NOTIFICATIONS_URL)
                contentType(ContentType.Application.Json)
                header("Authorization", "Basic $apiKey")
                body = notification  // automatically serialized to JSON
            }

            true
        } catch(e: Exception) {
            e.printStackTrace()

            false
        }
    }
}