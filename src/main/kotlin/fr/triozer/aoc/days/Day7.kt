package fr.triozer.aoc.days

import fr.triozer.aoc.readSingleLineInputAsInts
import kotlin.math.abs

private fun minFuel(positions: List<Int>, constantRate: (n: Int) -> Int): Int {
    val min = positions.minOrNull()!!
    val max = positions.maxOrNull()!!
    return (min..max).minOfOrNull { alignPosition ->
        positions.sumOf { position ->
            constantRate(abs(alignPosition - position))
        }
    } ?: 0
}

private fun part1(input: List<Int>): Int = minFuel(input) { it }

private fun part2(input: List<Int>): Int = minFuel(input) { it * (it + 1) / 2 }

/**
 * Explanation: https://triozer.github.io/aoc-2021-in-kotlin/blog/day-7
 */
private fun main() {
    val testInput = readSingleLineInputAsInts(7, "test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    println("Checks passed ✅")

    val input = readSingleLineInputAsInts(7, "input")
    println(part1(input))
    println(part2(input))
}
