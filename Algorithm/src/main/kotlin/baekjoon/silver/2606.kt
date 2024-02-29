package baekjoon.silver

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var computers = 0
private var edges = 0
private lateinit var edgeInfo: MutableList<MutableList<Int>>
private var cnt = 0

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    computers = readLine().toInt()
    edges = readLine().toInt()
    edgeInfo = MutableList(computers + 1) { mutableListOf<Int>() }

    repeat(edges){
        val temp = readLine().split(" ").map { it.toInt() }
        edgeInfo[temp[0]].add(temp[1])
        edgeInfo[temp[1]].add(temp[0])
    }
    bfs(1)
    println(cnt)
}
private fun bfs(start:Int){
    val queue: Queue<Int> = LinkedList()
    val visited = MutableList(computers + 1) { false }

    queue.add(start)
    visited[start] = true
    while (queue.isNotEmpty()){
        val u = queue.poll()
        edgeInfo[u].forEach {
            if(!visited[it]){
                queue.add(it)
                visited[it] = true
                cnt++
            }
        }
    }
}