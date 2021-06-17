package com.example.demorest

import javax.persistence.*

/*
The table name must be specified explicitly as "ORDER" is a keyword in SQL and so the generated queries are invalid,
if the name of the table is left to the default value "ORDER".
 */
@Entity
@Table(name = "ORDER2")
class Order(
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id")
    val customer: Customer? = null,
    var price: Int? = null,
    var orderNum: Int? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}