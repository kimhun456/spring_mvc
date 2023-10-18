package com.example.spring_mvc.data.entity

data class TodoEntity(
    var id: Long = 0L,
    var todo: String = "",
    var done: Boolean = false
)
