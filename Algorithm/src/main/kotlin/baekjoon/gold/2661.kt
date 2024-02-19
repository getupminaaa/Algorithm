package baekjoon.gold

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.system.exitProcess

private val componentsArr = intArrayOf(1, 2, 3)
private var n = 0

private var sequence = mutableListOf<Int>()
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    n = readLine().toInt()
    findSequence(0)
}

fun findSequence(depth: Int) {
    if (depth == n) {
        println(sequence.joinToString(""))
        exitProcess(0)
    }

    for (i in componentsArr.indices) {
        sequence.add(componentsArr[i])
        var flag = true

        if (depth >= 1) {
            val cnt = (depth + 1) / 2

            for (j in 0..<cnt) {
                val right = sequence.slice(depth - j..depth)
                val left = sequence.slice(depth - 2 * j - 1..<depth - j)

                if (left == right) {
                    flag = false
                }
            }
        }

        if (flag) {
            findSequence(depth + 1)
            sequence.removeLast()
        } else {
            sequence.removeLast()
        }
    }
}