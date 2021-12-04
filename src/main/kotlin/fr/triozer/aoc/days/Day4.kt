package fr.triozer.aoc.days

import fr.triozer.aoc.readInput

private data class Cell(val value: Int, var checked: Boolean)

private class Grid(val cells: List<List<Cell>>) {
    fun check(value: Int) {
        val cellIndex = this.cells.flatten().indexOfFirst { it.value == value }

        if (cellIndex == -1) {
            return
        }

        val x = cellIndex % this.cells.size
        val y = cellIndex / this.cells.first().size

        this.cells[y][x].checked = true
    }

    fun checkWin(): Boolean = this.cells.any { it -> it.all { it.checked } }
        || this.cells.indices
        .map { index -> this.cells.map { it[index] } }
        .any { it -> it.all { it.checked } }

    fun unmarkedSum() = this.cells.flatten().filter { !it.checked }.sumOf { it.value }

    companion object {
        fun from(gridInput: List<String>): Grid {
            return Grid(gridInput.map { it ->
                it
                    .split(" ")
                    .filter { it.isNotBlank() }
                    .map { Cell(it.toInt(), false) }
            })
        }
    }
}

private fun resolve(input: List<String>): MutableSet<MutableMap.MutableEntry<Grid, Int>> {
    val numberToPulled = input[0].split(",").map { it.toInt() }
    val grids = input.subList(1, input.size)
        .filter { it.isNotBlank() }
        .windowed(5, 5)
        .map { Grid.from(it) }

    return numberToPulled.fold(LinkedHashMap<Grid, Int>()) { resolvedGrids, value ->
        run {
            grids.forEach nextGrid@{
                if (resolvedGrids.contains(it)) {
                    return@nextGrid
                }

                it.check(value)

                if (it.checkWin()) {
                    resolvedGrids[it] = value
                }
            }

            return@fold resolvedGrids
        }
    }.entries
}

private fun MutableMap.MutableEntry<Grid, Int>.score() = this.key.unmarkedSum() * this.value

/**
 * Explanation: https://triozer.github.io/aoc-2021-in-kotlin/blog/day-4
 */
private fun main() {
    val testInput = readInput(4, "test")
    val resolvedTestGrids = resolve(testInput)

    check(resolvedTestGrids.first().score() == 4512)
    check(resolvedTestGrids.last().score() == 1924)

    println("Checks passed ✅")

    val input = readInput(4, "input")
    val resolvedInputGrids = resolve(input)

    println(resolvedInputGrids.first().score())
    println(resolvedInputGrids.last().score())
}
