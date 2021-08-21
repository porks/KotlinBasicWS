package io.github.porks.kotlinkbasicws.model.table

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

class DataTable {
    // Table columns - The columns are optional for the rows. A row can have just some columns (or all columns)
    private val columnModel = DataColumnModel()

    // Table rows
    val rows = ArrayList<DataRow>()

    fun addRow(values: HashMap<String, String>) {
        // Add columns to table
        values.forEach { columnModel.addColumn(it.key) }

        // Create and add the new line
        rows.add(DataRow(values))
    }

    // Transform the object in a JSON String
    fun toJson(): String {
        val json = jacksonObjectMapper()
        return json.writeValueAsString(rows)
    }
}