package io.github.porks.kotlinkbasicws.service

import io.github.porks.kotlinkbasicws.model.table.DataTable
import org.springframework.beans.factory.annotation.Autowired

/**
 * A read-only view for DataService to be used on the presentation application layer
 *
 * @author Marcelo Rossi
 */
class TableView(private val table: DataTable) {
    private val queryTable = QueryTable()

    fun columns(): Set<String> {
        return table.columnModel.columns
    }

    // Returns a list of String with the value for each column in the columnModel
    // If the row hasn't the column value an empty string is returned
    @Suppress("unused")
    fun rows(): List<List<String>> {
        return table.rows.map { row ->
            table.columnModel.columns.map { col ->
                row.values[col] ?: ""
            }
        }
    }

    // Filters the main Table and returns a new TableView/DataService with the resulting rows
    fun query(queryString: String): TableView {
        val filteredTable = queryTable.query(table, queryString)
        return TableView(filteredTable)
    }
}