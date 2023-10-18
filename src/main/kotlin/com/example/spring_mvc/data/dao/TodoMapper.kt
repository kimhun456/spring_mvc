package com.example.spring_mvc.data.dao

import com.example.spring_mvc.data.entity.TodoEntity
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class TodoMapper : RowMapper<TodoEntity> {
    override fun mapRow(rs: ResultSet, rowNum: Int): TodoEntity {
        return TodoEntity(
            id = rs.getLong(TodoColumns.ID.value),
            todo = rs.getString(TodoColumns.TODO.value),
            done = rs.getBoolean(TodoColumns.DONE.value)
        )
    }
}