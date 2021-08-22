package io.github.porks.kotlinkbasicws.model.table

class DataColumnModel {
    val columns = HashSet<String>()

    fun addColumn(column: String) {
        columns.add(column)
    }
}