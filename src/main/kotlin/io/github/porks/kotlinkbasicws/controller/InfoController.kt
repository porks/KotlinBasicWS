package io.github.porks.kotlinkbasicws.controller

import io.github.porks.kotlinkbasicws.model.InfoQuery
import io.github.porks.kotlinkbasicws.service.DataService
import io.github.porks.kotlinkbasicws.service.QueryTable
import io.github.porks.kotlinkbasicws.service.TableView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.net.URLDecoder

/**
 * WebView Controller used by the index page
 *
 * @author Marcelo Rossi
 */
@Controller
class InfoController {
    @Autowired
    private lateinit var dataService: DataService

    val queryTable = QueryTable()

    var infoQuery = InfoQuery("size=big,huge,middle&color=red,blue")

    // Index page with human-readable information
    @GetMapping("/")
    fun index(model: Model): String {
        dataService.createAccessTimeRow()

        model.addAttribute("tableView", TableView(dataService.table))
        model.addAttribute("accessInfo", dataService.accessTable)
        model.addAttribute("infoQuery", infoQuery)

        return "index"
    }

    // Index page with human-readable information
    @PostMapping("/")
    fun infoQueryExecute( //
        model: Model, //
        @RequestBody newQueryString: String): String {
        dataService.createAccessTimeRow()

        // Persist the newQueryString, if it is valid
        infoQuery.query = queryTable.parseQuery(newQueryString)

        model.addAttribute("tableView", TableView(dataService.table))
        model.addAttribute("accessInfo", dataService.accessTable)
        model.addAttribute("infoQuery", infoQuery)

        return "index"
    }
}