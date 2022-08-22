package com.microservices.chapter3

//class SimpleObject {
//    public val name = "Hello"
//    private val place = "World!!22333"
//    public fun getaaaa()  = place
//
//    public fun getAAA() = place     // 잘 출력됨
//    public fun getaaa() = place     // 잘 출력됨
//    public fun aaa() = place        // X : 출력되지 않음
//}


data class SimpleObject (
    var name : String = "justin",
    var test : String = "kotlin test text",
    val test2 : String = "val-setting"
)
