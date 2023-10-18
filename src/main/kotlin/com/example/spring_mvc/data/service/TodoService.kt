package com.example.spring_mvc.data.service

import com.example.spring_mvc.data.dao.TodoDao
import com.example.spring_mvc.data.entity.TodoEntity
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoService(private val todoDao: TodoDao) {

    @Transactional(readOnly = true)
    fun getAllTodos(): List<TodoEntity> {
        return todoDao.getTodos()
    }

    @Transactional(readOnly = true)
    fun getTodo(id: Long): TodoEntity {
        return todoDao.getTodo(id) ?: throw Exception("Todo is not found")
    }

    @Transactional()
    fun addTodo(todo: String): TodoEntity {
        return todoDao.insertTodo(todo)
    }

    @Transactional()
    fun updateTodo(id: Long) {
        val todo = getTodo(id)
        return todoDao.updateTodo(todo.copy(done = todo.done.not()))
    }

    @Transactional()
    fun deleteTodo(id: Long) {
        return todoDao.deleteTodo(id)
    }
}