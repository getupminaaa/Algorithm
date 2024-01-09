import java.util.*

fun main() {
    val solution = Process()
    val priorities: IntArray = intArrayOf(1, 1, 2, 3, 2, 1)
    val location:Int = 0
    solution.solution(priorities,location)
}
class Process {
    fun solution(priorities: IntArray, location: Int): Int {
        var answer = 0
        val printerQueue:Queue<Pair<Int,Int>> = LinkedList<Pair<Int,Int>>()
        var sortedArray: List<Pair<Int, Int>>? = printerQueue.toList()
        var  aQueue:Queue<Pair<Int,Int>> = LinkedList<Pair<Int,Int>>()
        priorities.indices.forEach { printerQueue.offer(Pair(it,priorities[it])) }
        sortedArray = printerQueue.toList()
        while (!printerQueue.isEmpty()){
            val maxPair =  sortedArray!!.maxByOrNull { it.second }
            if(maxPair!!.second>printerQueue.peek().second){
                val temp =  printerQueue.peek()
                printerQueue.remove()
                printerQueue.offer(temp)
            }else{
                aQueue.offer(printerQueue.peek())
                printerQueue.remove()
            }
            sortedArray = printerQueue.toList()
        }

        sortedArray = aQueue + printerQueue

        val myJob = sortedArray.find { it.first == location }
         answer = sortedArray.indexOf(myJob!!) +1
        println(answer)
        return answer
    }
}