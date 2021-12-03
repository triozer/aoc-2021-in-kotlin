package fr.triozer.aoc.days

import fr.triozer.aoc.readInput

private fun part1(input: List<String>): Int {
    var height = 0
    var depth = 0

    for (line in input) {
        val (command, step) = line.split(" ")
        when (command) {
            "forward" -> height += step.toInt()
            "down" -> depth += step.toInt()
            "up" -> depth -= step.toInt()
        }
    }

    return height * depth
}

private fun part2(input: List<String>): Int {
    var height = 0
    var depth = 0
    var aim = 0

    for (line in input) {
        val (command, step) = line.split(" ")
        when (command) {
            "forward" -> {
                height += step.toInt()
                depth += aim * step.toInt()
            }
            "down" -> aim += step.toInt()
            "up" -> aim -= step.toInt()
        }
    }

    return height * depth
}

/**
 * Explanation: https://triozer.github.io/aoc-2021-in-kotlin/blog/day-2
 */
private fun main() {
    val testInput = readInput(2, "test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput(2, "input")
    println(part1(input))
    println(part2(input))
}
