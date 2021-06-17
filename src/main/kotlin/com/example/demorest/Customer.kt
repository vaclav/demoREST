package com.example.demorest

import javax.persistence.*

@Entity
data class Customer(
                   var firstName: String? = null,
                   var lastName: String? = null,
                   var age: Int? = null,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0

    @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY)
    private val _orders = mutableListOf<Order>()

    val orders get() = _orders.toList()

    fun addOrder(newItem: Order) {
        _orders += newItem
    }
}