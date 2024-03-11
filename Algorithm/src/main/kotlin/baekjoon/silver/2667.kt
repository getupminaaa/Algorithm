package baekjoon.silver

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var n = 0
private lateinit var mapInfo: MutableList<MutableList<Int>>
private lateinit var visitInfo: MutableList<MutableList<Boolean>>
private var resultArr = mutableListOf<Int>()

private val dx = intArrayOf(-1, 0, 1, 0)
private val dy = intArrayOf(0, 1, 0, -1)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    n = readLine().toInt()

    mapInfo = MutableList(n) { MutableList(n) { 0 } }
    visitInfo = MutableList(n) { MutableList(n) { false } }

    repeat(n) { n ->
        val temp = readLine().chunked(1).map { it.toInt() }
        temp.forEachIndexed { idx, it ->
            mapInfo[n][idx] = it
        }
    }

    for (i in 0..<n) {
        for (j in 0..<n) {
            if (mapInfo[i][j] == 1 && !visitInfo[i][j]) {
                bfs(Pair(i, j))
            }
        }
    }

    val len = resultArr.size
    resultArr.sort()
    resultArr.add(0, len)
    resultArr.forEach { println(it) }
}

private fun bfs(start: Pair<Int, Int>) {
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    var cnt = 1

    queue.add(start)
    visitInfo[start.first][start.second] = true

    while (queue.isNotEmpty()) {
        val (x, y) = queue.poll()

        for (i in 0..3) {
            val tx = x + dx[i]
            val ty = y + dy[i]

            if (tx in 0..<n && ty in 0..<n && mapInfo[tx][ty] == 1&&!visitInfo[tx][ty]) {
                queue.add(Pair(tx, ty))
                visitInfo[tx][ty] = true
                cnt++
            }
        }
    }
    resultArr.add(cnt)
}