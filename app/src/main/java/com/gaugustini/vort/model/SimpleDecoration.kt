package com.gaugustini.vort.model

import androidx.room.ColumnInfo
import androidx.room.Ignore

/**
 * A class used to represent [ArmorPiece] with less data.
 */
data class SimpleDecoration(
    val id: Int,
    @ColumnInfo(name = "slots_required") val slotsRequired: Int,
    @ColumnInfo(name = "skill_one") val skillOne: Int,
    @ColumnInfo(name = "skill_one_points") val skillOnePoints: Int,
    @ColumnInfo(name = "skill_two") val skillTwo: Int?,
    @ColumnInfo(name = "skill_two_points") val skillTwoPoints: Int?,
) {

    @Ignore
    val skillCount = if (skillTwo != null) 1 else 2

}
