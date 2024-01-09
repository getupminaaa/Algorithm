import java.util.*

fun main() {
    val solution = TheKthNumber()
    val array: IntArray = intArrayOf(1, 5, 2, 6, 3, 7, 4)
    val commands: Array<IntArray> = arrayOf(intArrayOf(2, 5, 3), intArrayOf(4, 4, 1), intArrayOf(1, 7, 3))
    //return = [5,6,3]
    solution.solution(array, commands)
}

class TheKthNumber {
    fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
        var answer = intArrayOf()
        var mutableList = mutableListOf<Int>()
        var k = 0
        commands.forEach { it->
            k = it[2]
           mutableList.add ( array.sliceArray(it[0]-1 .. it[1]-1).sorted().get(k-1))
        }
        answer = mutableList.toIntArray()
        return answer
    }
}
