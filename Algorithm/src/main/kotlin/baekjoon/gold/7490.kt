package baekjoon.gold

import java.io.BufferedReader
import java.io.InputStreamReader

private var n = 0
private var testNumbers = mutableListOf<Int>()
private var operators = mutableListOf<String>()
private var defaultOperators = mutableListOf<String>(" ","+", "-")
private var sb = StringBuilder()
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    n = readLine().toInt()
    repeat(n) {
        val temp = readLine().toInt()
        testNumbers.add(temp)
    }
    testNumbers.forEach {
        makeZero(it)
        sb.append("\n")
    }
    println(sb)

}

private fun checkOperator(operator: String, x: Int, y: Int): Int = when (operator) {
    "+" -> x + y
    else -> x - y
}

private fun makeFormula(depth: Int): MutableList<String> {
    val formula = mutableListOf<String>()
    for (i in operators.indices) {
        formula.add("${i + 1}")
        formula.add(operators[i])
        if (i == operators.size - 1) {
            formula.add("${i + 2}")
        }
    }

    val temp = formula.joinToString("").replace(" ", "")
    var currentNumber = StringBuilder()
    val resultFormula = mutableListOf<String>()
    for (char in temp) {
        if (char.isDigit()) {
            currentNumber.append(char)
        } else {
            if (currentNumber.isNotEmpty()) {
                resultFormula.add(currentNumber.toString())
                currentNumber = StringBuilder()
            }
            resultFormula.add(char.toString())
        }
    }

    if (currentNumber.isNotEmpty()) {
        resultFormula.add(currentNumber.toString())
    }
    return resultFormula
}

private fun calculate(formula: MutableList<String>): Int {
    var temp = formula[0].toInt()

    for (i in 1..<formula.size - 1) {
        if (formula[i].toIntOrNull() == null) {
            temp = checkOperator(formula[i], temp, formula[i + 1].toInt())
        }
    }
    return temp
}

private fun makeZero(depth: Int) {
    if (operators.size == depth - 1) {
        val formula = makeFormula(depth)
        val result = calculate(formula)
        if (result == 0) {
            val temp =  formula.joinToString("").replace(Regex("(\\d)(\\d)"), "$1 $2")
            sb.append(temp).append("\n")
        }
        return
    }
    for (i in defaultOperators.indices) {
        operators.add(defaultOperators[i])
        makeZero(depth)
        operators.removeLast()
    }
}