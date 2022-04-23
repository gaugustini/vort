package com.gaugustini.vort.model

import androidx.room.Entity

/**
 * A class used to represent names in different languages of an armor, decoration, skill or material.
 * It is used in [Head], [Body], [Arms], [Waist], [Legs], [Decoration], [Skill].
 */
@Entity(tableName = "language", primaryKeys = ["id"])
data class Language(
    val id: Int,
    val english: String,
    val spanish: String,
    val french: String,
    val italian: String,
    val german: String,
)
