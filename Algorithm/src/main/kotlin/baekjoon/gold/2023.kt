package baekjoon.gold

import java.io.BufferedReader
import java.io.InputStreamReader

private var n = 0
private var primeNumList = mutableListOf<Int>()

private val sb = StringBuilder()
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    n = readLine().toInt()
    findAmazingPrimeNumber()
    println(sb)
}

private fun findAmazingPrimeNumber(){
    if(primeNumList.size == n && checkPrimeNumber(primeNumList.joinToString("").toInt())){
        sb.append(primeNumList.joinToString("")).append("\n")
        return
    }
    for(i in 1..9){
        primeNumList.add(i)
        val temp =  primeNumList.joinToString("").toInt()
        if(checkPrimeNumber(temp)){
            findAmazingPrimeNumber()
        }
        primeNumList.removeLast()
    }
}


private fun checkPrimeNumber(number: Int): Boolean {
    var divisor = 3;
    if (number <= 1) return false
    else if (number == 2 || number == 3) return true
    else if ((number % 2) == 0) return false
    while (number > divisor) {
        if ((number % divisor) == 0) return false;
        divisor += 2
    }
    return true
}

