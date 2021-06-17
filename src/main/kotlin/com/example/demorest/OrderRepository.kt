package com.example.demorest

import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<Order, Int> {
    fun findByOrderNum(orderNum: Int): List<Order>

    fun findById(id: Long): Order?
}