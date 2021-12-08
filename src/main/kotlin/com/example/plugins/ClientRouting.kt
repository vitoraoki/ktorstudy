package com.example.plugins

import com.example.model.ClientResponse
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.response.*
import io.ktor.routing.*

private const val URL = "https://jsonplaceholder.typicode.com"
private const val CLIENT_PATH = "/client"
private const val POSTS = "$URL/posts"
private const val POST1 = "$URL/posts/1"

fun Route.clientRouting() {
    route(CLIENT_PATH) {
        get {
            val client = buildClient()
            val response: List<ClientResponse> = client.get(POSTS)
            call.respond(response)
            client.close()
        }
    }
}

private fun buildClient(): HttpClient = HttpClient(CIO) {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
}