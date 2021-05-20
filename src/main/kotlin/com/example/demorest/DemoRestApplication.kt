package com.example.demorest

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.*

@SpringBootApplication
@RestController
class DemoRestApplication {

    private val log: Logger = LoggerFactory.getLogger(DemoRestApplication::class.java)

    @Autowired
    val customerRepository: CustomerRepository? = null


    @GetMapping("/hello")
    fun sayHello(@RequestParam(value = "myName", defaultValue = "World") name: String): String {
        return String.format("Hello %s!", name)
    }

    @GetMapping("/list")
    fun getCustomers(): Iterable<Customer>? {
        return customerRepository!!.findAll()
    }

    @GetMapping("/find/{id}")
    fun getCustomerById(@PathVariable id: Long): Customer? {
        return customerRepository!!.findById(id)
    }

    @GetMapping("/find")
    fun findCustomerById(@RequestParam id: Long): Customer? {
        return customerRepository!!.findById(id)
    }
    
    @PostMapping("/add")
    fun addCustomer(@RequestParam first: String?, @RequestParam last: String?): String? {
        val customer = Customer()
        if(1<5) throw RuntimeException("test")
        customer.firstName = first
        customer.lastName = last
        customerRepository!!.save(customer)
        return "Added new customer to repo!"
    }

    @DeleteMapping("/delete")
    fun deleteCustomerById(@RequestParam id: Long): Customer? {
        val c = customerRepository!!.findById(id)
        if(c!=null) {
            customerRepository!!.delete(c)
        }
        return c
    }

    @PostMapping("/delete")
    fun deleteWithPostCustomerById(@RequestParam id: Long): Customer? {
        val c = customerRepository!!.findById(id)
        if(c!=null) {
            customerRepository!!.delete(c)
        }
        return c
    }

    @PostMapping("/delete/{id}")
    fun deleteWithGetCustomerById(@PathVariable id: Long): Customer? {
        val c = customerRepository!!.findById(id)
        if(c!=null) {
            customerRepository!!.delete(c)
        }
        return c
    }

    @Bean
    fun demo(repository: CustomerRepository): CommandLineRunner? {
        return CommandLineRunner { args ->
            // save a few customers
            repository.save(Customer("Jack", "Bauer"))
            repository.save(Customer("Chloe", "O'Brian"))
            repository.save(Customer("Kim", "Bauer"))
            repository.save(Customer("David", "Palmer"))
            repository.save(Customer("Michelle", "Dessler"))

            // fetch all customers
            log.info("Customers found with findAll():")
            log.info("-------------------------------")
            for (customer in repository.findAll()) {
                log.info(customer.toString())
            }
            log.info("")

            // fetch an individual customer by ID
            val customer: Customer = repository.findById(1L)!!
            log.info("Customer found with findById(1L):")
            log.info("--------------------------------")
            log.info(customer.toString())
            log.info("")

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):")
            log.info("--------------------------------------------")
            repository.findByLastName("Bauer").forEach { bauer -> log.info(bauer.toString()) }
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            // 	log.info(bauer.toString());
            // }
            log.info("")
        }
    }
}

fun main(args: Array<String>) {
    runApplication<DemoRestApplication>(*args)
}
