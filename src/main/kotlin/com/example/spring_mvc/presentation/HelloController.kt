package com.example.spring_mvc.presentation

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HelloController {

    private val logger = LoggerFactory.getLogger(HelloController::class.java)

    @GetMapping(value = ["/hello"])
    fun hello(name: String?, model: Model): String {
        println("name : $name")
        model.addAttribute("name", name)
        return "hello"
    }
}