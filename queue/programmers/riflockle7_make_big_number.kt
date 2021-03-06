package programmers

import java.lang.StringBuilder

/**
 * 큰 수 만들기
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42883
 */
class riflockle7_make_big_number {
    companion object {
        var answer = StringBuilder()

        @JvmStatic
        fun main(args: Array<String>) {
            println(solution("4177252841", 4))    // 775841
            answer.clear()

            println(solution("1924", 2))          // 94
            answer.clear()

            println(solution("1231234", 3))       // 3234
            answer.clear()

            println(solution("9876554", 4))       // 987
            answer.clear()

            println(solution("3400001", 4))       // 341
            answer.clear()

            println(solution("10", 1))            // 1
            answer.clear()

            println(solution("12349999", 4))      // 1
            answer.clear()

        }

        fun solution(number: String, k: Int): String {
            var avaliableK = number.length - k
            var start = 0

            while (start < number.length) {
                if (number.length - start == avaliableK) {
                    answer.append(number.substring(start, number.length))
                    break
                }

                val (newIndex, value) = greedy(number, start, avaliableK)
                start = newIndex

                if (avaliableK != 0) {
                    answer.append(value)
                    avaliableK--
                } else
                    break
            }

            return answer.toString()
        }

        // 현재 number 에서 최소 k개의 숫자를 남기면서 가장 크게 얻을 수 있는 값을 찾는다.
        fun greedy(number: String, start: Int, k: Int): Pair<Int, Int> {
            var nextIndex = start
            var maxValue = number[start].toString().toInt()

            for (index in start + 1..number.length - k) {
                // 맨 마지막값 하나만 존재할 떄 여기로 로직이 들어오므로 예외처리를 해준다.
                if (index >= number.length) break

                // 이미 9를 찾았을 경우에는 더 이상 반복문을 실행할 필요가 없으므로 종료시킨다. (10번 시간초과)
                if (maxValue == 9) break

                val digit = number[index].toString().toInt()
                if (maxValue < digit) {
                    maxValue = digit
                    nextIndex = index
                }
            }

            return Pair(nextIndex + 1, maxValue)
        }
    }
}