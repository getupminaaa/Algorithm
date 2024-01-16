package baekjoon.gold

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.system.exitProcess

private var sudoku = MutableList(9) { MutableList<Int>(9) { 0 } }
private var blankArr = mutableListOf<Pair<Int, Int>>()
private val sb = StringBuilder()

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    for (i in 0..<9) {
        val temp = readLine().split(" ").map { it.toInt() }
        sudoku[i] = temp.toMutableList()
    }
    for (i in 0..<9) {
        for (j in 0..<9) {
            if (sudoku[i][j] == 0) {
                blankArr.add(Pair(i, j))
            }
        }
    }
    solve(0)
}


private fun checkRow(p: Pair<Int, Int>, value: Int): Boolean {
    for (i in 0..<sudoku[0].size) {
        if (sudoku[p.first][i] == value) {
            return false
        }
    }
    return true
}

private fun checkCol(p: Pair<Int, Int>, value: Int): Boolean {
    for (i in 0..<sudoku[0].size) {
        if (sudoku[i][p.second] == value) {
            return false
        }
    }
    return true
}

private fun checkSquare(p: Pair<Int, Int>, value: Int): Boolean {
    val x = p.first - (p.first.mod(3))
    val y = p.second - (p.second.mod(3))

    for (i in x..x + 2) {
        for (j in y..y + 2) {
            if (sudoku[i][j] == value) {
                return false
            }
        }
    }
    return true
}

//스도쿠 함수
private fun solve(depth: Int) {
    if (depth == blankArr.size) {
        sudoku.forEach {
            sb.append(it.joinToString(" ")).append("\n")
        }
        println(sb)
        exitProcess(0)
    }
    for (i in 1..9) {
        val position = blankArr[depth]
        if (checkRow(position, i) && checkCol(position, i) && checkSquare(position, i)) {
            sudoku[position.first][position.second] = i
            solve(depth + 1)
            sudoku[position.first][position.second] = 0
        }
    }
}