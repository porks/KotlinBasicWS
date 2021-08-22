package io.github.porks.kotlinkbasicws.controller

import io.github.porks.kotlinkbasicws.model.table.DataTable
import io.github.porks.kotlinkbasicws.service.DataService
import io.github.porks.kotlinkbasicws.service.QueryTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequestMapping("/data")
@RestController
class DataController {
    private val queryTable = QueryTable()

    @Autowired
    private lateinit var dataService: DataService

    // Print the JSON without any filter or formatting
    @GetMapping("/raw", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun raw(): String {
        dataService.createAccessTimeRow()
        return dataService.table.toJson()
    }

    // Filter the data and print the JSON
    @GetMapping("/query", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun query(
        @RequestParam queryParameters: Map<String, String>
    ): String {
        dataService.createAccessTimeRow()

        return queryTable.query(dataService.table, queryParameters).toJson()
    }
}