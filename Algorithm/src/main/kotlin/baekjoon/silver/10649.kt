package baekjoon.silver

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (n,m) = readLine().split(" ").map { it.toInt() }
    val arr = mutableListOf<Int>()
    val visited = Array(n+1){false}

    back(arr, n, m, visited)
}
fun back(arr:MutableList<Int>,n:Int,m:Int,visited:Array<Boolean>){
    if(arr.size == m){
        println(arr.joinToString(" "))
        return
    }
    for(i in 1..n){
        if(!visited[i]){
            arr.add(i)
            visited[i] = true
            back(arr,n,m,visited)
            arr.removeLast()
            visited[i] = false
        }
    }
}