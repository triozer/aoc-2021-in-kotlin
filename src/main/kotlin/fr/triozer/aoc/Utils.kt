package fr.triozer.aoc

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(day: Int, name: String) = File("src/main/resources/day$day", "$name.txt").readLines()

/**
 * Read lines from the given input txt file as a list of int
 */
fun readInputAsInt(day: Int, name: String) = readInput(day, name).map { it.toInt() }

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String =
    BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
