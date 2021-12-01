package day1

import readInputAsInt

fun main() {
    fun part1(input: List<Int>): Int {
        var x = -1
        var last = 0

        input.forEach { p ->
            if (last < p) {
                x++
            }

            last = p
        }

        return x
    }

    fun part2(input: List<Int>): Int {
        var x = -1
        var last = 0

        input.windowed(3).forEach { p ->
            if (last < p.sum()) {
                x++
            }

            last = p.sum()
        }

        return x
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsInt("day1/test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInputAsInt("day1/input")
    println(part1(input))
    println(part2(input))
}
