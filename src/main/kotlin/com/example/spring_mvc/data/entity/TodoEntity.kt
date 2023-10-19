package com.example.spring_mvc.data.entity

import io.swagger.v3.oas.annotations.media.Schema

data class TodoEntity(
    /**
     * Get id
     *
     * @return id
     */
    @Schema(example = "10", description = "")
    var id: Long = 0L,

    /**
     * Get TODO
     *
     * @return todo
     */
    @Schema(example = "TODO", description = "")
    var todo: String = "",

    /**
     * Get id
     *
     * @return done
     */
    @Schema(example = "true", description = "")
    var done: Boolean = false
)
