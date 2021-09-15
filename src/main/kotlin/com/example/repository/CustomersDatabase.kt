package com.example.repository

import com.example.model.Customer

object CustomersDatabase {
    private val listOfCustomers = mutableListOf<Customer>()

    fun getAllCustomers(): List<Customer> = listOfCustomers

    fun getCustomerById(id: String): Customer? = listOfCustomers.find { it.id == id }

    fun addCustomer(customer: Customer) {
        listOfCustomers.add(customer)
    }
}