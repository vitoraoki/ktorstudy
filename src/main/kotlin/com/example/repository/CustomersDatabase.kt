package com.example.repository

import com.example.model.Customer
import com.example.repository.configuration.*
import com.example.repository.tables.Customers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object CustomersDatabase {

    init {
        Database.connect(url = URL, driver = DRIVER, user = USER, password = PASSWORD)
        transaction {
            SchemaUtils.create(Customers)
        }
    }

    fun getAllCustomers(): List<Customer> {
        return transaction {
            Customers.selectAll().map { Customers.customersDataBaseToCustomer(it) }
        }
    }

    fun getCustomerById(id: String): Customer? {
        val customer = transaction {
            Customers.select { Customers.id eq id.toInt() }.map { Customers.customersDataBaseToCustomer(it) }
        }
        return if (customer.isNotEmpty()) customer[0] else null
    }

    fun addCustomer(customer: Customer) {
        transaction {
            Customers.insert {
                it[firstName] = customer.firstName
                it[lastName] = customer.lastName
                it[email] = customer.email
            }
        }
    }
}