package baekjoon.gold

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.pow

private val dx = intArrayOf(0, 0, -1, 1)
private val dy = intArrayOf(1, -1, 0, 0)
private var x = 0
private var y = 0

private var directionCounts = Array(4) { 0 }
private var directionsRate = mutableListOf<Int>()
private var routes = mutableListOf<Pair<Int, Int>>()

private var n = 0
private var ans = 0.0

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val temp = readLine().split(" ").map { it.toInt() }
    n = temp[0]
    directionsRate = temp.subList(1, 5).toMutableList()
    routes.add(0 to 0)
    move()
    println(ans)
}

private fun move() {
    if (routes.size == n + 1) {
        var rates = 1.0
        directionCounts.forEachIndexed { idx, value ->
            if (value > 0) {
                val rate = directionsRate[idx] / 100.0
                rates *= rate.pow(value)
            }
        }
        ans += rates
        return
    }
    for (i in 0..3) {
        if (directionsRate[i] != 0) {
            x += dx[i]
            y += dy[i]
            if (Pair(x, y) !in routes) {
                directionCounts[i]++
                routes.add(x to y)
                move()
                routes.removeLast()
                directionCounts[i]--
                x -= dx[i]
                y -= dy[i]
            } else {
                x -= dx[i]
                y -= dy[i]
            }
        }
    }
}