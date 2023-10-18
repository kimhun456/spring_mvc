package com.example.spring_mvc

import com.example.spring_mvc.data.service.TodoService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/todo")
class TodoController(private val todoService: TodoService) {

    @GetMapping("/list")
    fun getLists(model: Model): String {
        val todos = todoService.getAllTodos()
        model.addAttribute("list", todos)
        return "list"
    }
}