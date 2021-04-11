import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.min

/**
 * 2138_전구와 스위치_riflockle7
 *
 * 삽질 내역
 * 1. 모든 경우의 수 다 구하면서 해서 [메모리 초과]
 * 2. 하나의 인덱스 체크할때마다 배열 같은지 체크해서 [시간 초과]
 * 3. Int.MAX_VALUE 그대로 나와서 틀림
 *
 * https://www.acmicpc.net/problem/2138
 */
class `2138_전구와 스위치_riflockle7` {
    companion object {
        var N = -1
        var answer = Int.MAX_VALUE
        val before = mutableListOf<Boolean>()
        val after = mutableListOf<Boolean>()

        @JvmStatic
        fun main(args: Array<String>) {
            input()

            if (after.isEmpty())
                print("-1")
            else {
                val firstScenario = before.toMutableList()
                val secondScenario = before.toMutableList().apply { this.turnSwitch(0) }

                arrayOf(firstScenario, secondScenario).forEachIndexed { index, it ->
                    var newAnswer = if (index == 0) 0 else 1
                    for (index in 1 until N) {
                        newAnswer += it.check(index)
                    }

                    if (after == it) {
                        answer = min(answer, newAnswer)
                    }
                }

                if (answer == Int.MAX_VALUE)
                    print("-1")
                else
                    print("$answer")
            }
        }

        fun MutableList<Boolean>.turnSwitch(index: Int) {
            if (index >= 1) this[index - 1] = !(this[index - 1])
            this[index] = !this[index]
            if (index <= size - 2) this[index + 1] = !(this[index + 1])
        }

        fun MutableList<Boolean>.check(index: Int): Int {
            return if (index >= 1) {
                if (this[index - 1] != after[index - 1]) {
                    this.turnSwitch(index)
                    1
                } else
                    0
            } else
                0
        }

        /** 입력한 데이터 넣기 */
        fun input() {
            val br = BufferedReader(InputStreamReader(System.`in`))

            N = br.readLine().toInt()

            arrayOf(before, after).forEach { list ->
                list.addAll(
                        br.readLine().split("")
                                .filter { "" != it }
                                .map { it.toInt() == 1 }
                )
            }
        }
    }
}
