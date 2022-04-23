package com.gaugustini.vort.repository

import com.gaugustini.vort.database.ArmorPieceDao
import com.gaugustini.vort.model.Search
import com.gaugustini.vort.model.SimpleArmorPiece
import javax.inject.Inject

class ArmorPieceRepository @Inject constructor(private val armorPieceDao: ArmorPieceDao) {

    // TODO: Add armor pieces with 3 slots and empty skill in query
    suspend fun getArmorPieceList(piece: Int, search: Search): List<SimpleArmorPiece> = getBestList(
        when (piece) {
            // Head
            1 -> armorPieceDao.getHeadList(
                search.hunterRank,
                search.villageRank,
                search.gender,
                search.type,
                search.getSkillList()
            )
            // Body
            2 -> armorPieceDao.getBodyList(
                search.hunterRank,
                search.villageRank,
                search.gender,
                search.type,
                search.getSkillList()
            )
            // Arms
            3 -> armorPieceDao.getArmsList(
                search.hunterRank,
                search.villageRank,
                search.gender,
                search.type,
                search.getSkillList()
            )
            // Waist
            4 -> armorPieceDao.getWaistList(
                search.hunterRank,
                search.villageRank,
                search.gender,
                search.type,
                search.getSkillList()
            )
            // Legs
            5 -> armorPieceDao.getLegsList(
                search.hunterRank,
                search.villageRank,
                search.gender,
                search.type,
                search.getSkillList()
            )
            else -> listOf()
        },
        search.getSkillList()
    )

    suspend fun getNameIdList(idList: List<Int>, piece: Int): List<Int> = when (piece) {
        1 -> armorPieceDao.getHeadNameIdList(idList) // Head
        2 -> armorPieceDao.getBodyNameIdList(idList) // Body
        3 -> armorPieceDao.getArmsNameIdList(idList) // Arms
        4 -> armorPieceDao.getWaistNameIdList(idList) // Waist
        5 -> armorPieceDao.getLegsNameIdList(idList) // Legs
        else -> listOf()
    }

    private fun getBestList(
        armorPieceList: List<SimpleArmorPiece>,
        skills: List<Int>
    ): List<SimpleArmorPiece> {
        val list = armorPieceList.toMutableList()

        for (pieceA in armorPieceList) {
            for (pieceB in armorPieceList) {
                if (bestArmorPiece(pieceA, pieceB, skills) &&
                    !bestArmorPiece(pieceB, pieceA, skills) &&
                    list.indexOf(pieceB) >= 0
                ) {
                    list.removeAt(list.indexOf(pieceB))
                }
            }
        }

        return list
    }

    private fun bestArmorPiece(
        pieceA: SimpleArmorPiece,
        pieceB: SimpleArmorPiece,
        skills: List<Int>
    ): Boolean {
        if (pieceA.slots > pieceB.slots) {
            return true
        }

        skills.forEach { skill ->
            if (pieceA.getPoints(skill) > pieceB.getPoints(skill)) {
                return true
            }
        }

        return false
    }

}
