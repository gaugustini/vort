package com.gaugustini.vort.domain

import com.gaugustini.vort.model.Skill
import com.gaugustini.vort.repository.LanguageRepository
import com.gaugustini.vort.repository.SkillRepository
import javax.inject.Inject

class SkillWithNameCase @Inject constructor(
    private val skillRepository: SkillRepository,
    private val languageRepository: LanguageRepository,
) {

    /**
     * Returns Map(key= Name, value= [Skill]).
     */
    suspend fun getSkillList(language: Int, type: Int): Map<String, Skill> {
        return buildMap {
            skillRepository.getSkillList(type).forEach { skill ->
                this[languageRepository.getName(skill.name, language)] = skill
            }
        }
    }

}
