package baekjoon.gold

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.system.exitProcess

private var n = 0
private var answer: Int? = null //최종 결과
private var depth = 0
private var visited = Array(10) { false }
private var numList = mutableListOf<Int>()
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    n = readLine().toInt()

    for (i in 1..10) {
        getDecreaseNum(i)
    }

    println("-1")
}

private fun checkDecreaseNum(list: MutableList<Int>): Boolean {
    var max = list[0]
    if (list.size == 1) {
        return true
    } else {
        for (i in 1..<list.size) {
            if (list[i] >= max){
                return false
            }else{
                max = list[i]
            }
        }
    }
    return true
}

private fun getDecreaseNum(digit: Int) {
    if (numList.size == digit) {
        if (checkDecreaseNum(numList)) {
            if (depth == n) {
                println(numList.joinToString(""))
                exitProcess(0)
            }
            depth++
        }
        return
    }
    for (i in 0..9) {
        if (!visited[i]) {
            numList.add(i)
            visited[i] = true

            getDecreaseNum(digit)

            numList.removeLast()
            visited[i] = false
        }
    }
}
