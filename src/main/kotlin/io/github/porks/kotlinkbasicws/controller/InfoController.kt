package io.github.porks.kotlinkbasicws.controller

import io.github.porks.kotlinkbasicws.model.InfoQuery
import io.github.porks.kotlinkbasicws.model.NewDataRow
import io.github.porks.kotlinkbasicws.service.DataService
import io.github.porks.kotlinkbasicws.service.QueryTable
import io.github.porks.kotlinkbasicws.service.TableView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

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

    var newDataRow = NewDataRow()

    // Index page with human-readable information
    @GetMapping("/")
    fun index(model: Model): String {
        dataService.createAccessTimeRow()

        model.addAttribute("tableView", TableView(dataService.table))
        model.addAttribute("accessInfo", dataService.accessTable)
        model.addAttribute("infoQuery", infoQuery)
        model.addAttribute("newDataRow", newDataRow)

        return "index"
    }

    // Index page with human-readable information
    @PostMapping("/infoQueryExecute")
    fun infoQueryExecute( //
        model: Model, //
        @ModelAttribute("customer") formInfoQuery: InfoQuery //
    ): String {
        dataService.createAccessTimeRow()

        // Persist the newQueryString, if it is valid
        infoQuery.query = queryTable.parseQuery(formInfoQuery.query)

        return "redirect:/"
    }

    // Index page with human-readable information
    @PostMapping("/newData")
    fun newData( //
        model: Model, //
        @ModelAttribute("newDataRow") newDataRow: NewDataRow //
    ): String {
        dataService.createAccessTimeRow()

        // Check if there is any data and add the new row
        if (newDataRow.values.isNotEmpty() && newDataRow.values.any { it.value.isNotEmpty() })
            dataService.table.addRow(newDataRow.values)

        return "redirect:/"
    }
}