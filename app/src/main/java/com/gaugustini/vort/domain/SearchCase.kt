package com.gaugustini.vort.domain

import com.gaugustini.vort.model.*
import com.gaugustini.vort.repository.ArmorPieceRepository
import com.gaugustini.vort.repository.DecorationRepository
import com.gaugustini.vort.repository.ResultRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

// TODO: Set limit to the amount of tests or solution added to list.
class SearchCase @Inject constructor(
    private val armorPieceRepository: ArmorPieceRepository,
    private val decorationRepository: DecorationRepository,
    private val resultRepository: ResultRepository,
) {

    private val solutionCase = SolutionCase()
    private var headList = listOf<SimpleArmorPiece>()
    private var bodyList = listOf<SimpleArmorPiece>()
    private var armsList = listOf<SimpleArmorPiece>()
    private var waistList = listOf<SimpleArmorPiece>()
    private var legsList = listOf<SimpleArmorPiece>()
    private var decorationList = listOf<SimpleDecoration>()
    private var resultList = mutableListOf<Result>()

    suspend fun search(search: Search) {
        resultList.clear()
        withContext(Dispatchers.IO) {
            resultRepository.clear()
            getLists(search)
        }
        withContext(Dispatchers.Default) { createTests(search) }
        withContext(Dispatchers.IO) { resultRepository.insert(resultList) }
    }

    private suspend fun getLists(search: Search) {
        headList = armorPieceRepository.getArmorPieceList(1, search)
        bodyList = armorPieceRepository.getArmorPieceList(2, search)
        armsList = armorPieceRepository.getArmorPieceList(3, search)
        waistList = armorPieceRepository.getArmorPieceList(4, search)
        legsList = armorPieceRepository.getArmorPieceList(5, search)
        decorationList = decorationRepository.getDecorationList(search)
    }

    private fun createTests(search: Search) {
        loop@ for (head in headList) {
            for (body in bodyList) {
                for (arms in armsList) {
                    for (waist in waistList) {
                        for (legs in legsList) {
                            test(search = search, armor = Armor(head, body, arms, waist, legs))
                        }
                    }
                }
            }
        }
    }

    private fun test(search: Search, armor: Armor) {
        solutionCase.search(
            weaponSlot = search.weaponSlot,
            armor = armor,
            decorationList = decorationList,
            skills = search.getPointsToActivateSkills()
        )?.let { resultList.add(it) }
    }

}
