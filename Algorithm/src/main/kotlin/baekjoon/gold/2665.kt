package baekjoon.gold

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue

private var n = 0
private lateinit var maze: MutableList<MutableList<Int>>
private lateinit var dMaze: MutableList<MutableList<Int>>
private lateinit var visited: MutableList<MutableList<Int>>

private var dx = intArrayOf(0, 0, -1, 1)
private var dy = intArrayOf(1, -1, 0, 0)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    n = readLine().toInt()

    maze = MutableList(n) { mutableListOf<Int>() }
    dMaze = MutableList(n) { mutableListOf<Int>() }
    visited = MutableList(n) { MutableList(n) { Int.MAX_VALUE } }
    repeat(n) { idx ->
        val temp = readLine().chunked(1).map { it.toInt() }
        maze[idx] = temp.toMutableList()
        dMaze[idx] = temp.toMutableList()
    }
    bfs()
    println(visited[n - 1][n - 1])
}

private fun bfs() {
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.add(0 to 0)
    visited[0][0] = 0


    while (queue.isNotEmpty()) {
        val (x, y) = queue.poll()
        for (i in 0..3) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx in 0..<n && ny in 0..<n) {
                if (visited[nx][ny] <= visited[x][y]) continue

                if (maze[nx][ny] == 1) {
                    queue.add(nx to ny)
                    visited[nx][ny] = visited[x][y]

                } else if (maze[nx][ny] == 0) {
                    queue.add(nx to ny)
                    visited[nx][ny] = visited[x][y] + 1
                }
            }
        }

    }

}