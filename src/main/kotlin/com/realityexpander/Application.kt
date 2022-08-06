package com.realityexpander

import com.realityexpander.data.remote.OneSignalServiceImpl
import io.ktor.application.*
import com.realityexpander.plugins.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

// Make an account on OneSignal and get your app ID and Rest API key

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
