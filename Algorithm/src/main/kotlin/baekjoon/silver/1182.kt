package baekjoon.silver

import java.io.BufferedReader
import java.io.InputStreamReader

private var cnt = 0

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, s) = readLine().split(" ").map { it.toInt() }
    val numArr = readLine().split(" ").map { it.toInt() }
    val arr = mutableListOf<Int>()
    for (i in 1..n) {
        backTracking(arr, numArr, n, i, -1, s)
    }
    println(cnt)
}


private fun backTracking(arr: MutableList<Int>, numArr: List<Int>, num: Int, n: Int, start: Int, s: Int) {
    if (arr.size == n) {
        if (arr.sumOf { it.toInt() } == s) {
            cnt += 1
        }
        return
    }
    for (i in start + 1..<num) {
        arr.add(numArr[i])
        backTracking(arr, numArr, num, n, i, s)
        arr.removeLast()
    }
}