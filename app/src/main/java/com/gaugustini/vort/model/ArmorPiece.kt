package com.gaugustini.vort.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity

/**
 * A class used to represent each armor piece, contains proprieties that are used in [Head],
 * [Body], [Arms], [Waist], [Legs].
 */
data class ArmorPiece(
    val id: Int,
    val name: Int,
    val defense: Int,
    val fire: Int,
    val thunder: Int,
    val dragon: Int,
    val water: Int,
    val ice: Int,
    val gender: Int,
    val type: Int,
    val rarity: Int,
    @ColumnInfo(name = "hr") val hunterRank: Int,
    @ColumnInfo(name = "village") val villageRank: Int,
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
    val price: Int,
    @ColumnInfo(name = "material_one") val materialOne: Int?,
    @ColumnInfo(name = "amount_one") val amountOne: Int?,
    @ColumnInfo(name = "material_two") val materialTwo: Int?,
    @ColumnInfo(name = "amount_two") val amountTwo: Int?,
    @ColumnInfo(name = "material_three") val materialThree: Int?,
    @ColumnInfo(name = "amount_three") val amountThree: Int?,
    @ColumnInfo(name = "material_four") val materialFour: Int?,
    @ColumnInfo(name = "amount_four") val amountFour: Int?,
)

@Entity(tableName = "head", primaryKeys = ["id"])
data class Head(
    @Embedded val head: ArmorPiece,
)

@Entity(tableName = "body", primaryKeys = ["id"])
data class Body(
    @Embedded val body: ArmorPiece,
)

@Entity(tableName = "arms", primaryKeys = ["id"])
data class Arms(
    @Embedded val arms: ArmorPiece,
)

@Entity(tableName = "waist", primaryKeys = ["id"])
data class Waist(
    @Embedded val waist: ArmorPiece,
)

@Entity(tableName = "legs", primaryKeys = ["id"])
data class Legs(
    @Embedded val legs: ArmorPiece,
)
