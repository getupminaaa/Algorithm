package baekjoon.gold

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

private lateinit var cityMap: MutableList<MutableList<Int>>
private var homeMap = mutableListOf<Pair<Int, Int>>()
private var chickenMap = mutableListOf<Pair<Int, Int>>()
private var restaurants = mutableListOf<Pair<Int, Int>>()
private var distance = 0
private var distances = mutableListOf<Int>()

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    cityMap = MutableList(n) { MutableList(n) { 0 } }
    for (i in 0..<n) {
        val temp = readLine().split(" ").map { it.toInt() }
        cityMap[i] = temp.toMutableList()
    }
    getCoordinate()
    getChickenDistance(m, -1)
    println(distances.min())
}

fun getChickenDistance(m: Int, s: Int) {
    if (restaurants.size == m) {
        getMinDistance()
        distances.add(distance)
        distance = 0
        return
    }
    for (i in s + 1..<chickenMap.size) {
        restaurants.add(chickenMap[i])
        getChickenDistance(m, i)
        restaurants.removeLast()
    }
}

fun getMinDistance() {
    for (i in homeMap.indices) {
        var min = Int.MAX_VALUE
        for (j in restaurants.indices) {
            val temp = calDistance(homeMap[i], restaurants[j])
            min = minOf(min, temp)
        }
        distance += min
    }
}

fun getCoordinate() {
    for (i in cityMap.indices) {
        for (j in cityMap[i].indices) {
            if (cityMap[i][j] == 1) {
                homeMap.add(Pair(i, j))
            } else if (cityMap[i][j] == 2) {
                chickenMap.add(Pair(i, j))
            }
        }
    }
}

fun calDistance(a: Pair<Int, Int>, b: Pair<Int, Int>): Int {
    return abs(a.first - b.first) + abs(a.second - b.second)
}

