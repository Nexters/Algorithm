import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * 동전0 - 백준
 * 그리디 알고리즘의 기초 문제
 *
 * https://www.acmicpc.net/problem/11047
 */
class riflockle7_coin_zero {
    companion object {
        var N = Int.MIN_VALUE
        var K = Int.MIN_VALUE
        var N_Infos = listOf<Int>()
        var answer = Int.MAX_VALUE

        @JvmStatic
        fun main(args: Array<String>) {
            input().let {
                this.N = it.first
                this.K = it.second
                this.N_Infos = it.third.reversed()
            }

            // greedy 는 가장 가까운 값을 취한다. 마시멜로 같이
            for (index in N_Infos.indices)
                greedy(0, K, index)

            print(answer)
        }

        fun greedy(newAnswer: Int, remains: Int, index: Int) {
            if (remains == 0)
                answer = newAnswer.coerceAtMost(answer)
            else if (index == N_Infos.size) {
                return
            } else {
                val number = remains / N_Infos[index]

                if (number != 0)
                    greedy(newAnswer + number, remains - number * N_Infos[index], index + 1)
                else
                    greedy(newAnswer, remains, index + 1)
            }
        }

        /** 입력한 데이터 넣기 */
        fun input(): Triple<Int, Int, List<Int>> {
            val br = BufferedReader(InputStreamReader(System.`in`))
            val firstLine = br.readLine().split(" ")
            val N = firstLine.first().toInt()
            val K = firstLine.last().toInt()

            val N_Infos = mutableListOf<Int>()
            for (day in 0 until N) {
                val inputs = br.readLine().split(" ")
                N_Infos.add(inputs[0].toInt())
            }

            return Triple(N, K, N_Infos)
        }
    }
}
