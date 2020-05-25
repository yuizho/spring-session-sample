package com.github.yuizho.springsessionsample.controller.sessionbean

import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.context.annotation.SessionScope
import java.io.Serializable
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/session-bean")
class LoginController(private val cart: Cart) {
    @GetMapping
    fun index(model: Model, request: HttpServletRequest): String {
        model.addAttribute("host", request.getHeaders("X-App-Server-Host")?.nextElement())
        model.addAttribute("cart", cart)
        return "session_bean/index.html"
    }

    @PostMapping("/add")
    fun add(model: Model,
            @RequestParam("itemName") name: String,
            @RequestParam("itemCount") count: Int): String {
        cart.itemName = name
        cart.itemCount = count
        return "redirect:/session-bean"
    }

    @PostMapping("/reset")
    fun reset(): String {
        cart.reset()
        return "redirect:/session-bean"
    }
}

@Component
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
data class Cart(var itemName: String? = null,
                var itemCount: Int? = 0
) : Serializable {
    fun reset(): Unit {
        itemName = null
        itemCount = 0
    }
}