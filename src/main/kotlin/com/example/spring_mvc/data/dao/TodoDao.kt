package com.example.spring_mvc.data.dao

import com.example.spring_mvc.data.entity.TodoEntity
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class TodoDao(private val jdbcTemplate: JdbcTemplate) {

    @Transactional
    fun createTable(): Int {
        val sql = "create TABLE todos (" +
                "id INTEGER auto_increment PRIMARY KEY," +
                "todo VARCHAR(255)," +
                "done boolean" +
                ");" +
                "INSERT INTO todos (todo,done) VALUES ( '봄봄이랑 놀아주기', false);" +
                "INSERT INTO todos (todo,done) VALUES ( 'Spring Cloud 공부하기', false);" +
                "INSERT INTO todos (todo,done )VALUES ('블로그 글쓰기', false);"
        return jdbcTemplate.update(sql)
    }

    @Transactional
    fun insertTodo(todo: String): Int {
        val sql = "insert into TODOS(todo, done) values(?, false);"
        return jdbcTemplate.update(sql, todo)
    }

    @Transactional
    fun updateTodo(todoEntity: TodoEntity) {
        val sql = "update todos set done = ? where id = ?;"
        jdbcTemplate.update(sql, todoEntity.done, todoEntity.id)
    }

    @Transactional
    fun deleteTodo(id: Long) {
        val sql = "delete todos where id = ?;"
        jdbcTemplate.update(sql, id)
    }

    @Transactional(readOnly = true)
    fun getTodos(): List<TodoEntity> {
        val sql = "SELECT * FROM TODOS order by id desc"
        return jdbcTemplate.query(sql, BeanPropertyRowMapper(TodoEntity::class.java))
    }

    @Transactional(readOnly = true)
    fun getTodo(id: Long): TodoEntity? {
        val sql = "SELECT id, todo, done from todos where id = ? "
        return jdbcTemplate.queryForObject(sql, TodoMapper(), id)
    }

}