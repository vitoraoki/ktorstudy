package com.example.plugins

import com.example.model.Customer
import com.example.plugins.error.errorMissingId
import com.example.plugins.error.errorNoCustomersYet
import com.example.plugins.error.errorCustomerNotFound
import com.example.plugins.error.errorWrongCustomerBody
import com.example.repository.CustomersDatabase
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*
import kotlinx.serialization.SerializationException

private const val CUSTOMER_PATH = "/customer"
private const val ALL_PATH = "all"
private const val ID = "id"
private const val ID_PARAMETER = "{$ID}"
private const val ADD_CUSTOMER_PATH = "/add_customer"

fun Route.customerRouting() {
    route(CUSTOMER_PATH) {
        get(ALL_PATH) {
            val customers = CustomersDatabase.getAllCustomers()
            if (customers.isNotEmpty()) {
                respondAllCostumers(customers)
            } else {
                errorNoCustomersYet()
            }
        }

        get(ID_PARAMETER) {
            val id = call.parameters[ID]
            if (id != null) {
                respondCustomer(id)
            } else {
                errorMissingId()
            }
        }

        post(ADD_CUSTOMER_PATH) {
            try {
                storeCustomer()
            } catch (exception: SerializationException) {
                errorWrongCustomerBody()
            }
        }
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.storeCustomer() {
    val customer = call.receive<Customer>()
    CustomersDatabase.addCustomer(customer)
    val message = "Customer added!"
    call.respond(status = HttpStatusCode.Created, message = message)
}

private suspend fun PipelineContext<Unit, ApplicationCall>.respondCustomer(id: String) {
    val customer = CustomersDatabase.getCustomerById(id)
    if (customer != null) {
        call.respond(customer)
    } else {
        errorCustomerNotFound()
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.respondAllCostumers(customers: List<Customer>) {
    call.respond(customers)
}