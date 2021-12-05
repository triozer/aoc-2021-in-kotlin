package fr.triozer.aoc.days

import fr.triozer.aoc.Vec2
import fr.triozer.aoc.readInput
import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.sign

private data class Segment(val from: Vec2, val to: Vec2) {
    fun toPointSequence(): Sequence<Vec2> {
        val delta = to - from
        val step = Vec2(delta.x.sign, delta.y.sign)
        val length = max(delta.x.absoluteValue, delta.y.absoluteValue) + 1
        return generateSequence(from, step::plus).take(length)
    }
}

private fun List<Segment>.countIntersections() = this.flatMap(Segment::toPointSequence)
    .groupingBy { it }
    .eachCount()
    .count { it.value > 1 }

private fun part1(segments: List<Segment>) = segments
    .filter { it.from.x == it.to.x || it.from.y == it.to.y }
    .countIntersections()

private fun part2(segments: List<Segment>) = segments.countIntersections()

private fun List<String>.toSegments() = this.map { line ->
    val (x0, y0, x1, y1) = line.split(" -> ")
        .flatMap { points ->
            points.split(",").map { it.toInt() }
        }
    return@map Segment(Vec2(x0, y0), Vec2(x1, y1))
}

/**
 * Explanation: https://triozer.github.io/aoc-2021-in-kotlin/blog/day-5
 */
private fun main() {
    val testInput = readInput(5, "test").toSegments()
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    println("Checks passed ✅")

    val input = readInput(5, "input").toSegments()
    println(part1(input))
    println(part2(input))
}
