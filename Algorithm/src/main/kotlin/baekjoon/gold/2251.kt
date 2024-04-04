package baekjoon.gold

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue

private lateinit var bottles :Triple<Int,Int,Int>
private lateinit var visited: Array<Array<BooleanArray>>
private var answer = mutableListOf<Int>()
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val(a,b,c) = readLine().split(" ").map { it.toInt() }

    bottles = Triple(a,b,c)

    visited = Array(201) { Array(201) { BooleanArray(201) } }

    bfs(bottles)

    println(answer.sorted().joinToString(" "))
}

private fun bfs(bottles:Triple<Int,Int,Int>) {
    val queue:Queue<Triple<Int,Int,Int>> = LinkedList()
    queue.add(Triple(0, 0, bottles.third))

    while (queue.isNotEmpty()) {
        val (a, b, c) = queue.poll()
        if (visited[a][b][c]) continue

        visited[a][b][c] = true

        if (a == 0) {
            answer.add(c)
        }

        if (a + b > bottles.second) queue.add(Triple(a + b - bottles.second, bottles.second, c))
        else queue.add(Triple(0, a + b, c))

        if (a + c > bottles.third) queue.add(Triple(a + c - bottles.third, b, bottles.third))
        else queue.add(Triple(0, b, a + c))

        if (a + b > bottles.first) queue.add(Triple(bottles.first, a + b - bottles.first, c))
        else queue.add(Triple(a + b, 0, c))

        if (b + c > bottles.third) queue.add(Triple(a, b + c - bottles.third, bottles.third))
        else queue.add(Triple(a, 0, b + c))

        if (a + c > bottles.first) queue.add(Triple(bottles.first, b, a + c - bottles.first))
        else queue.add(Triple(a + c, b, 0))

        if (b + c > bottles.second) queue.add(Triple(a, bottles.second, b + c - bottles.second))
        else queue.add(Triple(a, b + c, 0))
    }
}