package com.gaugustini.vort.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A class used to represent each result. The values are ID in their respective table in database.
 */
@Entity(tableName = "result")
data class Result(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val head: Int,
    val body: Int,
    val arms: Int,
    val waist: Int,
    val legs: Int,
    val decorations: Map<Int, Int> = mapOf(),
)
