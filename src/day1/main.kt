package day1

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day1/test")
    check(part1(testInput) == 7)

    val input = readInput("day1/input")
    println(part1(input))
    println(part2(input))
}
