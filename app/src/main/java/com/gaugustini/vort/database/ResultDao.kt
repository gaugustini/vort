package com.gaugustini.vort.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gaugustini.vort.model.Result
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for [Result].
 */
@Dao
interface ResultDao {

    /**
     * Inserts a new list of [Result].
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(resultList: List<Result>)

    /**
     * Delete all.
     */
    @Query("DELETE FROM result")
    suspend fun clear()

    /**
     * Returns a list of [Result].
     */
    @Query("SELECT * FROM result")
    fun getResultList(): Flow<List<Result>>

}
