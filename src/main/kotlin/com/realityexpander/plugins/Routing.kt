package com.realityexpander.plugins

import com.realityexpander.data.remote.OneSignalService
import com.realityexpander.data.remote.dto.Notification
import com.realityexpander.data.remote.dto.NotificationMessage
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*

fun Application.configureRouting(service: OneSignalService) {

    routing {

        // should be a POST request, but for testing purposes we'll use GET so we can use simple query strings
        get("/sendNotification") {
            val title = call.parameters["title"] ?: "Test"
            val description = call.parameters["description"] ?: "Test"

            val successful = service.sendNotification(
                Notification(
                    includedSegments = listOf("All"), // send to all registered users
                    headings = NotificationMessage(en = title),
                    contents = NotificationMessage(en = description),
                    appId = OneSignalService.ONESIGNAL_APP_ID
                )
            )

            if(successful) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.InternalServerError)
            }
        }
    }
}
