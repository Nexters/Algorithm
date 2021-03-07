import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * 일곱 난쟁이
 *
 * https://www.acmicpc.net/problem/2309
 */
class `2309_일곱 난쟁이_riflockle7` {
    companion object {
        var answer: List<Int> = listOf()

        @JvmStatic
        fun main(args: Array<String>) {
            val inputs = input()

            for (i in inputs.indices) {
                val emptyAnswer = listOf<Int>()
                search(emptyAnswer, i, inputs)
            }

            val answerArray = answer.toTypedArray().apply { sort() }

            answerArray.forEach { println(it) }
        }

        fun search(prevAnswer: List<Int>, startIndex: Int, table: List<Int>) {
            val totalHeight = prevAnswer.sum()
            when {
                totalHeight == 100 && prevAnswer.size == 7 -> answer = prevAnswer
                totalHeight > 100 || startIndex >= table.size -> return
                else -> {
                    val newAnswer: List<Int> = mutableListOf<Int>().apply {
                        addAll(prevAnswer)
                        add(table[startIndex])
                    }
                    for (newStartIndex in (startIndex + 1)..table.size)
                        search(newAnswer, newStartIndex, table)
                }
            }
        }

        /** 입력한 데이터 넣기 */
        fun input(): List<Int> {
            val br = BufferedReader(InputStreamReader(System.`in`))
            val inputs = mutableListOf<Int>()

            for (i in 0..8)
                inputs.addAll(br.readLine().split("\n").map { it.toInt() })

            return inputs
        }
    }
}