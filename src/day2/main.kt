package day1

import readInput

fun main() {
    fun part1(input: List<String>): Int {
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

    fun part2(input: List<String>): Int {
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

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day2/test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("day2/input")
    println(part1(input))
    println(part2(input))
}
