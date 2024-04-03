package baekjoon.gold

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.*

private var n = 0 
private lateinit var friendsInfo: MutableList<MutableList<Int>>
private lateinit var scoreInfo: MutableList<Int>
private val sb = StringBuilder()
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    n = readLine().toInt()

    friendsInfo = MutableList(n + 1) { mutableListOf<Int>() }
    scoreInfo = MutableList(n + 1) { Int.MAX_VALUE }

    while (true) {
        val temp = readLine().split(" ").map { it.toInt() }
        if (temp == mutableListOf(-1, -1)) break
        friendsInfo[temp[0]].add(temp[1])
        friendsInfo[temp[1]].add(temp[0])
    }

    repeat(n) {
        bfs(it + 1)
    }
    val min = scoreInfo.min()
    val num = scoreInfo.count { it == min }
    val candidates = mutableListOf<Int>()
    scoreInfo.forEachIndexed { index, i ->
        if(i == min){
            candidates.add(index)
        }
    }
    sb.append("$min $num").append("\n")
    candidates.sorted().forEach { sb.append(it).append(" ") }

    println(sb)
}

private fun bfs(start: Int) {
    val queue: Queue<Int> = LinkedList()
    val visited = MutableList(n + 1) { -1 }

    queue.add(start)
    visited[start] = 0

    while (queue.isNotEmpty()) {
        val p = queue.poll()
        friendsInfo[p].forEach {
            if (visited[it] == -1) {
                queue.add(it)
                visited[it] = visited[p] + 1
            }
        }
    }
    scoreInfo[start] = visited.max()
}