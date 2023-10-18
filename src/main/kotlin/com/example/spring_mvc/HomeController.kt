package com.example.spring_mvc

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.text.DateFormat
import java.util.*

@Controller
class HomeController {

    @GetMapping(value = ["/"])
    fun home(locale: Locale, model: Model): String {
        logger.info("Welcome home! The client locale is {}.", locale)
        val date = Date()
        val dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale)
        val formattedDate = dateFormat.format(date)
        model.addAttribute("serverTime", formattedDate)
        logger.info("formattedDate : $formattedDate")
        return "index"
    }

    companion object {
        private val logger = LoggerFactory.getLogger(HomeController::class.java)
    }
}