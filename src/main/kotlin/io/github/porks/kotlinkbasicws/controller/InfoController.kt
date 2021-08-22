package io.github.porks.kotlinkbasicws.controller

import io.github.porks.kotlinkbasicws.service.DataService
import io.github.porks.kotlinkbasicws.service.TableView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class InfoController {
    @Autowired
    private lateinit var dataService: DataService

    // Index page with human-readable information
    @GetMapping("/")
    fun info(model: Model): String {
        dataService.createAccessTimeRow()

        model.addAttribute("tableView", TableView(dataService.table))
        model.addAttribute("accessInfo", dataService.accessTable)
        return "index"
    }
}