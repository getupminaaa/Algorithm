package baekjoon.silver

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue

private var n = 0
private var m = 0
private var r = 0
private lateinit var edgeInfo: MutableList<MutableList<Int>>
private lateinit var visitSequence: Array<Int>

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine().split(" ").map { it.toInt() }
    n = input[0]
    m = input[1]
    r = input[2]
    edgeInfo = MutableList(n + 1) { mutableListOf<Int>() }
    repeat(m) {
        val temp = readLine().split(" ").map { it.toInt() }
        edgeInfo[temp[0]].add(temp[1])
        edgeInfo[temp[1]].add(temp[0])
    }
    edgeInfo.forEach { it.sort() }
    visitSequence = Array(n+1) { 0 }

    bfs(r)

    for(i in 1..n){
        println(visitSequence[i])
    }
}

private fun bfs(start: Int) {
    val queue: Queue<Int> = LinkedList()
    val visited = MutableList(n + 1) { false }
    var cnt = 1
    queue.add(start)
    visited[start] = true
    visitSequence[start] = cnt++

    while (queue.isNotEmpty()) {
        val u = queue.poll()
        edgeInfo[u].forEach {
            if (!visited[it]) {
                queue.add(it)
                visited[it] = true
                visitSequence[it] = cnt++
            }
        }
    }
}
