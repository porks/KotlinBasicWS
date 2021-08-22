package io.github.porks.kotlinkbasicws.controller.restapi

import io.github.porks.kotlinkbasicws.model.table.DataTable
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequestMapping("/data")
@RestController
class DataController {
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    private val _table = DataTable()

    init {
        preloadData(_table)
    }

    // Print the JSON without any filter or formatting
    @GetMapping("/raw", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun raw(): String {
        createAccessTimeRow(_table)
        return _table.toJson()
    }

    // Filter the data and print the JSON
    @GetMapping("/query", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun query(
        @RequestParam queryParameters: Map<String, String>
    ): String {
        createAccessTimeRow(_table)

        val normalizedQueryParams = normalizeQuery(queryParameters)

        val tableFiltered = DataTable()
        _table.rows.filter { row ->
            row.values.filter { cell ->
                val queryValues = normalizedQueryParams[cell.key]
                queryValues?.any { it == cell.value.toUpperCase() } ?: false
            }.isNotEmpty()
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


        val accessTimeRow = hashMapOf("AccessTime" to accessTimeFormatted)
        table.addRow(accessTimeRow)
    }

    private fun preloadData(table: DataTable) {

        val firstRow = hashMapOf("greeting" to "Hello!", "country" to "USA", "color" to "Blue",
            "size" to "Big", "size-km" to "9,833,520"
        )
        table.addRow(firstRow)

        val secondRow = hashMapOf("greeting" to "Bonjour!", "country" to "France", "color" to "Blue",
            "size" to "Middle", "size-km" to "640,679"
        )
        table.addRow(secondRow)

        val thirdRow = hashMapOf("greeting" to "Privet!", "country" to "Russia", "color" to "Red",
            "size" to "Huge", "size-km" to "17,098,246"
        )
        table.addRow(thirdRow)

        val fourthRow = hashMapOf("greeting" to "Bom Dia!", "country" to "Brazil", "color" to "Yellow",
            "size" to "Big", "size-km" to "8,515,767"
        )
        table.addRow(fourthRow)

        val fifthRow = hashMapOf("greeting" to "Guten Morgen!", "country" to "Germany", "color" to "Yellow",
            "size" to "Small", "size-km" to "357,022"
        )
        table.addRow(fifthRow)
    }
}