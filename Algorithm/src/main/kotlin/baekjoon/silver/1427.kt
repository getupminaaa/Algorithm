package baekjoon.silver

fun main() {
    val input = readLine()!!.toString()
    val solution = SortInside()
    solution.sortInside(input)
}

class SortInside {
    fun sortInside(input: String) {
        input.chunked(1).sortedDescending().joinToString(separator = "").let { print(it) }
        //toList도 가능
    }
}