package io.github.porks.kotlinkbasicws.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class Info {
    @GetMapping("/info")
    fun info(model: Model): String {
        model.addAttribute("mainName", "Marcelo Rossi")
        model.addAttribute("mainAge", 37)
        return "info"
    }
}