package baekjoon.gold

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue

private var m = 0
private var n = 0
private lateinit var tomatoBox: MutableList<MutableList<Int>>
private var ripeTomato = mutableListOf<Pair<Int, Int>>()

private var dx = intArrayOf(0, 0, -1, 1)
private var dy = intArrayOf(1, -1, 0, 0)

private var ans = 0


fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (tm, tn) = readLine().split(" ").map { it.toInt() }
    m = tm
    n = tn
    tomatoBox = MutableList(n) { mutableListOf() }

    repeat(n) {
        val temp = readLine().split(" ").map { it.toInt() }
        tomatoBox[it] = temp.toMutableList()
    }
    for (i in 0..<n) {
        for (j in 0..<m) {
            if (tomatoBox[i][j] == 1) {
                ripeTomato.add(Pair(i, j))
            }
        }
    }

    bfs(ripeTomato)

    if (tomatoBox.flatten().count { it == 0 } >= 1) {
        println(-1)
    } else if (!tomatoBox.flatten().contains(0) && ans == 0) {
        println(0)
    } else {
        println(--ans)
    }
}


private fun bfs(starts: MutableList<Pair<Int, Int>>): Int {
    val queue: Queue<Pair<Int, Int>> = LinkedList()

    starts.forEach { queue.add(it) }

    while (queue.isNotEmpty()) {
        val (x, y) = queue.poll()
        for (i in 0..3) {
            val tx = x + dx[i]
            val ty = y + dy[i]

            if (tx in 0..<n && ty in 0..<m && tomatoBox[tx][ty] == 0) {
                tomatoBox[tx][ty] = tomatoBox[x][y] + 1
                ans = tomatoBox[tx][ty]
                queue.add(Pair(tx, ty))
            }
        }
    }

    return ans
}