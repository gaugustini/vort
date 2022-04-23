package com.gaugustini.vort.model

import androidx.room.Entity

/**
 * A class used to represent each skill, includes all skills (positives and negatives).
 * TODO: Change Skills to ID instead of nameId on Armor/Decoration in the database.
 */
@Entity(tableName = "skill", primaryKeys = ["id"])
data class Skill(
    val id: Int,
    val name: Int,
    val family: Int, // Skill Tree, e.g. Atk. Up (Large) belongs to family Atk.
    val points: Int,
    val type: Int,
)
