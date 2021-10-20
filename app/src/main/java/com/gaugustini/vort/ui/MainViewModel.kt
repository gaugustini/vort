package com.gaugustini.vort.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    // TODO: Replace this values
    var type = "Blademaster"
    val ranks = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9")
    val slots = listOf("0", "1", "2", "3")
    val skillsBlade =
        listOf("aSkill#00", "bSkill#01", "cSkill#02", "dSkill#03", "ddSkill#04", "eSkill#05")
    val skillsGunner = listOf("Skill#06", "Skill#07", "Skill#08", "Skill#09", "Skill#10")
    var mySkillsBlade = listOf<String>()
    var mySkillsGunner = listOf<String>()
    val done = MutableLiveData(false)
}
