package baekjoon.gold

import java.io.BufferedReader
import java.io.InputStreamReader

private val baseWord = mutableListOf<String>()
private val blankIndexes = mutableListOf<Int>()
private var count = 0
private val vowels = listOf("A", "E", "I", "O", "U")

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val temp = readLine().chunked(1).map { baseWord.add(it) }
    baseWord.forEachIndexed { idx, it -> if (it == "_") blankIndexes.add(idx) }

    println(makeJoyfulWord(0, 0, 0, 0))
}

fun makeJoyfulWord(depth: Int, vCnt: Int, cCnt: Int, l: Int): Long {
    var ans: Long = 0
    if (vCnt >= 3 || cCnt >= 3) {
        return 0
    }

    if(depth == baseWord.size){
        return if(l >= 1){
            1
        }else{
            0
        }
    }


    if (baseWord[depth] == "_") {
        ans += 5 * makeJoyfulWord(depth + 1, vCnt + 1, 0, l)
        ans += 20 * makeJoyfulWord(depth + 1, 0, cCnt + 1, l)
        ans += makeJoyfulWord(depth + 1, 0, cCnt + 1, l + 1)
    } else {
        if (baseWord[depth] in vowels) {
            ans = makeJoyfulWord(depth + 1, vCnt + 1, 0, l)
        } else {
            if (baseWord[depth] == "L") {
                ans = makeJoyfulWord(depth + 1, 0, cCnt + 1, l + 1)
            } else {
                ans = makeJoyfulWord(depth + 1, 0, cCnt + 1, l)
            }
        }

    }

    return ans
}