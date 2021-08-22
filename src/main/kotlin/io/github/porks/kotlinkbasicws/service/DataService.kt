package io.github.porks.kotlinkbasicws.service

import io.github.porks.kotlinkbasicws.model.table.DataTable
import org.springframework.stereotype.Service

@Service
class DataService {
    final val table = DataTable()

    init {
        preloadData(table)
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