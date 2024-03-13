package baekjoon.gold

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var n = 0
private var m = 0
private lateinit var treeInfo: MutableList<MutableList<Int>>
private lateinit var distanceInfo: MutableList<MutableList<Int>>
private var problems = mutableListOf<Pair<Int, Int>>()
private var result = mutableListOf<Int>()
private lateinit var visited: MutableList<Int>
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val temp = readLine().split(" ").map { it.toInt() }
    n = temp[0]
    m = temp[1]

    treeInfo = MutableList(n + 1) { mutableListOf<Int>() }
    distanceInfo = MutableList(n + 1) { MutableList(n + 1) { 0 } }

    repeat(n - 1) {
        val tempInfo = readLine().split(" ").map { it.toInt() }
        treeInfo[tempInfo[0]].add(tempInfo[1])
        treeInfo[tempInfo[1]].add(tempInfo[0])
        distanceInfo[tempInfo[0]][tempInfo[1]] = tempInfo[2]
        distanceInfo[tempInfo[1]][tempInfo[0]] = tempInfo[2]
    }
    repeat(m) {
        val tempProb = readLine().split(" ").map { it.toInt() }
        problems.add(Pair(tempProb[0], tempProb[1]))
    }

    repeat(m) {
        bfs(problems[it])
        result.add(visited[problems[it].second])
    }

    result.forEach {
        println(it)
    }
}

private fun bfs(start: Pair<Int, Int>) {
    val queue: Queue<Int> = LinkedList()
    visited = MutableList(n + 1) { -1 }
    queue.add(start.first)
    visited[start.first] = 0

    while (queue.isNotEmpty()) {
        val p = queue.poll()

        if (p == start.second) {
            return
        }

        for (i in treeInfo[p]) {
            if (visited[i] == -1) {
                queue.add(i)
                visited[i] = visited[p] + distanceInfo[i][p]
            }
        }
    }
}
