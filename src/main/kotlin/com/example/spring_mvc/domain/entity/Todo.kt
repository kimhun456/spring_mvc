package com.example.spring_mvc.domain.entity

data class Todo(
    val id: Long = 0L,
    val todo: String = "",
    val done: Boolean = false
)
