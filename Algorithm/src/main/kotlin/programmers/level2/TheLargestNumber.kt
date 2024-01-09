fun main() {
    val solution = TheLargestNumber()
// var numbers:IntArray = intArrayOf(6, 10, 2)
    //return 6210
    var numbers: IntArray = intArrayOf(6, 10, 2)
    // return 9534330
    solution.solution(numbers)
}

class TheLargestNumber {
    fun solution(numbers: IntArray): String {
        var answer = ""
        numbers.sortedWith(Comparator { o1, o2 ->
            "$o2 $o1".compareTo("$o1$o2")
        }).forEach { i: Int -> answer += i.toString() }
        println(answer)

        return if (answer.startsWith("0")) {
            "0"
        } else {
            answer
        }
    }
}