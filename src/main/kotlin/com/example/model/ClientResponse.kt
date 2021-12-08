package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class ClientResponse(
    val userId: Long,
    val id: Long,
    val title: String,
    val body: String
)
