package com.example.demorest

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class AnotherController {
    private val log: Logger = LoggerFactory.getLogger(DemoRestApplication::class.java)

    @Autowired
    val customerRepository: CustomerRepository? = null

    @GetMapping("/findCustomer/{id}")
    fun getCustomerDetailById(@PathVariable id: Long, m: Model): String {
        val c = customerRepository!!.findById(id)
        m.addAttribute("customer", c)
        return "customer2"
    }
}