package com.gaugustini.vort.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaugustini.vort.domain.ResultWithNamesCase
import com.gaugustini.vort.domain.SearchCase
import com.gaugustini.vort.domain.SkillWithNameCase
import com.gaugustini.vort.model.Search
import com.gaugustini.vort.model.Skill
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val skillCase: SkillWithNameCase,
    private val resultWithNamesCase: ResultWithNamesCase,
    private val searchCase: SearchCase,
) : ViewModel() {

    var type = 1
    val ranks = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9")
    val slots = listOf("0", "1", "2", "3")

    var skillsBlade = mapOf<String, Skill>()
    var skillsGunner = mapOf<String, Skill>()
    var mySkillsBlade = listOf<String>()
    var mySkillsGunner = listOf<String>()
    val done = MutableLiveData(false)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            skillsBlade = skillCase.getSkillList(language = 1, type = 1)
            skillsGunner = skillCase.getSkillList(language = 1, type = 2)
        }
    }

    fun search(hunterRank: Int, villageRank: Int, weaponSlot: Int, gender: Int) {
        viewModelScope.launch {
            searchCase.search(
                search = Search(
                    hunterRank = hunterRank,
                    villageRank = villageRank,
                    weaponSlot = weaponSlot,
                    gender = gender,
                    type = type,
                    skills = if (type == 1) {
                        skillsBlade.filter { (key, _) -> mySkillsBlade.contains(key) }.values.toList()
                    } else {
                        skillsGunner.filter { (key, _) -> mySkillsGunner.contains(key) }.values.toList()
                    }
                )
            )
        }
    }

    fun getResults() = resultWithNamesCase.getResultListWithNames(1)

}
