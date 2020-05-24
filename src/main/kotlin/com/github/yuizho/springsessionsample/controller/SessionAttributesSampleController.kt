package com.github.yuizho.springsessionsample.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/session-attr")
// model内のuserSession属性の値をsessionに突っ込む
@SessionAttributes(names = ["userSession"])
class SessionAttributesSampleController {
    @GetMapping
    fun index(model: Model, request: HttpServletRequest): String {
        model.addAttribute("host", request.getHeaders("X-App-Server-Host")?.nextElement())
        return if (model.getAttribute("userSession") != null) {
            println("logged in")
            "session_attr/loggedin.html"
        } else {
            println("not logged in")
            model.addAttribute("loginForm", LoginForm())
            "session_attr/index.html"
        }
    }

    @PostMapping("/login")
    fun login(model: Model, loginForm: LoginForm): String {
        if (loginForm.id?.equals("userA") == true
                && loginForm.password?.equals("password") == true) {
            println("authentication succeeded")
            model.addAttribute("userSession", LoginUser(true, loginForm.id))
        }
        return "redirect:/session-attr"
    }

    @PostMapping("/logout")
    fun logout(sessionStatus: SessionStatus): String {
        sessionStatus.setComplete()
        return "redirect:/session-attr"
    }

    // これでもModelの中にuserSessionという属性を増やせるけど、
    // セッション情報とかはModelAttributeより普通にmodelに入れたほうが
    // 初期化のタイミングコントロールしやすい気がするけど
    /*
    @ModelAttribute("userSession")
    fun initLoginUser(): LoginUser {
        return LoginUser(false)
    }
    */
}

data class LoginForm(val id: String? = null, val password: String? = null)

data class LoginUser(val loggedin: Boolean, val id: String? = null)