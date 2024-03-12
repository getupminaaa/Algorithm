package baekjoon.silver

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var br = BufferedReader(InputStreamReader(System.`in`))
private var testNum = 0
private var m = 0
private var n = 0
private var cMapSize = 0
private lateinit var cMap: MutableList<MutableList<Int>>
private lateinit var visitInfo: MutableList<MutableList<Boolean>>
private var resultArr = mutableListOf<Int>()

private val sb = StringBuilder()
private val dx = intArrayOf(-1, 0, 1, 0)
private val dy = intArrayOf(0, 1, 0, -1)

fun main() {
    testNum = br.readLine().toInt()
    repeat(testNum) {
        getInput()
        sb.append(resultArr.size).append("\n")
        resultArr.clear()
    }
    println(sb)
}

private fun getInput() {
    val temp = br.readLine().split(" ").map { it.toInt() }
    m = temp[0]
    n = temp[1]
    cMapSize = temp[2]
    cMap = MutableList(n) { MutableList(m) { 0 } }
    visitInfo = MutableList(n) { MutableList(m) { false } }

    repeat(cMapSize) {
        val (x, y) = br.readLine().split(" ").map { it.toInt() }
        cMap[y][x] = 1
    }

    for (i in 0..<n) {
        for (j in 0..<m) {
            if (cMap[i][j] == 1 && !visitInfo[i][j]) {
                bfs(Pair(i, j))
            }
        }
    }
    cMap.clear()
    visitInfo.clear()
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

            if (tx in 0..<n && ty in 0..<m && cMap[tx][ty] == 1 && !visitInfo[tx][ty]) {
                queue.add(Pair(tx, ty))
                visitInfo[tx][ty] = true
                cnt++
            }
        }
    }
    resultArr.add(cnt)
}