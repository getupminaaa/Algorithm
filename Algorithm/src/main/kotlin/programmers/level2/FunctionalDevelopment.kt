import java.util.*
import kotlin.math.ceil

fun main() {
    val solution = FunctionalDevelopment()
    val progresses = intArrayOf(95, 90, 99,99,80,99)
    val speeds = intArrayOf(1, 1, 1,1,1,1)
    //return = [2,1]
    solution.solution(progresses, speeds)
}

class FunctionalDevelopment {
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        val requiredDays: Queue<Int> = LinkedList<Int>()
        var answer: IntArray = intArrayOf()
        val tasks = mutableListOf<Int>()
        progresses.forEachIndexed{ idx, _ ->
            var day = 0
            day = (100 -  progresses[idx])/speeds[idx]
            if((100-progresses[idx])%speeds[idx] != 0){
                day++
            }
            requiredDays.offer(day)
        }
        while (!requiredDays.isEmpty()){
            var count = 1
            val head = requiredDays.poll()
            while(!requiredDays.isEmpty()&&head>=requiredDays.peek()){
                requiredDays.remove()
                count++
            }
            tasks.add(count)
        }
        answer = tasks.toIntArray()
        return answer
    }
}
