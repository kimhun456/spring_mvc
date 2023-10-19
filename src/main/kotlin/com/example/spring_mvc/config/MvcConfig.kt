package com.example.spring_mvc.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
@ComponentScan(basePackages = ["com.example.spring_mvc"])
@EnableWebMvc
class MvcConfig : WebMvcConfigurer {

    override fun configureDefaultServletHandling(configurer: DefaultServletHandlerConfigurer) {
        configurer.enable()
    }

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>?>) {
        val builder = Jackson2ObjectMapperBuilder()
        val converter = MappingJackson2HttpMessageConverter()
        val objectMapper = ObjectMapper()
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT)
        converter.setObjectMapper(objectMapper)
        builder.serializationInclusion(JsonInclude.Include.NON_NULL)
        builder.serializationInclusion(JsonInclude.Include.NON_EMPTY)

        // Add settings to converter, builder
        converters.add(converter)
        converters.add(MappingJackson2HttpMessageConverter(builder.build()))
    }

    override fun configureViewResolvers(registry: ViewResolverRegistry) {
        registry.jsp("/WEB-INF/views/", ".jsp")
    }
}