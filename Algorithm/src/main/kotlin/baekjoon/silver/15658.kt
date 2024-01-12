package baekjoon.silver

import java.io.BufferedReader
import java.io.InputStreamReader

private lateinit var operatorQuantity: MutableList<Int>
private lateinit var numArr: List<Int>
private var operatorArr = mutableListOf<Int>()
private var max = Int.MIN_VALUE
private var min = Int.MAX_VALUE

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val numQuantity = readLine().toInt()
    numArr = readLine().split(" ").map { it.toInt() }
    operatorQuantity = readLine().split(" ").map { it.toInt() }.toMutableList()

    back(numQuantity)
    println(max)
    println(min)
}
private fun checkOperator(operator: Int,a:Int,b:Int) = when (operator) {
    0 -> a + b
    1 -> a - b
    2 -> a * b
    else -> a.div(b)
}
private fun calculate():Int{
    var temp = checkOperator(operatorArr[0], numArr[0], numArr[1])

    for(i in 1..<operatorArr.size){
        temp = checkOperator(operatorArr[i], temp, numArr[i + 1])
    }

    return temp
}

private fun back(numQuantity: Int) {
    if (operatorArr.size == numQuantity - 1) {
        val result = calculate()
        max = maxOf(max, result)
        min = minOf(min, result)
        return
    }
    for(i in 0..<operatorQuantity.size){
        if(operatorQuantity[i] != 0){
            operatorQuantity[i]--
            operatorArr.add(i)
            back(numQuantity)
            operatorArr.removeLast()
            operatorQuantity[i]++
        }
    }
}