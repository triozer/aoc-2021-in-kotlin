package fr.triozer.aoc.days

import fr.triozer.aoc.readInput

private fun frequency(list: List<String>, index: Int) = list.groupingBy { it[index] }.eachCount()

private fun part1(input: List<String>): Int {
    val frequencies = input[0].indices.map {
        frequency(input, it)
    }

    val gammaBinary = frequencies.joinToString("") {
        it.maxByOrNull { it.value }?.key.toString()
    }
    val epsilonBinary = gammaBinary.map { if (it == '0') '1' else '0' }.joinToString("")

    return gammaBinary.toInt(2) * epsilonBinary.toInt(2)
}

private fun filter(list: List<String>, desiredCharacter: (zeroes: Int, ones: Int) -> Char): String {
    var candidateList = list
    for (index in list.indices) {
        val frequencies = frequency(candidateList, index)
        val zeroes = frequencies['0'] ?: 0
        val ones = frequencies['1'] ?: 0
        candidateList = candidateList.filter { it[index] == desiredCharacter(zeroes, ones) }
        if (candidateList.size == 1) break
    }
    return candidateList.single()
}

private fun part2(input: List<String>): Int {
    val oxygenGeneratorRatingBinary = filter(input) { zeroes, ones ->
        if (zeroes > ones) '0' else '1'
    }

    val co2ScrubberRatingBinary = filter(input) { zeroes, ones ->
        if (zeroes > ones) '1' else '0'
    }

    return oxygenGeneratorRatingBinary.toInt(2) * co2ScrubberRatingBinary.toInt(2)
}

/**
 * Explanation: https://triozer.github.io/aoc-2021-in-kotlin/blog/day-3
 */
private fun main() {
    val testInput = readInput(3, "test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    println("Checks passed ✅")

    val input = readInput(3, "input")
    println(part1(input))
    println(part2(input))
}
