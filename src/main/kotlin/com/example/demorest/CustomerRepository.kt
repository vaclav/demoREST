package com.example.demorest

import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<Customer, Int> {
    fun findByLastName(lastName: String): List<Customer>

    fun findById(id: Long): Customer?
}