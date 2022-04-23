package com.gaugustini.vort.database

import androidx.room.Dao
import androidx.room.Query
import com.gaugustini.vort.model.Skill

/**
 * The Data Access Object for [Skill].
 */
@Dao
interface SkillDao {

    /**
     * Returns a list of positive [Skill] for blademaster or gunner.
     */
    @Query("SELECT * FROM skill WHERE points >= 10 AND type IN (0, :type)")
    suspend fun getSkillList(type: Int): List<Skill>

}
