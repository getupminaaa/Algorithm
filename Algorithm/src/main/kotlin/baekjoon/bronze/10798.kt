package baekjoon.bronze

import kotlin.math.max

fun main() {
    val input = Array<String>(5) { readLine()!!.toString() }
    val solution = ReadingVertically()
    solution.readingVertically(input)
}

class ReadingVertically {
    fun readingVertically(input: Array<String>) {

        var arr = Array<MutableList<Char>>(input.size) { mutableListOf() }
        var maxSize = 0
        for (i in input.indices) {
            maxSize = max(maxSize, input[i].length)
            arr[i].addAll(input[i].toList())
        }

        for (i in 0 until maxSize) {
            for (j in arr.indices) {
                if (arr[j].getOrNull(i) != null) {
                    print(arr[j][i])
                }
            }
        }
    }
}
