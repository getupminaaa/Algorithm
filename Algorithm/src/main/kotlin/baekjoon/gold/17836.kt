package baekjoon.gold

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.abs
import kotlin.math.min

private var n = 0
private var m = 0
private var t = 0
private var castle = mutableListOf<MutableList<Int>>()
private val dx = intArrayOf(-1, 0, 1, 0)
private val dy = intArrayOf(0, 1, 0, -1)
private var sword = -1 to -1
private var specialT = 0
private var basicT = 0
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val temp = readLine().split(" ").map { it.toInt() }
    n = temp[0]
    m = temp[1]
    t = temp[2]

    repeat(n) {
        val tmp = readLine().split(" ").map { it.toInt() }
        castle.add(tmp.toMutableList())
    }

    for (i in 0..<n) {
        for (j in 0..<m) {
            if (castle[i][j] == 2) {
                sword = Pair(i, j)
            }
        }
    }

    bfs()

    var ans = if(basicT == -1 && specialT == 0 ) {
        -1
    }else if(basicT == -1 && specialT > 0){
        specialT
    }else{
        min(specialT, basicT)
    }

    if(ans == -1 || ans > t){
        println("Fail")
    }else{
        println(ans)
    }

}

private fun bfs() {
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    val visitInfo = MutableList(n) { MutableList(m) { -1 } }

    queue.add(Pair(0, 0))
    visitInfo[0][0] = 0

    while (queue.isNotEmpty()) {
        val (x, y) = queue.poll()

        //칼 줍기
        if (x == sword.first && y == sword.second) {
            specialT = abs(n - 1 - sword.first) + abs(m - 1 - sword.second) + visitInfo[x][y]
        }


        for (i in 0..3) {
            val tx = x + dx[i]
            val ty = y + dy[i]

            //범위 밖으로 벗어나지 않게 조건 넣어주고, visited == false인지 확인한다.
            if (tx in 0..<n && ty in 0..<m && castle[tx][ty] != 1 && visitInfo[tx][ty] == -1) {
                queue.add(Pair(tx, ty))
                visitInfo[tx][ty] = visitInfo[x][y] + 1
                //2를 안거쳤을 때

            }
        }
    }
    basicT = visitInfo[n - 1][m - 1]
}

//private fun specialBfs() {
//    val queue: Queue<Pair<Int, Int>> = LinkedList()
//    val visitInfo = MutableList(n) { MutableList(m) { -1 } }
//
//    queue.add(Pair(0, 0))
//    visitInfo[0][0] = 0
//
//    while (queue.isNotEmpty()) {
//
//        val (x, y) = queue.poll()
//
//        for (i in 0..3) {
//            val tx = x + dx[i]
//            val ty = y + dy[i]
//
//            if (tx == sword.first && ty == sword.second) {
//                visitInfo[tx][ty] = visitInfo[x][y] + 1
//
//                specialT = abs(n - sword.first) + abs(m - sword.second) + visitInfo[sword.first][sword.second] - 2
//                ans = min(basicT, specialT)
//            }
//
//            //범위 밖으로 벗어나지 않게 조건 넣어주고, visited == false인지 확인한다.
//            if (tx in 0..sword.first && ty in 0..sword.second && castle[tx][ty] == 0 && visitInfo[tx][ty] == -1) {
//
//                queue.add(Pair(tx, ty))
//                visitInfo[tx][ty] = visitInfo[x][y] + 1
//            }
//        }
//    }
//}
