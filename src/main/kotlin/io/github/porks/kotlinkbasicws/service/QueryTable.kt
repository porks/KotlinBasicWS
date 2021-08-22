package io.github.porks.kotlinkbasicws.service

import io.github.porks.kotlinkbasicws.model.table.DataTable
import java.net.URLDecoder

/**
 * Aggregate the query utilities
 *
 * @author Marcelo Rossi
 */
class QueryTable {
    fun query(table: DataTable, queryString: String): DataTable {
        val queryParams = extractQueryParams(queryString)

        return query(table, queryParams)
    }

    fun query(table: DataTable, queryParameters: Map<String, String>): DataTable {
        val normalizedQueryParams = normalizeQuery(queryParameters)

        val tableFiltered = DataTable()

        // Has all the original table columns (in the same sequence)
        tableFiltered.columnModel.columns.addAll(table.columnModel.columns)

        table.rows.filter { row ->
            // Must match all params
            normalizedQueryParams.all { param ->
                // Match just one param's value is enough
                param.value.any { paramValue ->
                    row.values[param.key]?.toUpperCase() == paramValue
                }
            }
        }.forEach {
            tableFiltered.addRow(it.values)
        }

        return tableFiltered
    }

    // Handle the queryParameters to make easy the filter
    private fun normalizeQuery(queryParameters: Map<String, String> ): Map<String, Set<String>> {
        val normalized = HashMap<String, Set<String>>()
        queryParameters.forEach {
            val valuesExpanded = it.value.toUpperCase().split(",").toSet()
            normalized[it.key] = valuesExpanded
        }

        return normalized
    }

    // Parses the string and return a valid query string or null
    fun parseQuery(inputString: String): String {
        // Removes the beginning "query="
        var queryString = inputString.removePrefix("query=")

        // Decode
        queryString = URLDecoder.decode(queryString, "UTF-8")

        // Parse the string creating a Map, this removes the invalid tuples
        val queryParams = extractQueryParams(queryString)

        // returns a string with the valid key=value parsed
        return queryParams.map { it.key + "=" + it.value }.joinToString("&")
    }

    // Extracts the query params Key/Value
    private fun extractQueryParams(queryString: String): Map<String, String> {
        val queryParams = HashMap<String, String>()
        for (tuple in queryString.split("&")) {
            val listTuple = tuple.split("=")
            if (listTuple.size != 2)
                continue

            queryParams[listTuple[0]] = listTuple[1]
        }

        return queryParams
    }
}