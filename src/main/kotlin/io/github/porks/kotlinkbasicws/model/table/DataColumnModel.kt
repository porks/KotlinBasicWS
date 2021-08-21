package io.github.porks.kotlinkbasicws.model.table

class DataColumnModel {
    private val columns = HashSet<String>()

    fun addColumn(column: String) {
        columns.add(column)
    }
}