package io.github.porks.kotlinkbasicws.model.table

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty

class DataRow (
    // These are the values in the row. A collection of pairs (Column Name, Value to show)
    @JsonProperty("row")
    val values: HashMap<String, String>
    ) {
}