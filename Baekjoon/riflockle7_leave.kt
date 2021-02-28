import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * 퇴사
 *
 * https://www.acmicpc.net/problem/14501
 */
class riflockle7_leave {
    companion object {
        var maxAmounts = Int.MIN_VALUE

        @JvmStatic
        fun main(args: Array<String>) {
            val (totalDay, workDays, workPrices) = input()

            // 첫날에 일하는 것부터 시작
            for (dayPivot in 0 until totalDay)
                start(0, dayPivot, totalDay, workDays, workPrices)

            print(maxAmounts)
        }

        /**
         * 해당 날짜에 일을 할 수 있는지 확인하고 새로운 답안을 도출하는 재귀함수
         * 가능한 경우의 수가 또 있는 경우 그에 대해서도 반복문을 실행한다.
         *
         * @param answer 답안 후보
         * @param dayPivot 당일(n + 1) 에 일할 수 있는지 체크할 일차
         * @param totalDay N
         * @param workDays 상담 일정표 T 테이블
         * @param workPrices 상담 일정표 P 테이블
         */
        fun start(answer: Int, dayPivot: Int, totalDay: Int, workDays: List<Int>, workPrices: List<Int>) {
            // 해당 일자에 일이 가능한지를 확인한다
            var (newAnswer, newDayPivot) = if (isWorkValid(totalDay, dayPivot, workDays[dayPivot])) {
                // 가능하면 계산하고 다음 값으로 넘어간다.
                Pair(answer + workPrices[dayPivot], dayPivot + workDays[dayPivot])
            } else {
                Pair(answer, dayPivot + 1)
            }

            // 아직 더 일할 수 있는 경우 반복문을 돌린다.
            if (newDayPivot < totalDay)
                for (checkDayPivot in newDayPivot until totalDay)
                    start(newAnswer, checkDayPivot, totalDay, workDays, workPrices)
            else
                maxAmounts = Math.max(newAnswer, maxAmounts)
        }

        /**
         * 해당 일자에 일이 가능한지 확인한다.
         *
         * @param totalDay N
         * @param targetDay 체크할 일차 (i)
         * @param workDay 체크할 일차에 일하는 데 걸리는 시간 (P[i])
         */
        fun isWorkValid(totalDay: Int, targetDay: Int, workDay: Int): Boolean {
            return (targetDay + workDay <= totalDay)
        }

        /** 입력한 데이터 넣기 */
        fun input(): Triple<Int, List<Int>, List<Int>> {
            val br = BufferedReader(InputStreamReader(System.`in`))
            val totalDay = br.readLine().toInt()
            val workDays = mutableListOf<Int>()
            val workPrices = mutableListOf<Int>()

            for (day in 0 until totalDay) {
                val inputs = br.readLine().split(" ")
                workDays.add(inputs[0].toInt())
                workPrices.add(inputs[1].toInt())
            }

            return Triple(totalDay, workDays, workPrices)
        }
    }
}