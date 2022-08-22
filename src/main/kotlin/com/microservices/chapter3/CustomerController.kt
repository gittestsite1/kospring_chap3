package com.microservices.chapter3

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ConcurrentHashMap

@RestController
class CustomerController {

    //ADD 객체를 주입하낟.
    @Autowired
//    lateinit var customers : ConcurrentHashMap<Int, Customer>
    private lateinit var customerService: CustomerService

    @GetMapping(value = arrayOf("/customer/{id}"))
//    fun getCustomer(@PathVariable id : Int) = customerService.getCustomer(id)
    fun getCustomer(@PathVariable id : Int) : ResponseEntity<Customer?>{
        val customer = customerService.getCustomer(id)
        val status = if (customer == null) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(customer, status)
    }
    @GetMapping(value = arrayOf("/customers"))
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
        customerService.searchCustomer(nameFilter)

    @PostMapping(value = arrayOf("/customer"))
//    fun createCustomer(@RequestBody customer: Customer) {
//        customerService.createCustomer(customer)
//    }
    fun createCustomer(@RequestBody customer: Customer) : ResponseEntity<Unit?>{
        customerService.createCustomer(customer)
//        return ResponseEntity(Unit, HttpStatus.CREATED)
        return ResponseEntity<Unit?>(null, HttpStatus.CREATED)
    }

    @DeleteMapping(value = arrayOf("/customer/{id}"))
    fun deleteCustomer(@PathVariable id: Int) : ResponseEntity<Unit>{
        var status = HttpStatus.NOT_FOUND
        if (customerService.getCustomer(id) != null){
            customerService.deleteCustomer(id)
            status = HttpStatus.OK
        }
        return ResponseEntity(Unit, status)
    }


    @PutMapping(value = arrayOf("/customer/{id}"))
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: Customer) :
            ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if ( customerService.getCustomer(id) != null){
            customerService.updateCustomer(id, customer)
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit, status)
    }
}

