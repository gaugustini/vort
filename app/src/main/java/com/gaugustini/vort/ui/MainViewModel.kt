package com.gaugustini.vort.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gaugustini.vort.model.Result
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
    val results: List<Result> = listOf(
        Result(
            head = "Head#0", body = "Body#0", arms = "Arms#0", waist = "Waist#0", legs = "Legs#0",
            decorations = listOf(
                Pair(1, "Decoration#0"), Pair(2, "Decoration#1"), Pair(3, "Decoration#2"),
            )
        ),
        Result(
            head = "Head#1", body = "Body#1", arms = "Arms#1", waist = "Waist#1", legs = "Legs#1",
            decorations = listOf(
                Pair(1, "Decoration#0"), Pair(2, "Decoration#1"), Pair(3, "Decoration#2"),
            )
        ),
        Result(
            head = "Head#2", body = "Body#2", arms = "Arms#2", waist = "Waist#2", legs = "Legs#2",
            decorations = listOf(
                Pair(1, "Decoration#0"), Pair(2, "Decoration#1"), Pair(3, "Decoration#2"),
            )
        ),
        Result(
            head = "Head#3", body = "Body#3", arms = "Arms#3", waist = "Waist#3", legs = "Legs#3",
            decorations = listOf(
                Pair(1, "Decoration#0"), Pair(2, "Decoration#1"), Pair(3, "Decoration#2"),
            )
        ),
        Result(
            head = "Head#4", body = "Body#4", arms = "Arms#4", waist = "Waist#4", legs = "Legs#4",
            decorations = listOf(
                Pair(1, "Decoration#0"), Pair(2, "Decoration#1"), Pair(3, "Decoration#2"),
            )
        ),
        Result(
            head = "Head#5", body = "Body#5", arms = "Arms#5", waist = "Waist#5", legs = "Legs#5",
            decorations = listOf(
                Pair(1, "Decoration#0"), Pair(2, "Decoration#1"), Pair(3, "Decoration#2"),
            )
        ),
        Result(
            head = "Head#6", body = "Body#6", arms = "Arms#6", waist = "Waist#6", legs = "Legs#6",
            decorations = listOf(
                Pair(1, "Decoration#0"), Pair(2, "Decoration#1"), Pair(3, "Decoration#2"),
            )
        ),
    )
}
