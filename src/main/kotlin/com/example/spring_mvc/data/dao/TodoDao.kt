package com.example.spring_mvc.data.dao

import com.example.spring_mvc.data.entity.TodoEntity
import jakarta.annotation.PostConstruct
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class TodoDao(private val jdbcTemplate: JdbcTemplate) {


    private lateinit var simpleJdbcInsert: SimpleJdbcInsert

    @PostConstruct
    fun init() {
        simpleJdbcInsert = SimpleJdbcInsert(jdbcTemplate)
            .withTableName("todos")
            .usingGeneratedKeyColumns("id")
    }

    @Transactional
    fun createTable(): Int {
        val sql = "create TABLE todos (" +
                "id INTEGER auto_increment PRIMARY KEY," +
                "todo VARCHAR(255)," +
                "done boolean" +
                ");" +
                "INSERT INTO todos (todo,done) VALUES ( 'eat beef', false);" +
                "INSERT INTO todos (todo,done) VALUES ( 'study cloud', false);" +
                "INSERT INTO todos (todo,done )VALUES ('write blog', false);"
        return jdbcTemplate.update(sql)
    }

//    @Transactional
//    fun addTodo(todo: String): TodoEntity {
//        val sql = "insert into TODOS(todo, done) values(?, false);"
//        val id = jdbcTemplate.update(sql, todo)
//        return TodoEntity(id = id, todo = todo, done = false)
//    }

    @Transactional
    fun insertTodo(todo: String): TodoEntity {
        val params: MutableMap<String, Any?> = mutableMapOf()
        params["todo"] = todo
        params["done"] = false
        val pk: Number = simpleJdbcInsert.executeAndReturnKey(params)
        return TodoEntity(
            id = pk.toLong(),
            todo = todo.orEmpty(),
            done = false
        )
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