import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val arr = mutableListOf<Int>()

    repeat(n) {
        val number = readLine().toInt()
        if (number == 0) {
            arr.removeLast()
        } else {
            arr.add(number)
        }
    }
    print(arr.sum())
}