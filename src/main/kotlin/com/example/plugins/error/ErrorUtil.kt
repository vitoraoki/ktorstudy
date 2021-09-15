package com.example.plugins.error

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.util.pipeline.*

internal suspend fun PipelineContext<Unit, ApplicationCall>.errorCustomerNotFound() {
    val errorMessage = "Customer not found!"
    call.respond(
        status = HttpStatusCode.NotFound,
        message = errorMessage
    )
}

internal suspend fun PipelineContext<Unit, ApplicationCall>.errorNoCustomersYet() {
    val errorMessage = "There are no customers yet!"
    call.respond(
        status = HttpStatusCode.NotFound,
        message = errorMessage
    )
}

internal suspend fun PipelineContext<Unit, ApplicationCall>.errorMissingId() {
    val errorMessage = "Missing or malformed id!"
    call.respond(
        status = HttpStatusCode.BadRequest,
        message = errorMessage,
    )
}

internal suspend fun PipelineContext<Unit, ApplicationCall>.errorWrongCustomerBody() {
    val errorMessage = "Bad formatted customer body!"
    call.respond(
        status = HttpStatusCode.BadRequest,
        message = errorMessage
    )
}