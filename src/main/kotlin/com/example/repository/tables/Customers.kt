package com.example.repository.tables

import com.example.model.Customer
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

private const val TABLE_NAME = "Customers"
private const val PRIMARY_KEY = "PK_customers_id"
private const val COLUMN_ID = "id"
private const val COLUMN_FIRST_NAME = "first_name"
private const val COLUMN_LAST_NAME = "last_name"
private const val COLUMN_EMAIL = "email"

object Customers: Table(name = TABLE_NAME) {
    val id : Column<Int> = integer(COLUMN_ID).autoIncrement()
    val firstName: Column<String> = text(COLUMN_FIRST_NAME)
    val lastName: Column<String> = text(COLUMN_LAST_NAME)
    val email: Column<String> = text(COLUMN_EMAIL)

    override val primaryKey: PrimaryKey = PrimaryKey(id, name = PRIMARY_KEY)

    fun customersDataBaseToCustomer(row: ResultRow): Customer =
        Customer(
            id = row[id].toString(),
            firstName = row[firstName],
            lastName = row[lastName],
            email = row[email]
        )
}