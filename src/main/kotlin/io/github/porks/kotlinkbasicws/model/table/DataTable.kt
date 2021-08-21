package io.github.porks.kotlinkbasicws.model.table

class DataTable {
    // Table columns - The columns are optional for the rows. A row can have just some columns (or all columns)
    private val columnModel = DataColumnModel()

    // Table rows
    private val rows = ArrayList<DataRow>()

    fun addRow(values: ArrayList<Pair<String, String>>) {
        // Add columns to table
        values.forEach { columnModel.addColumn(it.first) }

        // Create and add the new line
        rows.add(DataRow(values))
    }
}