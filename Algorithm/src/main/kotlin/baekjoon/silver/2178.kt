package baekjoon.silver

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var n = 0
private var m = 0
private lateinit var mazeInfo: MutableList<MutableList<Int>>
private lateinit var visitInfo: MutableList<MutableList<Int>>

private val dx = intArrayOf(-1, 0, 1, 0)
private val dy = intArrayOf(0, 1, 0, -1)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine().split(" ").map { it.toInt() }
    n = input[0]
    m = input[1]
    mazeInfo = MutableList(n + 1) { MutableList(m + 1) { 0 } }
    visitInfo = MutableList(n + 1) { MutableList(m + 1) { -1 } }
    repeat(n) { n ->
        val temp = readLine().chunked(1).map { it.toInt() }
        temp.forEachIndexed { idx, it ->
            mazeInfo[n + 1][idx + 1] = it
        }
    }

    bfs()
    println(visitInfo[n][m])
}

private fun bfs() {
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.add(Pair(1, 1))
    visitInfo[1][1] = 1

    while (queue.isNotEmpty()) {
        val (x, y) = queue.poll()

        for (i in 0..3) {
            val tx = x + dx[i]
            val ty = y + dy[i]

            if (tx in 0..n && ty in 0..m && mazeInfo[tx][ty] == 1 && visitInfo[tx][ty] == -1) {
                queue.add(Pair(tx, ty))
                visitInfo[tx][ty] = visitInfo[x][y] + 1
            }
        }
    }
}