package com.example.spring_mvc

import com.example.spring_mvc.config.ApplicationConfig
import com.example.spring_mvc.data.dao.TodoDao
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.AnnotationConfigApplicationContext

@SpringBootApplication
class SpringMvcApplication

fun main(args: Array<String>) {
    runApplication<SpringMvcApplication>(*args)

    val context = AnnotationConfigApplicationContext(ApplicationConfig::class.java)
    val todoDao = context.getBean(TodoDao::class.java)
    todoDao.createTable()
    todoDao.insertTodo("TEST todo!")
    todoDao.getTodos().forEach { println(it) }
    todoDao.getTodo(1)?.let { println(it) }

    todoDao.updateTodo(todoEntity = todoDao.getTodo(1)!!.copy(done = true))
    todoDao.getTodos().forEach { println(it) }
}
