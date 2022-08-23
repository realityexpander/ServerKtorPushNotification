package com.realityexpander.data.remote

import com.realityexpander.data.remote.dto.Notification
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class OneSignalServiceImpl(
    private val client: HttpClient,
    private val apiKey: String
): OneSignalService {

    override suspend fun sendNotification(notification: Notification): Boolean {
        return try {
            println(Json.encodeToString(notification))

            client.post<String> {
                url(OneSignalService.SEND_NOTIFICATION_URL)
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