package com.github.yuizho.springsessionsample.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest


@Controller
@RequestMapping("/hello")
class HelloController {
    @GetMapping
    fun get(model: Model, request: HttpServletRequest): String {
        val headerNames = request.headerNames
        while(headerNames.hasMoreElements()) {
            val name = headerNames.nextElement() as String
            val headers = request.getHeaders(name)
            while(headers.hasMoreElements()) {
                val header = headers.nextElement() as String
                println("""$name:$header""")
            }
        }
        return "hello/index.html"
    }
}