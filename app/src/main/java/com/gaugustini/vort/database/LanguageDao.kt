package com.gaugustini.vort.database

import androidx.room.Dao
import androidx.room.Query
import com.gaugustini.vort.model.Language

/**
 * The Data Access Object for [Language].
 */
@Dao
interface LanguageDao {

    /**
     * Returns a name in English.
     */
    @Query("SELECT english FROM language WHERE id = :id")
    suspend fun getEnglishName(id: Int): String

    /**
     * Returns list of names in English.
     */
    @Query("SELECT english FROM language WHERE id IN (:idList)")
    suspend fun getEnglishNameList(idList: List<Int>): List<String>

    /**
     * Returns a name in Spanish.
     */
    @Query("SELECT spanish FROM language WHERE id = :id")
    suspend fun getSpanishName(id: Int): String

    /**
     * Returns list of names in Spanish.
     */
    @Query("SELECT spanish FROM language WHERE id IN (:idList)")
    suspend fun getSpanishNameList(idList: List<Int>): List<String>

    /**
     * Returns a name in French.
     */
    @Query("SELECT french FROM language WHERE id = :id")
    suspend fun getFrenchName(id: Int): String

    /**
     * Returns list of names in French.
     */
    @Query("SELECT french FROM language WHERE id IN (:idList)")
    suspend fun getFrenchNameList(idList: List<Int>): List<String>

    /**
     * Returns a name in Italian.
     */
    @Query("SELECT italian FROM language WHERE id = :id")
    suspend fun getItalianName(id: Int): String

    /**
     * Returns list of names in Italian.
     */
    @Query("SELECT italian FROM language WHERE id IN (:idList)")
    suspend fun getItalianNameList(idList: List<Int>): List<String>

    /**
     * Returns a name in German.
     */
    @Query("SELECT german FROM language WHERE id = :id")
    suspend fun getGermanName(id: Int): String

    /**
     * Returns list of names in German.
     */
    @Query("SELECT german FROM language WHERE id IN (:idList)")
    suspend fun getGermanNameList(idList: List<Int>): List<String>

}
