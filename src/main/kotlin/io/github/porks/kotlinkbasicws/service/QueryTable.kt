package io.github.porks.kotlinkbasicws.service

import io.github.porks.kotlinkbasicws.model.table.DataTable
import org.springframework.stereotype.Service

class QueryTable {
    fun query(table: DataTable, queryParameters: Map<String, String>): DataTable {
        val normalizedQueryParams = normalizeQuery(queryParameters)

        val tableFiltered = DataTable()
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
}