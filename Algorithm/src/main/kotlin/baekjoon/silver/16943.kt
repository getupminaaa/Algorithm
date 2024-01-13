package baekjoon.silver

import java.io.BufferedReader
import java.io.InputStreamReader

private var numArr = mutableListOf<Int>()
private lateinit var baseArr: List<Int>
private var max = Int.MIN_VALUE
private var res: Int? = null
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (a, b) = readLine().split(" ").map { it.toInt() }
    baseArr = a.toString().chunked(1).map { it.toInt() }
    val visited = Array(baseArr.size + 1) { false }

    mix(b, visited)

    if (res != null) {
        println(res)
    } else {
        println(-1)
    }
}

private fun mix(b: Int, visited: Array<Boolean>) {
    if (numArr.size == baseArr.size) {
        val temp = numArr.joinToString("").toInt()
        if (temp.toString().length == baseArr.joinToString("").length) {
            if (temp < b) {
                max = maxOf(max, temp)
                res = max
            }
        }
        return
    }
    for (i in baseArr.indices) {
        if (!visited[i]) {
            numArr.add(baseArr[i])
            visited[i] = true
            mix(b, visited)
            visited[i] = false
            numArr.removeLast()
        }
    }
}