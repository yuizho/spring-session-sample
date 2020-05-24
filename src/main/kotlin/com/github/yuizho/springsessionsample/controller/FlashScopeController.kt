package com.github.yuizho.springsessionsample.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.io.Serializable
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/flash-scope")
class FlashScopeController {
    @GetMapping
    fun index(model: Model, request: HttpServletRequest): String {
        model.addAttribute("host", request.getHeaders("X-App-Server-Host")?.nextElement())
        model.addAttribute("accountForm", AccountForm())
        return "flash_scope/index"
    }

    /**
     * This handler method receives form object and redirect to complete page.
     */
    @PostMapping("/post")
    fun post(model: Model, accountForm: AccountForm, redirectAttributes: RedirectAttributes): String {
        // this object will be stored HttpSession temporary
        // when the recirect is completed, the session will be discarded.
        redirectAttributes.addFlashAttribute(accountForm)
        println(accountForm)
        return "redirect:complete"
    }

    @GetMapping("/complete")
    fun complete(model: Model, accountForm: AccountForm, request: HttpServletRequest): String {
        model.addAttribute("host", request.getHeaders("X-App-Server-Host")?.nextElement())
        model.addAttribute("account", accountForm)
        return "flash_scope/complete"
    }
}

data class AccountForm(val name: String? = null) : Serializable
