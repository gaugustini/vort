package com.gaugustini.vort.model

/**
 * A class used to represent each result with names in the values.
 */
data class ResultWithNames(
    val head: String,
    val body: String,
    val arms: String,
    val waist: String,
    val legs: String,
    val decorations: List<Pair<String, Int>>,
)
