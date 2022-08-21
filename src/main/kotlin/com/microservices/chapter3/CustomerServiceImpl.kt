package com.microservices.chapter3

import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class CustomerServiceImpl : CustomerService {


    // 메소드를 구현하기 전에 고객을 해당 클래스에 포함시캬야 한다.
    // 전에 Bean에서 했던 것처럼 맵과 초기화를 추가한다.

    companion object{
        val initialCustomers = arrayOf(
            Customer(1, "aaaa"),
            Customer(2, "bbbbb"),
            Customer(3, "ccccc")
        )

        val customers = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))
    }
    override fun getCustomer(id: Int) = customers[id]

    override fun createCustomer(customer: Customer) {
        customers[customer.id] = customer
    }

    override fun deleteCustomer(id: Int) {
        customers.remove(id)
    }


    override fun updateCustomer(id: Int, customer: Customer) {
        deleteCustomer(id)
        createCustomer(customer)
    }

    override fun searchCustomer(nameFilter: String): List<Customer> =
        customers.filter {
            it.value.name.contains(nameFilter, true)
        }.map(Map.Entry<Int,Customer>::value).toList()
}