package com.gaugustini.vort.repository

import com.gaugustini.vort.database.LanguageDao
import javax.inject.Inject

class LanguageRepository @Inject constructor(private val languageDao: LanguageDao) {

    suspend fun getName(nameId: Int, language: Int): String {
        return when (language) {
            1 -> languageDao.getEnglishName(nameId) // English
            2 -> languageDao.getSpanishName(nameId) // Spanish
            3 -> languageDao.getFrenchName(nameId) // French
            4 -> languageDao.getItalianName(nameId) // Italian
            5 -> languageDao.getGermanName(nameId) // German
            else -> ""
        }
    }

    suspend fun getNameList(nameIdList: List<Int>, language: Int): List<String> {
        return when (language) {
            1 -> languageDao.getEnglishNameList(nameIdList) // English
            2 -> languageDao.getSpanishNameList(nameIdList) // Spanish
            3 -> languageDao.getFrenchNameList(nameIdList) // French
            4 -> languageDao.getItalianNameList(nameIdList) // Italian
            5 -> languageDao.getGermanNameList(nameIdList) // German
            else -> listOf()
        }
    }

}
