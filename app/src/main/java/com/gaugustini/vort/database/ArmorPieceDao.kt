package com.gaugustini.vort.database

import androidx.room.Dao
import androidx.room.Query
import com.gaugustini.vort.model.*

/**
 * The Data Access Object for [Head], [Body], [Arms], [Waist], [Legs].
 */
@Dao
interface ArmorPieceDao {

    /**
     * Returns a list of [Head] from the given skills.
     */
    @Query(
        """
        SELECT id, slots, skill_one, skill_one_points, skill_two, skill_two_points,skill_three, 
        skill_three_points, skill_four, skill_four_points, skill_five, skill_five_points
        FROM head WHERE
        (hr <= :hunterRank OR village <= :villageRank) AND
        gender IN (0, :gender) AND
        type IN (0, :type) AND
        (skill_one IN (:skills) AND skill_one_points > 0 OR
        skill_two IN (:skills) AND skill_two_points > 0 OR
        skill_three IN (:skills) AND skill_three_points > 0 OR
        skill_four IN (:skills) AND skill_four_points > 0 OR
        skill_five IN (:skills) AND skill_five_points > 0)
        """
    )
    suspend fun getHeadList(
        hunterRank: Int,
        villageRank: Int,
        gender: Int,
        type: Int,
        skills: List<Int>,
    ): List<SimpleArmorPiece>

    /**
     * Returns a list of [Body] from the given skills.
     */
    @Query(
        """
        SELECT id, slots, skill_one, skill_one_points, skill_two, skill_two_points,skill_three, 
        skill_three_points, skill_four, skill_four_points, skill_five, skill_five_points
        FROM body WHERE
        (hr <= :hunterRank OR village <= :villageRank) AND
        gender IN (0, :gender) AND
        type IN (0, :type) AND
        (skill_one IN (:skills) AND skill_one_points > 0 OR
        skill_two IN (:skills) AND skill_two_points > 0 OR
        skill_three IN (:skills) AND skill_three_points > 0 OR
        skill_four IN (:skills) AND skill_four_points > 0 OR
        skill_five IN (:skills) AND skill_five_points > 0)
        """
    )
    suspend fun getBodyList(
        hunterRank: Int,
        villageRank: Int,
        gender: Int,
        type: Int,
        skills: List<Int>,
    ): List<SimpleArmorPiece>

    /**
     * Returns a list of [Arms] from the given skills.
     */
    @Query(
        """
        SELECT id, slots, skill_one, skill_one_points, skill_two, skill_two_points,skill_three, 
        skill_three_points, skill_four, skill_four_points, skill_five, skill_five_points
        FROM arms WHERE
        (hr <= :hunterRank OR village <= :villageRank) AND
        gender IN (0, :gender) AND
        type IN (0, :type) AND
        (skill_one IN (:skills) AND skill_one_points > 0 OR
        skill_two IN (:skills) AND skill_two_points > 0 OR
        skill_three IN (:skills) AND skill_three_points > 0 OR
        skill_four IN (:skills) AND skill_four_points > 0 OR
        skill_five IN (:skills) AND skill_five_points > 0)
        """
    )
    suspend fun getArmsList(
        hunterRank: Int,
        villageRank: Int,
        gender: Int,
        type: Int,
        skills: List<Int>,
    ): List<SimpleArmorPiece>

    /**
     * Returns a list of [Waist] from the given skills.
     */
    @Query(
        """
        SELECT id, slots, skill_one, skill_one_points, skill_two, skill_two_points,skill_three, 
        skill_three_points, skill_four, skill_four_points, skill_five, skill_five_points
        FROM waist WHERE
        (hr <= :hunterRank OR village <= :villageRank) AND
        gender IN (0, :gender) AND
        type IN (0, :type) AND
        (skill_one IN (:skills) AND skill_one_points > 0 OR
        skill_two IN (:skills) AND skill_two_points > 0 OR
        skill_three IN (:skills) AND skill_three_points > 0 OR
        skill_four IN (:skills) AND skill_four_points > 0 OR
        skill_five IN (:skills) AND skill_five_points > 0)
        """
    )
    suspend fun getWaistList(
        hunterRank: Int,
        villageRank: Int,
        gender: Int,
        type: Int,
        skills: List<Int>,
    ): List<SimpleArmorPiece>

    /**
     * Returns a list of [Legs] from the given skills.
     */
    @Query(
        """
        SELECT id, slots, skill_one, skill_one_points, skill_two, skill_two_points,skill_three, 
        skill_three_points, skill_four, skill_four_points, skill_five, skill_five_points
        FROM legs WHERE
        (hr <= :hunterRank OR village <= :villageRank) AND
        gender IN (0, :gender) AND
        type IN (0, :type) AND
        (skill_one IN (:skills) AND skill_one_points > 0 OR
        skill_two IN (:skills) AND skill_two_points > 0 OR
        skill_three IN (:skills) AND skill_three_points > 0 OR
        skill_four IN (:skills) AND skill_four_points > 0 OR
        skill_five IN (:skills) AND skill_five_points > 0)
        """
    )
    suspend fun getLegsList(
        hunterRank: Int,
        villageRank: Int,
        gender: Int,
        type: Int,
        skills: List<Int>,
    ): List<SimpleArmorPiece>

    @Query("SELECT name FROM head WHERE id IN (:idList)")
    suspend fun getHeadNameIdList(idList: List<Int>): List<Int>

    @Query("SELECT name FROM body WHERE id IN (:idList)")
    suspend fun getBodyNameIdList(idList: List<Int>): List<Int>

    @Query("SELECT name FROM arms WHERE id IN (:idList)")
    suspend fun getArmsNameIdList(idList: List<Int>): List<Int>

    @Query("SELECT name FROM waist WHERE id IN (:idList)")
    suspend fun getWaistNameIdList(idList: List<Int>): List<Int>

    @Query("SELECT name FROM legs WHERE id IN (:idList)")
    suspend fun getLegsNameIdList(idList: List<Int>): List<Int>

}
