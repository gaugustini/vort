package com.gaugustini.vort.database

import androidx.room.Dao
import androidx.room.Query
import com.gaugustini.vort.model.Decoration
import com.gaugustini.vort.model.SimpleDecoration

/**
 * The Data Access Object for [Decoration].
 */
@Dao
interface DecorationDao {

    /**
     * Returns a list of [Decoration] from the given skills.
     */
    @Query(
        """
        SELECT id, slots_required, skill_one, skill_one_points, skill_two, skill_two_points
        FROM decoration WHERE
        (hr <= :hunterRank OR village <= :villageRank) AND
        skill_one IN (:skill)
        """
    )
    suspend fun getDecorationList(
        hunterRank: Int,
        villageRank: Int,
        skill: List<Int>,
    ): List<SimpleDecoration>

    @Query("SELECT name FROM decoration WHERE id IN (:idList)")
    suspend fun getDecorationNameIdList(idList: List<Int>): List<Int>

}
