package com.gaugustini.vort.repository

import com.gaugustini.vort.database.DecorationDao
import com.gaugustini.vort.model.Search
import com.gaugustini.vort.model.SimpleDecoration
import javax.inject.Inject

class DecorationRepository @Inject constructor(private val decorationDao: DecorationDao) {

    suspend fun getDecorationList(search: Search): List<SimpleDecoration> = getBestList(
        decorationDao.getDecorationList(
            search.hunterRank,
            search.villageRank,
            search.getSkillList()
        )
    )

    suspend fun getNameIdList(idList: List<Int>): List<Int> =
        decorationDao.getDecorationNameIdList(idList)

    private fun getBestList(
        decorationList: List<SimpleDecoration>
    ): List<SimpleDecoration> {
        val list = decorationList.toMutableList()

        for (decorationA in decorationList) {
            for (decorationB in decorationList) {
                if (bestDecoration(decorationA, decorationB) &&
                    !bestDecoration(decorationB, decorationA) &&
                    list.indexOf(decorationB) >= 0
                ) {
                    list.removeAt(list.indexOf(decorationB))
                }
            }
        }

        return list
    }

    private fun bestDecoration(
        decorationA: SimpleDecoration,
        decorationB: SimpleDecoration
    ): Boolean {
        if (decorationA.slotsRequired < decorationB.slotsRequired
            || decorationA.skillOne != decorationB.skillOne
        ) {
            return true
        }

        val a = decorationA.skillOnePoints * decorationB.slotsRequired
        val b = decorationB.skillOnePoints * decorationA.slotsRequired

        return if (a != b) a > b else notWorse(decorationA, decorationB)
    }

    private fun notWorse(
        decorationA: SimpleDecoration,
        decorationB: SimpleDecoration,
    ): Boolean {
        val a = decorationA.skillTwoPoints?.times(decorationB.slotsRequired) ?: 0

        val b = decorationB.skillTwoPoints?.times(decorationA.slotsRequired) ?: 0

        return decorationB.skillCount == 2 && (decorationA.skillCount == 1 || a < b)
    }

}
