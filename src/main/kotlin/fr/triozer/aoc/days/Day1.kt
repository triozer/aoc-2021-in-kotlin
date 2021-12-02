package fr.triozer.aoc.days

import fr.triozer.aoc.readInputAsInt

fun part1(input: List<Int>) = input.zipWithNext().count { (a, b) -> b > a }

fun part2(input: List<Int>) = part1(input.windowed(3) { it.sum() })

fun main() {
    val testInput = readInputAsInt(1, "test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInputAsInt(1, "input")
    println(part1(input))
    println(part2(input))
}
