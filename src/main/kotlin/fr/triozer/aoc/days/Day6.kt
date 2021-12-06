package fr.triozer.aoc.days

import fr.triozer.aoc.readInput

private fun count(input: List<Int>, days: Int): Long {
    val fishes = LongArray(9) { 0 }
    input.forEach { fish -> fishes[fish]++ }

    repeat(days) {
        val zeroes = fishes[0]
        for (i in 0..7) {
            fishes[i] = fishes[i + 1]
        }
        fishes[6] += zeroes
        fishes[8] = zeroes
    }

    return fishes.sum()
}

/**
 * Explanation: https://triozer.github.io/aoc-2021-in-kotlin/blog/day-6
 */
private fun main() {
    val testInput = readInput(6, "test")
        .flatMap { line -> line.split(",").map { it.toInt() } }
    check(count(testInput, 80) == 5934L)
    check(count(testInput, 256) == 26984457539L)

    println("Checks passed ✅")

    val input = readInput(6, "input")
        .flatMap { line -> line.split(",").map { it.toInt() } }
    println(count(input, 80))
    println(count(input, 256))
}
