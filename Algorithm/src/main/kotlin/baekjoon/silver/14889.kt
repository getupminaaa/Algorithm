package baekjoon.silver

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

private var n = 0
private var min = Int.MAX_VALUE

private var teams = mutableListOf<Int>()
private var team = mutableListOf<Int>()

private var sum = 0

private var start = 0
private var link = 0

private var totalMembers = mutableListOf<Int>()

private var abilityBoard = mutableListOf<MutableList<Int>>()
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    n = readLine().toInt()
    repeat(n) { idx ->
        val temp = readLine().split(" ").map { it.toInt() }
        abilityBoard.add(idx, temp.toMutableList())
        totalMembers.add(idx + 1)
    }
    makeTeams(0)
    println(min)
}

private fun makeTeams(s: Int) {
    if (teams.size == n / 2) {
        combineAbilities(teams, -1)
        start = sum
        sum = 0
        combineAbilities(totalMembers.filterNot { it in teams }.toMutableList(), -1)
        link = sum
        sum = 0

        min = minOf(min, abs(start - link))
        return
    }
    for (i in s + 1..n) {
        teams.add(i)
        makeTeams(i)
        teams.removeLast()
    }
}

private fun combineAbilities(teams: MutableList<Int>, s: Int) {
    if (team.size == 2) {
        sum += calAbilities(team[0], team[1])
        return
    }
    for (i in s + 1..<teams.size) {
        team.add(teams[i])
        combineAbilities(teams, i)
        team.removeLast()
    }
}

private fun calAbilities(i: Int, j: Int) = abilityBoard[i - 1][j - 1] + abilityBoard[j - 1][i - 1]
