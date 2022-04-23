package com.gaugustini.vort.repository

import com.gaugustini.vort.database.SkillDao
import javax.inject.Inject

class SkillRepository @Inject constructor(private val skillDao: SkillDao) {

    suspend fun getSkillList(type: Int) = when (type) {
        1 -> skillDao.getSkillList(1) // Blademaster
        2 -> skillDao.getSkillList(2) // Gunner
        else -> listOf()
    }

}
