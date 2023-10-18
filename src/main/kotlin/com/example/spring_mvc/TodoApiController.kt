package com.example.spring_mvc

import com.example.spring_mvc.data.entity.TodoEntity
import com.example.spring_mvc.data.service.TodoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todos")
class TodoApiController(
    private val todoService: TodoService
) {

    @GetMapping
    fun getList(): List<TodoEntity> {
        return todoService.getTodos()
    }

    @GetMapping("/{id}")
    fun getTodo(@PathVariable id: Long): TodoEntity {
        return todoService.getTodo(id)
    }

    @PostMapping
    fun addTodo(@RequestBody todo: TodoEntity): TodoEntity {
        return todoService.addTodo(todo.todo)
    }

    @DeleteMapping
    fun deleteTodo(@RequestBody todo: TodoEntity): String {
        todoService.deleteTodo(todo.id)
        return "Okay"
    }


    @PatchMapping
    fun updateTodo(@RequestBody todo: TodoEntity): String {
        todoService.updateTodo(todo.id)
        return "Okay"
    }
}