package com.example.spring_mvc

import com.example.spring_mvc.data.service.TodoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todos")
class TodoApiController(
    private val todoService: TodoService
) {

    @GetMapping
    fun getList(): String {
        return todoService.getTodos().toString()
    }
}