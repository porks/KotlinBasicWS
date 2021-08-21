package io.github.porks.kotlinkbasicws.controller.restapi

import io.github.porks.kotlinkbasicws.model.table.DataRow
import io.github.porks.kotlinkbasicws.model.table.DataTable
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DataController {
    @GetMapping("/data/query", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun query(): String {
        val table = DataTable()
        fakeData(table)

        return table.toJson()
    }

    fun fakeData(table: DataTable) {

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