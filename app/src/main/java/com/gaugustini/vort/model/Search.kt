package com.gaugustini.vort.model

/**
 * A class used to represent the search query.
 */
data class Search(
    val hunterRank: Int,
    val villageRank: Int,
    val weaponSlot: Int,
    val gender: Int, // 1 = Male, 2 = Female
    val type: Int, // 1 = Blademaster, Gunner = 2
    val skills: List<Skill>,
) {

    /**
     * Returns Skill Family List.
     */
    fun getSkillList(): List<Int> = buildList {
        skills.forEach { add(it.family) }
    }

    /**
     * Returns Map(key= SkillID, value= Points to Activate Skill).
     */
    fun getPointsToActivateSkills(): Map<Int, Int> = buildMap {
        skills.forEach { this[it.family] = it.points }
    }

}
