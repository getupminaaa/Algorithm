package baekjoon.gold

import java.io.BufferedReader
import java.io.InputStreamReader

private var vowels = mutableListOf<String>()
private var consonants = mutableListOf<String>()
private var password = mutableListOf<String>()
private var result = mutableListOf<String>()

private val sb = StringBuilder()
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (l, c) = readLine().split(" ").map { it.toInt() }
    val cList = readLine().split(" ")
    val vowelLetter = arrayListOf("a", "e", "i", "o", "u")
    vowels = cList.filter { it in vowelLetter }.toMutableList()
    consonants = cList.filterNot { it in vowelLetter }.toMutableList()

    findPwd(l, c, cList, -1)
    result = result.sorted().toMutableList()

    for (i in result.indices) {
        sb.append(result[i]).append("\n")
    }

    println(sb)
}

private fun findPwd(l: Int, c: Int, cList: List<String>, s: Int) {
    if (password.size == l) {
        if (password.count { it in vowels } >= 1 && password.count { it in consonants } >= 2) {
            result.add(password.sorted().joinToString(""))
        }
        return
    }
    for (i in s + 1..<c) {
        password.add(cList[i])
        findPwd(l, c, cList, i)
        password.removeLast()
    }
}