package com.microservices.chapter3

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
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
    lateinit var customers : ConcurrentHashMap<Int, Customer>

    @RequestMapping(value = arrayOf("/customer/{id}"), method = arrayOf(RequestMethod.GET))
//    fun getCustomer() = "hello from a controller"
//    fun getCustomer() = Customer(1, "kotlin")
//    fun getCustomer() = customer[2]
    fun getCustomer(@PathVariable id : Int) = customers[id]


    @RequestMapping(value = arrayOf("/customers"), method= arrayOf(RequestMethod.GET))
//    fun getCustomers() = customers.map(
//        Map.Entry<Int, Customer>::value).toList()

    // ?name을 식별하기 위한 방법
    fun getCustomer(@RequestParam(required = false, defaultValue = "") nameFilter : String)
    = customers.filter {
        it.value.name.contains(nameFilter, true)
    }.map (Map.Entry<Int, Customer>::value).toList()


    @RequestMapping(value = arrayOf("/test", "test2")
        , method = arrayOf(RequestMethod.GET, RequestMethod.POST))
    @ResponseBody
    fun hello() = "aaaa"
}

