import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * 연산자 끼워넣기
 *
 * https://www.acmicpc.net/problem/14888
 */
object `14888_연산자 끼워넣기_riflockle7` {
    var min = Int.MAX_VALUE
    var max = Int.MIN_VALUE

    @JvmStatic
    fun main(args: Array<String>) {
        val (count, numbers, operations) = input()

        start(0, numbers, operations, 0)

        println("$max\n$min")
    }

    /**
     * 연산을 진행하는 재귀 함수
     *
     * @param answer 연산 직전에 가지고 있던 답
     * @param numbers 아직 연산을 하지 않은 숫자 list
     * @param remainOperations 아직 사용하지 않은 연산자 list
     * @param operationIndex [연산자 list][remainOperations] 에서 이번 함수에서 사용할 연산자 index
     */
    private fun start(answer: Int, numbers: List<Int>, remainOperations: List<Int>, operationIndex: Int) {
        // 연산 다 끝난 상태가 아니라면 연산을 해준다.
        if (numbers.isNotEmpty()) {
            // 다음 재귀함수에서 사용할 배열 세팅
            val newNumbers = mutableListOf<Int>().apply { addAll(numbers) }
            val newRemainOperations = mutableListOf<Int>().apply { addAll(remainOperations) }

            // 사용할 숫자, 연산자를 뽑아낸다.
            val numberValue = newNumbers.removeAt(0)
            val operationValue = newRemainOperations.removeAt(operationIndex)

            // 계산한다.
            val newAnswer = calculate(answer, operationValue, numberValue)

            // 더 이상 계산하지 않을 경우 min max 를 판별한다.
            if (newRemainOperations.isEmpty()) {
                min = Math.min(min, newAnswer)
                max = Math.max(max, newAnswer)
            }
            // 아직 계산이 남았을 경우 다양한 연산자를 사용하는 경우의 수만큼 다음 연산을 진행한다.
            else {
                newRemainOperations.forEachIndexed { nextOperationIndex, _ ->
                    start(newAnswer, newNumbers, newRemainOperations, nextOperationIndex)
                }
            }
        }
        // 연산할 숫자, 연산자가 없는 경우 min max 판별을 해준다.
        else {
            min = Math.min(min, answer)
            max = Math.max(max, answer)
        }
    }

    /** 계산한다. */
    private fun calculate(one: Int, operation: Int, two: Int): Int {
        return when (operation) {
            0 -> one + two
            1 -> one - two
            2 -> one * two
            else -> one / two
        }
    }

    /**
     * 초기값 세팅
     * 연산자 list 에서 맨 처음에는 덧셈 연산을 취해주기 맨 앞에 0(+) 을 넣는다.
     */
    private fun input(): Triple<Int, List<Int>, List<Int>> {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val count = br.readLine().toInt()

        val numbers = br.readLine().split(" ").map { it.toInt() }

        val operationTable = br.readLine().split(" ").map { it.toInt() }
        val operations = mutableListOf<Int>().apply { add(0) }
        operationTable.forEachIndexed { operation, size ->
            for (i in 0 until size) {
                operations.add(operation)
            }
        }

        return Triple(count, numbers, operations)
    }
}