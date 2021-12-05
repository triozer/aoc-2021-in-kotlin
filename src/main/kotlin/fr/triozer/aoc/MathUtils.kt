package fr.triozer.aoc

import kotlin.math.absoluteValue

data class Vec2(val x: Int, val y: Int)
{
    operator fun plus(vec: Vec2) = Vec2(x + vec.x, y + vec.y)
    operator fun minus(vec: Vec2) = Vec2(x - vec.x, y - vec.y)
    operator fun div(value: Int) = Vec2(x / value, y / value)
    operator fun times(value: Int) = Vec2(x * value, y * value)

    fun distance(o: Vec2) = (o - this).let { it.x.absoluteValue + it.y.absoluteValue }

    companion object
    {
        val origin = Vec2(0, 0)
    }
}
