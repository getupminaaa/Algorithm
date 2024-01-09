package baekjoon.silver

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr = mutableListOf<Int>()
    val visited = Array(n + 1) { false }
    back(arr, visited, n, m, 0)
}

fun back(arr: MutableList<Int>, visited: Array<Boolean>, n: Int, m: Int, s: Int) {
    if (arr.size == m) {
        println(arr.joinToString(" "))
        return
    }
    for (i in s + 1..n) {
        arr.add(i)
        back(arr, visited, n, m, i)
        arr.removeLast()
    }
}