package day1

import readInputAsInt

fun main() {
    fun part1(input: List<Int>) = input.zipWithNext().count { (a, b) -> b > a }

    fun part2(input: List<Int>) = part1(input.windowed(3).map { it.sum() })

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsInt("day1/test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInputAsInt("day1/input")
    println(part1(input))
    println(part2(input))
}
