package com.example.spring_mvc

import com.example.spring_mvc.data.service.TodoService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/todo")
class TodoController(private val todoService: TodoService) {

    @GetMapping("/list")
    fun getLists(model: Model): String {
        val todos = todoService.getTodos()
        model.addAttribute("todoList", todos)
        return "list"
    }

    @PostMapping("/add")
    fun addList(@RequestParam("todo") todo: String): String {
        logger.info("todo is $todo")
        todoService.addTodo(todo)
        return "redirect:/todo/list"
    }

    @GetMapping("/delete")
    fun deleteTodo(@RequestParam("id") id: Long): String {
        logger.info("deleteTodo id: $id")
        todoService.deleteTodo(id)
        return "redirect:/todo/list"
    }

    @GetMapping("/modify")
    fun modifyTodo(@RequestParam("id") id: Long): String {
        logger.info("modifyTodo id: $id")
        todoService.updateTodo(id)
        return "redirect:/todo/list"
    }

    companion object {
        private val logger = LoggerFactory.getLogger(TodoController::class.java)
    }
}