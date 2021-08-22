package io.github.porks.kotlinkbasicws.service

// A read-only view for DataService to be used on the presentation application layer
class TableView(private val dataService: DataService) {
    fun columns(): Set<String> {
        return dataService.table.columnModel.columns
    }

    // Returns a list of String with the value for each column in the columnModel
    // If the row hasn't the column value an empty string is returned
    @Suppress("unused")
    fun rows(): List<List<String>> {
        return dataService.table.rows.map { row ->
            dataService.table.columnModel.columns.map { col ->
                row.values[col] ?: ""
            }
        }
    }

    fun accessInfo(): List<String> {
        return dataService.accessTable
    }
}