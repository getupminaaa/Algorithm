fun main() {
    val sizes: Array<IntArray> = arrayOf(intArrayOf(60, 50), intArrayOf(30, 70), intArrayOf(60, 30), intArrayOf(80, 40))
    val solution = MinSquare()
    solution.solution(sizes)
}

class MinSquare {
    fun solution(sizes: Array<IntArray>): Int {
        var answer: Int = 0
        val height = mutableListOf<Int>()
        val width = mutableListOf<Int>()

        sizes.forEach { ints ->
            ints.minOf { it }.let { height.add(it) }
            ints.maxOf { it }.let { width.add(it) }
        }
        answer = height.maxOf { it } * width.maxOf { it }

        return answer
    }
}

