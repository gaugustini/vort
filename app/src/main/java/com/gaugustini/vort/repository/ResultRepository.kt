package com.gaugustini.vort.repository

import com.gaugustini.vort.database.ResultDao
import com.gaugustini.vort.model.Result
import javax.inject.Inject

class ResultRepository @Inject constructor(private val resultDao: ResultDao) {

    suspend fun insert(resultList: List<Result>) = resultDao.insert(resultList)

    suspend fun clear() = resultDao.clear()

    fun getResultList() = resultDao.getResultList()

}
