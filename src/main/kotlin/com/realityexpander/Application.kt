package com.realityexpander

import com.realityexpander.data.remote.OneSignalServiceImpl
import io.ktor.application.*
import com.realityexpander.plugins.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

// Make an account on OneSignal and get your app ID and Rest API key

// test this server from browser:
// http://192.168.0.186:8083/sendNotification?title=123&description=456

// Dashboards:
// https://console.firebase.google.com/u/0/project/ktorpushnotifications/settings/cloudmessaging
// https://console.cloud.google.com/apis/api/googlecloudmessaging.googleapis.com/quotas?project=ktorpushnotifications&authuser=0&hl=en
// https://app.onesignal.com/apps/01206360-f412-41b8-b4ed-830f026b4660/settings/configure/google-android

// https://documentation.onesignal.com/reference/create-notification

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

    val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer() // Convert JSON to Kotlin objects
        }
    }
    val apiKey = environment.config.property("onesignal.api_key").getString() // from application.conf
    val service = OneSignalServiceImpl(client, apiKey)

    configureRouting(service)
}
