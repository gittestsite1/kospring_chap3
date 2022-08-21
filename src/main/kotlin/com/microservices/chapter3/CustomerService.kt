package com.microservices.chapter3


// 서비스에 필요한 메소드를 정의하자!!
interface CustomerService {
    fun getCustomer(id : Int) : Customer?
    fun createCustomer(customer: Customer)
    fun deleteCustomer(id : Int)
    fun updateCustomer(id : Int, customer: Customer)
    fun searchCustomer(nameFilter : String): List<Customer>
}