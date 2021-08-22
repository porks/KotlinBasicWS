package io.github.porks.kotlinkbasicws.controller

import io.github.porks.kotlinkbasicws.model.table.DataTable
import io.github.porks.kotlinkbasicws.service.DataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequestMapping("/data")
@RestController
class DataController {
    // Special row kind
    private val _systemrow = "__systemrow"

    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    @Autowired
    private lateinit var dataService: DataService

    // Print the JSON without any filter or formatting
    @GetMapping("/raw", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun raw(): String {
        createAccessTimeRow(dataService.table)
        return dataService.table.toJson()
    }

    // Filter the data and print the JSON
    @GetMapping("/query", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun query(
        @RequestParam queryParameters: Map<String, String>
    ): String {
        createAccessTimeRow(dataService.table)

        val normalizedQueryParams = normalizeQuery(queryParameters)

        val tableFiltered = DataTable()
        dataService.table.rows.filter { row ->
            (
                // Must match all params
                (normalizedQueryParams.all { param ->
                    // Match just one param's value is enough
                    param.value.any { paramValue ->
                        row.values[param.key]?.toUpperCase() == paramValue
                    }
                })
                // We don't filter system's row
                || (row.values[_systemrow] == "true")
            )
        }.forEach {
            tableFiltered.addRow(it.values)
        }

        return tableFiltered.toJson()
    }

    // Handle the queryParameters to make easy the filter
    fun normalizeQuery(queryParameters: Map<String, String> ): Map<String, Set<String>> {
        val normalized = HashMap<String, Set<String>>()
        queryParameters.forEach {
            val valuesExpanded = it.value.toUpperCase().split(",").toSet()
            normalized[it.key] = valuesExpanded
        }

        return normalized
    }

    // Create a DataRow with the current Date and Time
    fun createAccessTimeRow(table: DataTable) {
        val accessTime = LocalDateTime.now()
        val accessTimeFormatted = accessTime.format(dateFormatter)

        val accessTimeRow = hashMapOf("AccessTime" to accessTimeFormatted, _systemrow to "true")
        table.addRow(accessTimeRow)
    }
}