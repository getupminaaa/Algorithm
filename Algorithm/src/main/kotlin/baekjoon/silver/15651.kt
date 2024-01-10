package baekjoon.silver

import java.io.BufferedReader
import java.io.InputStreamReader

private val sb = StringBuilder()
fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr = mutableListOf<Int>()
    back(arr, n, m)
    println(sb)
}
fun back(arr:MutableList<Int>,n:Int,m:Int){
    if(arr.size == m){
        sb.append(arr.joinToString(" ")).append("\n")
        return
    }
    for(i in 1..n){
        arr.add(i)
        back(arr, n, m)
        arr.removeLast()
    }
}