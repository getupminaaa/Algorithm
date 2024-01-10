package baekjoon.silver

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

private val sb = StringBuilder()
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr = mutableListOf<Int>()
    val visited = Array(n + 1) { false }
    back(arr, visited, n, m, 1)
    print(sb)
}

private fun back(arr: MutableList<Int>, visited: Array<Boolean>, n: Int, m: Int, s: Int) {
    if (arr.size == m) {
        sb.append(arr.joinToString(" ")).append("\n")
        return
    }
    for (i in s ..n) {
        arr.add(i)
        back(arr, visited, n, m, i)
        arr.removeLast()
    }
}