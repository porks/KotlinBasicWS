package io.github.porks.kotlinkbasicws.service

import io.github.porks.kotlinkbasicws.model.table.DataTable
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * A Service shared by the Controllers (RESTful: DataControler & WebView: InfoController)
 * Used to hold and manipulate the main table
 *
 * @author Marcelo Rossi
 */
@Service
class DataService {
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    // Main table with all data
    final val table = DataTable()

    // Information about access date and time
    final val accessTable = ArrayList<String>()

    init {
        preloadData(table)
    }

    // Create a DataRow with the current Date and Time
    fun createAccessTimeRow() {
        val accessTime = LocalDateTime.now()
        val accessTimeFormatted = accessTime.format(dateFormatter)
        accessTable.add(accessTimeFormatted)
    }

    private fun preloadData(table: DataTable) {

        val firstRow = hashMapOf("greeting" to "Hello!", "country" to "USA", "color" to "Blue",
            "size" to "Big", "size-km" to "9,833,520", "Calling code" to "+1", "Population" to "331,449,281"
        )
        table.addRow(firstRow)

        val secondRow = hashMapOf("greeting" to "Bonjour!", "country" to "France", "color" to "Blue",
            "size" to "Middle", "size-km" to "640,679", "Driving side" to "right", "Calling code" to "+33"
        )
        table.addRow(secondRow)

        val thirdRow = hashMapOf("greeting" to "Privet!", "country" to "Russia", "color" to "Red",
            "size" to "Huge", "size-km" to "17,098,246", "Driving side" to "right"
        )
        table.addRow(thirdRow)

        val fourthRow = hashMapOf("greeting" to "Bom Dia!", "country" to "Brazil", "color" to "Yellow",
            "size" to "Big", "size-km" to "8,515,767", "TimeZone" to "UTC-3"
        )
        table.addRow(fourthRow)

        val fifthRow = hashMapOf("greeting" to "Guten Morgen!", "country" to "Germany", "color" to "Yellow",
            "size" to "Small", "size-km" to "357,022", "TimeZone" to "UTC+1", "Population" to "83,190,556"
        )
        table.addRow(fifthRow)
    }
}