package fr.triozer.aoc.days

import fr.triozer.aoc.readInput

private sealed class Command(val step: Int) {
    abstract fun execute(submarine: Submarine): Submarine
    abstract fun execute(submarine: AimedSubmarine): AimedSubmarine

    companion object {
        fun from(command: String): Command {
            val (name, step) = command.split(" ")
            return when (name) {
                "forward" -> Forward(step.toInt())
                "down" -> Down(step.toInt())
                "up" -> Up(step.toInt())
                else -> error("what is $name?")
            }
        }
    }
}

private data class Submarine(val height: Int, val depth: Int)
private data class AimedSubmarine(val height: Int, val depth: Int, val aim: Int)

private class Forward(step: Int) : Command(step) {
    override fun execute(submarine: Submarine) = submarine.copy(height = submarine.height + step)

    override fun execute(submarine: AimedSubmarine) =
        submarine.copy(
            height = submarine.height + step,
            depth = submarine.depth + submarine.aim * step
        )
}

private class Down(step: Int) : Command(step) {
    override fun execute(submarine: Submarine) = submarine.copy(depth = submarine.depth + step)

    override fun execute(submarine: AimedSubmarine) = submarine.copy(aim = submarine.aim + step)
}

private class Up(step: Int) : Command(step) {
    override fun execute(submarine: Submarine) = submarine.copy(depth = submarine.depth - step)

    override fun execute(submarine: AimedSubmarine) = submarine.copy(aim = submarine.aim - step)
}

private fun part1(input: List<Command>): Int {
    val submarine = input.fold(Submarine(0, 0)) { acc, inst -> inst.execute(acc) }
    return submarine.height * submarine.depth
}

private fun part2(input: List<Command>): Int {
    val submarine = input.fold(AimedSubmarine(0, 0, 0)) { acc, inst -> inst.execute(acc) }
    return submarine.height * submarine.depth
}

private fun List<String>.toCommand() = this.map { Command.from(it) }

/**
 * Explanation: https://triozer.github.io/aoc-2021-in-kotlin/blog/day-2
 */
private fun main() {
    val testInput = readInput(2, "test").toCommand()
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    println("Checks passed ✅")

    val input = readInput(2, "input").toCommand()
    println(part1(input))
    println(part2(input))
}
