package com.microservices.chapter3

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class JsonExampleController {
    @GetMapping(value = arrayOf("/json"))
    fun getJson() = SimpleObject()

}