import java.util.*

fun main() {
    val solution = Solution()
    val terms: Array<String> = arrayOf("A 6", "B 12", "C 3")
    val privacies: Array<String> = arrayOf("2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C")

    solution.solution(today = "2022.05.19", terms, privacies)
}

class Solution {
    fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        var answer: IntArray = intArrayOf()

        //오늘 날짜 변환
        var tDates: Int = 0
        val todayArray: List<String> = today.split(".", ".")
        tDates += todayArray[0].toInt() * 12 * 28
        tDates += todayArray[1].toInt() * 28
        tDates += todayArray[2].toInt()
        for (i in privacies.indices) {
            var pDates: Int = 0

            val privaciesArray: List<String> = privacies[i].split(".", ".", " ")
            pDates += privaciesArray[0].toInt() * 12 * 28
            pDates += privaciesArray[1].toInt() * 28
            pDates += privaciesArray[2].toInt()

            for (j in terms.indices) {
                if (terms[j].split("\\s+".toRegex()).first() == privaciesArray[3]) {
                    pDates += terms[j].split("\\s+".toRegex()).last().toInt() * 28
                    if (tDates >= pDates) {
                        answer += i + 1
                    }
                }
            }
        }
        print(Arrays.toString(answer))
        return answer
    }

}