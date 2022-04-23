package com.gaugustini.vort.model

/**
 * A class used to represent full armor without decorations.
 */
data class Armor(
    val head: SimpleArmorPiece,
    val body: SimpleArmorPiece,
    val arms: SimpleArmorPiece,
    val waist: SimpleArmorPiece,
    val legs: SimpleArmorPiece,
)
