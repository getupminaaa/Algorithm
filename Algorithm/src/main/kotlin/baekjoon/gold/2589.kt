package baekjoon.gold

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue

private var n = 0
private var m = 0
private lateinit var treasureMap: MutableList<MutableList<String>>
private var landLocation = mutableListOf<Pair<Int, Int>>()
private lateinit var visitedInfo: MutableList<MutableList<Int>>

private var dx = intArrayOf(0, 0, -1, 1)
private var dy = intArrayOf(1, -1, 0, 0)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (tn, tm) = readLine().split(" ").map { it.toInt() }
    n = tn
    m = tm

    treasureMap = MutableList(n) { mutableListOf() }

    repeat(n) {
        val temp = readLine().chunked(1)
        treasureMap[it] = temp.toMutableList()
    }
    for (i in 0..<n) {
        for (j in 0..<m) {
            if (treasureMap[i][j] == "L") {
                landLocation.add(Pair(i, j))
            }
        }
    }
    val cases = mutableListOf<Int>()

    landLocation.forEach { it ->
        cases.add(bfs(it))
    }

    println(if (cases.isEmpty()) 0 else cases.max())
}

private fun bfs(start: Pair<Int, Int>): Int {
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    visitedInfo = MutableList(n) { MutableList(m) { -1 } }
    val times = mutableListOf<Int>()

    queue.add(start)
    visitedInfo[start.first][start.second] = 0

    while (queue.isNotEmpty()) {
        val (x, y) = queue.poll()
        for (i in 0..3) {

            val tx = x + dx[i]
            val ty = y + dy[i]

            if (tx in 0..<n && ty in 0..<m && treasureMap[tx][ty] == "L" && visitedInfo[tx][ty] == -1) {
                queue.add(Pair(tx, ty))
                visitedInfo[tx][ty] = visitedInfo[x][y] + 1
                times.add(visitedInfo[tx][ty])
            }
        }
    }

    return if (times.isEmpty()) 1 else times.max()
}