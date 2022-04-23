package com.gaugustini.vort.domain

import com.gaugustini.vort.model.Result
import com.gaugustini.vort.model.ResultWithNames
import com.gaugustini.vort.repository.ArmorPieceRepository
import com.gaugustini.vort.repository.DecorationRepository
import com.gaugustini.vort.repository.LanguageRepository
import com.gaugustini.vort.repository.ResultRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ResultWithNamesCase @Inject constructor(
    private val resultRepository: ResultRepository,
    private val languageRepository: LanguageRepository,
    private val armorPieceRepository: ArmorPieceRepository,
    private val decorationRepository: DecorationRepository,
) {

    private var headList = mapOf<Int, String>()
    private var bodyList = mapOf<Int, String>()
    private var armsList = mapOf<Int, String>()
    private var waistList = mapOf<Int, String>()
    private var legsList = mapOf<Int, String>()
    private var decorationList = mapOf<Int, String>()

    fun getResultListWithNames(language: Int): Flow<List<ResultWithNames>> = flow {
        resultRepository.getResultList().collect { list ->
            withContext(Dispatchers.IO) { loadLists(list, language) }
            this.emit(buildList { list.forEach { result -> add(toResultWithNames(result)) } })
        }
    }

    private suspend fun loadLists(resultList: List<Result>, language: Int) {
        var idList: List<Int> =
            buildList { resultList.forEach { if (!this.contains(it.head)) add(it.head) } }
        var nameIdList = armorPieceRepository.getNameIdList(idList, 1)
        var names: List<String> = languageRepository.getNameList(nameIdList, language)
        headList = buildMap { idList.forEachIndexed { index, id -> this[id] = names[index] } }

        idList = buildList { resultList.forEach { if (!this.contains(it.body)) add(it.body) } }
        nameIdList = armorPieceRepository.getNameIdList(idList, 2)
        names = languageRepository.getNameList(nameIdList, language)
        bodyList = buildMap { idList.forEachIndexed { index, id -> this[id] = names[index] } }

        idList = buildList { resultList.forEach { if (!this.contains(it.arms)) add(it.arms) } }
        nameIdList = armorPieceRepository.getNameIdList(idList, 3)
        names = languageRepository.getNameList(nameIdList, language)
        armsList = buildMap { idList.forEachIndexed { index, id -> this[id] = names[index] } }

        idList = buildList { resultList.forEach { if (!this.contains(it.waist)) add(it.waist) } }
        nameIdList = armorPieceRepository.getNameIdList(idList, 4)
        names = languageRepository.getNameList(nameIdList, language)
        waistList = buildMap { idList.forEachIndexed { index, id -> this[id] = names[index] } }

        idList = buildList { resultList.forEach { if (!this.contains(it.legs)) add(it.legs) } }
        nameIdList = armorPieceRepository.getNameIdList(idList, 5)
        names = languageRepository.getNameList(nameIdList, language)
        legsList = buildMap { idList.forEachIndexed { index, id -> this[id] = names[index] } }

        idList = buildList {
            resultList.forEach {
                it.decorations.forEach { (key, _) -> if (!this.contains(key)) this.add(key) }
            }
        }.sorted()
        nameIdList = decorationRepository.getNameIdList(idList)
        names = languageRepository.getNameList(nameIdList, language)
        decorationList = buildMap { idList.forEachIndexed { index, id -> this[id] = names[index] } }

    }

    private fun toResultWithNames(result: Result): ResultWithNames {
        return ResultWithNames(
            head = headList[result.head] as String,
            body = bodyList[result.body] as String,
            arms = armsList[result.arms] as String,
            waist = waistList[result.waist] as String,
            legs = legsList[result.legs] as String,
            decorations = buildList {
                result.decorations.forEach { (key, value) ->
                    this.add(Pair(decorationList[key].toString(), value))
                }
            }
        )
    }

}
