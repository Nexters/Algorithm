package _2021._3_1

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * 다음 순열 - 백준
 *
 * 3월 1주차 부터 작성 방식 바꿈
 * https://www.acmicpc.net/problem/10972
 */
class `10972_다음 순열_riflockle7` {
    companion object {
        var columnCursor = 0

        var digits = mutableListOf<Int>()

        @JvmStatic
        fun main(args: Array<String>) {
            input()

            columnCursor = 0
            val newDigits = nextPermutation()

            if (newDigits.isEmpty()) {
                println("-1")
            } else {
                newDigits.forEach { print("$it ") }
            }
        }

        /** 다음 순열 찾기 */
        fun nextPermutation(): List<Int> {
            var firstPivot = -1
            var lastPivot = -1

            // 만약 1자리 수라면 바로 맨 마지막 순열이므로 빈 배열을 리턴한다.
            if (digits.size == 1) return emptyList()

            // 맨 좌측에서부터, 값이 갑자기 내림 차순이 되는 마지막 영역을 찾는다.
            for (index in 0 until digits.size - 1) {
                if (digits[index] < digits[index + 1]) {
                    firstPivot = index
                }
            }

            // 위에 해당되는 영역이 없을 경우, 맨 마지막 순열이므로 빈 배열을 리턴한다.
            if (firstPivot == -1) return emptyList()

            // 맨 우측에서부터, 값이 갑자기 오름 차순이 되는 영역을 찾아, 영역 시작 index 를 새로운 pivot 값으로 갱신한다.
            for (index in digits.size - 1 downTo firstPivot) {
                if (digits[firstPivot] < digits[index]) {
                    lastPivot = index
                    break
                }
            }

            // pivot 위치 숫자와, 맨 마지막에 위치한 숫자 위치 교체
            val swap = digits[lastPivot]
            digits[lastPivot] = digits[firstPivot]
            digits[firstPivot] = swap

            // pivot 기준으로 영역 분리
            val returnList = mutableListOf<Int>().apply { addAll(digits.subList(0, firstPivot + 1)) }
            val lastList = mutableListOf<Int>().apply { addAll(digits.subList(firstPivot + 1, digits.size)) }

            // 뒷 list 오름차순 정렬 후 returnList 에 합침
            lastList.sort()
            returnList.addAll(lastList)

            // returnList 반환
            return returnList
        }

        /** 입력한 데이터 넣기 */
        fun input() {
            val br = BufferedReader(InputStreamReader(System.`in`))

            br.readLine()

            digits.addAll(br.readLine().split(" ").filter { it.isNotEmpty() }.map { it.toInt() })
        }
    }
}