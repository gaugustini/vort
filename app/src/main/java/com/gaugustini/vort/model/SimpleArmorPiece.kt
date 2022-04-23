package com.gaugustini.vort.model

import androidx.room.ColumnInfo

/**
 * A class used to represent [ArmorPiece] with less data.
 */
data class SimpleArmorPiece(
    val id: Int,
    val slots: Int,
    @ColumnInfo(name = "skill_one") val skillOne: Int?,
    @ColumnInfo(name = "skill_one_points") val skillOnePoints: Int?,
    @ColumnInfo(name = "skill_two") val skillTwo: Int?,
    @ColumnInfo(name = "skill_two_points") val skillTwoPoints: Int?,
    @ColumnInfo(name = "skill_three") val skillThree: Int?,
    @ColumnInfo(name = "skill_three_points") val skillThreePoints: Int?,
    @ColumnInfo(name = "skill_four") val skillFour: Int?,
    @ColumnInfo(name = "skill_four_points") val skillFourPoints: Int?,
    @ColumnInfo(name = "skill_five") val skillFive: Int?,
    @ColumnInfo(name = "skill_five_points") val skillFivePoints: Int?,
) {

    /**
     * Returns points from a given skill.
     */
    fun getPoints(skill: Int): Int {
        if (skill == skillOne) return skillOnePoints ?: 0
        if (skill == skillTwo) return skillTwoPoints ?: 0
        if (skill == skillThree) return skillThreePoints ?: 0
        if (skill == skillFour) return skillFourPoints ?: 0
        if (skill == skillFive) return skillFivePoints ?: 0
        return 0
    }

}
