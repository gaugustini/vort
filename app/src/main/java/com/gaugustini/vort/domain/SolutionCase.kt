package com.gaugustini.vort.domain

import com.gaugustini.vort.model.Armor
import com.gaugustini.vort.model.Result
import com.gaugustini.vort.model.SimpleDecoration

class SolutionCase {

    private var weaponSlot = 0
    private var decorationList = listOf<SimpleDecoration>()
    private var skills = mapOf<Int, Int>()
    private lateinit var armor: Armor

    /**
     * Possibility = Map(key= Decoration, value= amount).
     */
    data class Possibility(val map: Map<SimpleDecoration, Int>)

    fun search(
        weaponSlot: Int,
        armor: Armor,
        decorationList: List<SimpleDecoration>,
        skills: Map<Int, Int>
    ): Result? {
        this.weaponSlot = weaponSlot
        this.armor = armor
        this.decorationList = decorationList
        this.skills = skills

        return start()
    }

    private fun start(): Result? {
        val points = getPointsMissing(skills = skills)

        if (points.isEmpty()) {
            return Result(
                head = armor.head.id,
                body = armor.body.id,
                arms = armor.arms.id,
                waist = armor.waist.id,
                legs = armor.legs.id,
                decorations = mapOf()
            )
        } else {
            val possibilities = getPossibilities(skills = points, decorations = decorationList)

            if (testPossibilitiesAndSlotsAvailable(possibilities)) {
                val map = getDecorationsAndAmount(possibilities)

                return if (map.isEmpty()) {
                    null
                } else {
                    Result(
                        head = armor.head.id,
                        body = armor.body.id,
                        arms = armor.arms.id,
                        waist = armor.waist.id,
                        legs = armor.legs.id,
                        decorations = buildMap { map.forEach { this[it.key.id] = it.value } }
                    )
                }
            } else {
                return null
            }
        }
    }

    /**
     * Returns Map(key= SKillID, value= Points Missing).
     * @param skills Map(key= SkillID, value= Points to Activate SKill).
     */
    private fun getPointsMissing(skills: Map<Int, Int>) = buildMap {
        skills.forEach { skill ->
            val points = armor.head.getPoints(skill.key) +
                    armor.body.getPoints(skill.key) +
                    armor.arms.getPoints(skill.key) +
                    armor.waist.getPoints(skill.key) +
                    armor.legs.getPoints(skill.key)

            if (skill.value - points > 0) {
                this[skill.key] = (skill.value - points)
            }
        }
    }

    /**
     * Returns Map(key= SKillID, value= List([Possibility])).
     * @param skills Map(key= SkillID, value= Points Missing).
     */
    private fun getPossibilities(skills: Map<Int, Int>, decorations: List<SimpleDecoration>) =
        buildMap {
            skills.forEach { skill ->
                this[skill.key] = generatePossibilities(
                    points = skill.value,
                    decorations = decorations.filter { it.skillOne == skill.key }
                )
            }
        }

    /**
     * Returns List([Possibility]).
     * @param points Points Missing.
     */
    private fun generatePossibilities(points: Int, decorations: List<SimpleDecoration>) =
        buildList {
            // TODO: Add possibilities between A/C and A/B/C;
            //  Eliminate possibilities with slots greater than slots available

            if (decorations.isEmpty()) return this

            var possibility: Possibility

            var index = 1
            var decorationA = 0 // index 1
            var decorationB = 0 // index 2
            var decorationC = 0 // index 3

            while (true) {
                if (this.isEmpty()) {
                    while (true) {
                        possibility = generatePossibility(
                            decorations = decorations,
                            amount = listOf(decorationA, decorationB, decorationC)
                        )

                        if (getAmountOfPoints(possibility) >= points) {
                            this.add(possibility)
                            break
                        } else {
                            decorationA++
                        }
                    }

                    if (decorations.size == 1) break

                } else {
                    index++

                    if (index > decorations.size) break

                    while (true) {
                        if (index == 2) decorationB++
                        if (index == 3) decorationC++

                        while (true) {
                            possibility = generatePossibility(
                                decorations = decorations,
                                amount = listOf(decorationA, decorationB, decorationC)
                            )

                            if (getAmountOfPoints(possibility) == points) {
                                this.add(possibility)
                                break
                            } else {
                                if (getAmountOfPoints(possibility) < points) {
                                    if (index == 2) decorationA++
                                    if (index == 3) decorationB++
                                    possibility = generatePossibility(
                                        decorations = decorations,
                                        amount = listOf(decorationA, decorationB, decorationC)
                                    )
                                    this.add(possibility)
                                    break
                                }
                                if (index == 2 && decorationA == 0) {
                                    this.add(possibility)
                                    break
                                }
                                if (index == 3 && decorationB == 0) {
                                    this.add(possibility)
                                    break
                                }
                                if (index == 2) decorationA--
                                if (index == 3) decorationB--
                            }
                        }

                        if (index == 2 && decorationA == 0) {
                            if (decorations.size > 2) index = 3 else break
                        }
                        if (index == 3 && decorationB == 0) break

                    }
                }
            }
        }.sortedWith(compareBy({ getAmountOfSlots(it) }, { getAmountOfPoints(it) }))

    /**
     * Returns a [Possibility].
     */
    private fun generatePossibility(decorations: List<SimpleDecoration>, amount: List<Int>) =
        Possibility(map = buildMap {
            decorations.sortedBy { it.skillOnePoints }.forEachIndexed { index, decoration ->
                if (amount[index] > 0) this[decoration] = amount[index]
            }
        })

    /**
     * Returns points from a [Possibility].
     */
    private fun getAmountOfPoints(possibility: Possibility): Int {
        var points = 0
        possibility.map.forEach { points += (it.key.skillOnePoints * it.value) }
        return points
    }

    /**
     * Returns slots from a [Possibility].
     */
    private fun getAmountOfSlots(possibility: Possibility): Int {
        var slots = 0
        possibility.map.forEach { slots += (it.key.slotsRequired * it.value) }
        return slots
    }

    /**
     * Returns list of slots from a [Possibility].
     */
    private fun getSlotsList(possibility: Possibility) = buildList {
        possibility.map.forEach { (decoration, amount) ->
            for (i in 1..amount) {
                this.add(decoration.slotsRequired)
            }
        }
    }

    /**
     * Returns List(Slots Available).
     */
    private fun getSlotsAvailable() = buildList {
        add(weaponSlot)
        add(armor.head.slots)
        add(armor.body.slots)
        add(armor.arms.slots)
        add(armor.waist.slots)
        add(armor.legs.slots)
    }

    /**
     * Returns Map(key= Decoration, value= Amount).
     * @param possibilities Map(key= SKillID, value= List([Possibility])).
     */
    private fun getDecorationsAndAmount(possibilities: Map<Int, List<Possibility>>) = buildMap {
        val list = mutableListOf<Possibility>()
        val skills = buildList { possibilities.forEach { this.add(it.key) } }

        when (possibilities.size) {
            1 -> {
                loop@ for (possibilityA in possibilities[skills[0]]!!) {
                    list.add(possibilityA)
                    if (testPossibilities(list)) {
                        list.forEach {
                            it.map.forEach { (decoration, amount) -> this[decoration] = amount }
                        }
                        break@loop
                    } else {
                        list.removeAt(0)
                    }
                }
            }
            2 -> {
                loop@ for (possibilityA in possibilities[skills[0]]!!) {
                    list.add(possibilityA)
                    for (possibilityB in possibilities[skills[1]]!!) {
                        list.add(possibilityB)
                        if (testPossibilities(list)) {
                            list.forEach {
                                it.map.forEach { (decoration, amount) ->
                                    this[decoration] = amount
                                }
                            }
                            break@loop
                        } else {
                            list.removeAt(1)
                        }
                    }
                    list.removeAt(0)
                }
            }
            3 -> {
                loop@ for (possibilityA in possibilities[skills[0]]!!) {
                    list.add(possibilityA)
                    for (possibilityB in possibilities[skills[1]]!!) {
                        list.add(possibilityB)
                        for (possibilityC in possibilities[skills[2]]!!) {
                            list.add(possibilityC)
                            if (testPossibilities(list)) {
                                list.forEach {
                                    it.map.forEach { (decoration, amount) ->
                                        this[decoration] = amount
                                    }
                                }
                                break@loop
                            } else {
                                list.removeAt(2)
                            }
                        }
                        list.removeAt(1)
                    }
                    list.removeAt(0)
                }
            }
            4 -> {
                loop@ for (possibilityA in possibilities[skills[0]]!!) {
                    list.add(possibilityA)
                    for (possibilityB in possibilities[skills[1]]!!) {
                        list.add(possibilityB)
                        for (possibilityC in possibilities[skills[2]]!!) {
                            list.add(possibilityC)
                            for (possibilityD in possibilities[skills[3]]!!) {
                                list.add(possibilityD)
                                if (testPossibilities(list)) {
                                    list.forEach {
                                        it.map.forEach { (decoration, amount) ->
                                            this[decoration] = amount
                                        }
                                    }
                                    break@loop
                                } else {
                                    list.removeAt(3)
                                }
                            }
                            list.removeAt(2)
                        }
                        list.removeAt(1)
                    }
                    list.removeAt(0)
                }
            }
            5 -> {
                loop@ for (possibilityA in possibilities[skills[0]]!!) {
                    list.add(possibilityA)
                    for (possibilityB in possibilities[skills[1]]!!) {
                        list.add(possibilityB)
                        for (possibilityC in possibilities[skills[2]]!!) {
                            list.add(possibilityC)
                            for (possibilityD in possibilities[skills[3]]!!) {
                                list.add(possibilityD)
                                for (possibilityE in possibilities[skills[4]]!!) {
                                    if (testPossibilities(list)) {
                                        list.forEach {
                                            it.map.forEach { (decoration, amount) ->
                                                this[decoration] = amount
                                            }
                                        }
                                        break@loop
                                    } else {
                                        list.removeAt(4)
                                    }
                                }
                                list.removeAt(3)
                            }
                            list.removeAt(2)
                        }
                        list.removeAt(1)
                    }
                    list.removeAt(0)
                }
            }
        }
    }

    /**
     * Returns if it is possible to gem with the given possibilities.
     */
    private fun testPossibilities(possibilities: List<Possibility>): Boolean {
        var count = 0
        val slotsPossibilities = buildList {
            possibilities.forEach { possibility ->
                getSlotsList(possibility).forEach { this.add(it) }
            }
        }
        val slotsAvailable = getSlotsAvailable().toMutableList()

        if (slotsPossibilities.sum() > slotsAvailable.sum()) return false

        slotsPossibilities.sortedDescending().forEach { slot ->
            when {
                slotsAvailable.contains(slot) -> {
                    slotsAvailable.remove(slot)
                    count += slot
                }
                slotsAvailable.isNotEmpty() -> {
                    for ((index, slotAvb) in slotsAvailable.withIndex()) {
                        if (slotAvb > slot) {
                            slotsAvailable[index] = slotAvb - slot
                            count += slot
                            break
                        }
                    }
                }
                else -> return false
            }
        }

        return count == slotsPossibilities.sum()
    }

    /**
     * Returns if it is possible to gem with the minimum of slots of the possibilities.
     * @param possibilities Map(key= SKillID, value= List([Possibility])).
     */
    private fun testPossibilitiesAndSlotsAvailable(possibilities: Map<Int, List<Possibility>>): Boolean {
        var minimumSlots = 0
        possibilities.forEach { (_, list) -> minimumSlots += getAmountOfSlots(list[0]) }
        return minimumSlots <= getSlotsAvailable().sum()
    }

}
