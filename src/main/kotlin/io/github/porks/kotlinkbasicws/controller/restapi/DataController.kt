package io.github.porks.kotlinkbasicws.controller.restapi

import io.github.porks.kotlinkbasicws.model.table.DataTable
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RestController
class DataController {
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    private val _table = DataTable()

    init {
        preloadData(_table)
    }

    @GetMapping("/data/query", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun query(): String {
        createAccessTimeRow(_table)
        return _table.toJson()
    }

    fun createAccessTimeRow(table: DataTable) {
        val accessTime = LocalDateTime.now()
        val accessTimeFormatted = accessTime.format(dateFormatter)


        val accessTimeRow = hashMapOf("AccessTime" to accessTimeFormatted)
        table.addRow(accessTimeRow)
    }

    private fun preloadData(table: DataTable) {

        val firstRow = hashMapOf("Greeting" to "Hello!", "Language" to "English")
        table.addRow(firstRow)

        val secondRow = hashMapOf("Greeting" to "Bonjour!", "Language" to "French")
        table.addRow(secondRow)

        val thirdRow = hashMapOf("Greeting" to "Privet!", "Language" to "Russian")
        table.addRow(thirdRow)

        val fourthRow = hashMapOf("Greeting" to "Bom Dia!", "Language" to "Portuguese")
        table.addRow(fourthRow)

        val fifthRow = hashMapOf("Greeting" to "Guten Morgen!", "Language" to "German")
        table.addRow(fifthRow)
    }
}