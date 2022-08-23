package com.realityexpander.plugins

import com.realityexpander.data.remote.OneSignalService
import com.realityexpander.data.remote.dto.Notification
import com.realityexpander.data.remote.dto.NotificationButton
import com.realityexpander.data.remote.dto.NotificationMessage
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*

fun Application.configureRouting(service: OneSignalService) {

    routing {

        // should be a POST request, but for testing purposes we'll use GET so we can use simple query strings
        get("/sendNotification") {
            val title = call.parameters["title"] ?: "Title is missing"
            val description = call.parameters["description"] ?: "Description is missing"

            var buttons: List<NotificationButton> = listOf()
            call.parameters["button1Id"]?.let { buttonId ->
                val buttonText = call.parameters["button1Text"] ?: "Button 1 title is text"
                val buttonIcon = call.parameters["button1Icon"] ?: ""
                buttons = buttons + NotificationButton(id=buttonId, text=buttonText, icon=buttonIcon)
            }
            call.parameters["button2Id"]?.let { buttonid ->
                val buttonText = call.parameters["button2Text"] ?: "Button 2 title is text"
                val buttonIcon = call.parameters["button2Icon"] ?: ""
                buttons = buttons + NotificationButton(id = buttonid, text=buttonText, icon=buttonIcon)
            }
            call.parameters["button3Id"]?.let { buttonid ->
                val buttonText = call.parameters["button3Text"] ?: "Button 3 title is text"
                val buttonIcon = call.parameters["button3Icon"] ?: ""
                buttons = buttons + NotificationButton(id = buttonid, text=buttonText, icon=buttonIcon)
            }

            val successful =
                service.sendNotification(
                    Notification(
                        includedSegments = listOf("All"), // send to all registered users
                        headings = NotificationMessage(en = title),
                        contents = NotificationMessage(en = description),
                        buttons = buttons,
                        appId = OneSignalService.ONESIGNAL_APP_ID,
                        big_picture = "https://www.filepicker.io/api/file/CfdM1u7UQxOApkWw5kP1?filename=name.jpg",
                    )
                )

            if (successful) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.InternalServerError)
            }
        }
    }
}
