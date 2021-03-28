package _2021._3_1

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

/**
 * 시그널 - 백준
 *
 * 3월 1주차 부터 작성 방식 바꿈
 * https://www.acmicpc.net/problem/16113
 */
class `16113_시그널_riflockle7` {
    companion object {
        // 외계어의 전체 길이
        var signalLength = 0

        // 한 줄의 라인 길이
        var lineLength = 0

        // 현재 어느 열을 가리키고 있는지를 가리킴
        var columnCursor = 0

        // 외계어를 테이블화 시킨 내용 (그 그림)
        var table: List<MutableList<String>> = mutableListOf(
                mutableListOf(),
                mutableListOf(),
                mutableListOf(),
                mutableListOf(),
                mutableListOf()
        )
        var answer = StringBuilder()

        @JvmStatic
        fun main(args: Array<String>) {
            input()

            while (columnCursor < lineLength) {
                if (!isColumnEmpty(columnCursor)) {
                    answer.append("${start(columnCursor)}")
                } else
                    columnCursor++
            }

            print(answer)
        }

        fun start(column: Int): Int {
            // 먼저 1인지 판별하기
            if (isOne(column)) {
                columnCursor++
                return 1
            }

            // 아니라면 여기서부터 획 분석 시작
            val digits = mutableSetOf(0, 2, 3, 4, 5, 6, 7, 8, 9)

            // 1 영역 체크
            if (table[1][column].isWhite()) {            // 1 white
                digits.remove(0)
                digits.remove(4)
                digits.remove(5)
                digits.remove(6)
                digits.remove(8)
                digits.remove(9)
            } else if (table[1][column].isBlack()) {     // 1 black
                digits.remove(2)
                digits.remove(3)
                digits.remove(7)
            }

            // 2 영역 체크
            if (table[0][column + 1].isWhite()) {        // 2 white
                digits.remove(0)
                digits.remove(2)
                digits.remove(3)
                digits.remove(5)
                digits.remove(6)
                digits.remove(7)
                digits.remove(8)
                digits.remove(9)
            } else if (table[0][column + 1].isBlack()) { // 2 black
                digits.remove(4)
            }

            // 3 영역 체크
            if (table[1][column + 2].isWhite()) {        // 3 white
                digits.remove(0)
                digits.remove(2)
                digits.remove(3)
                digits.remove(4)
                digits.remove(7)
                digits.remove(8)
                digits.remove(9)
            } else if (table[1][column + 2].isBlack()) { // 3 black
                digits.remove(5)
                digits.remove(6)
            }

            // 4 영역 체크
            if (table[2][column + 1].isWhite()) {        // 4 white
                digits.remove(2)
                digits.remove(3)
                digits.remove(4)
                digits.remove(5)
                digits.remove(6)
                digits.remove(8)
                digits.remove(9)
            } else if (table[2][column + 1].isBlack()) { // 4 black
                digits.remove(0)
                digits.remove(7)
            }

            // 5 영역 체크
            if (table[3][column].isWhite()) {            // 5 white
                digits.remove(0)
                digits.remove(2)
                digits.remove(6)
                digits.remove(8)
            } else if (table[3][column].isBlack()) {     // 5 black
                digits.remove(3)
                digits.remove(4)
                digits.remove(5)
                digits.remove(7)
                digits.remove(9)
            }

            // 6 영역 체크
            if (table[4][column + 1].isWhite()) {        // 6 white
                digits.remove(0)
                digits.remove(2)
                digits.remove(3)
                digits.remove(5)
                digits.remove(6)
                digits.remove(8)
                digits.remove(9)
            } else if (table[4][column + 1].isBlack()) { // 6 black
                digits.remove(4)
                digits.remove(7)
            }

            // 7 영역 체크
            if (table[3][column + 2].isWhite()) {        // 7 white
                digits.remove(0)
                digits.remove(3)
                digits.remove(4)
                digits.remove(5)
                digits.remove(6)
                digits.remove(7)
                digits.remove(8)
                digits.remove(9)
            } else if (table[3][column + 2].isBlack()) { // 7 black
                digits.remove(2)
            }

            columnCursor += 3

            // 위 유효성 체크들을 하면 적어도 하나는 걸린다.
            return digits.first()
        }

        /** 세로열 자체가 빈 값인지 확인 */
        fun isColumnEmpty(column: Int): Boolean {
            var isColumnEmpty = true
            table.forEach {
                if (it[column] != ".") {
                    isColumnEmpty = false
                    return@forEach
                }
            }
            return isColumnEmpty
        }

        /** 숫자가 1인지 자체를 확인 */
        fun isOne(column: Int): Boolean {
            var isColumnBlack = true

            table.forEach {
                if (it[column] != "#") {
                    isColumnBlack = false
                    return@forEach
                }
            }

            return if (isColumnBlack && column + 1 == lineLength)
                isColumnBlack
            else
                isColumnEmpty(column + 1)
        }

        /** 특정 문자열이 black 인지 확인 */
        fun String.isBlack(): Boolean = this == "#"

        /** 특정 문자열이 black 인지 확인 */
        fun String.isWhite(): Boolean = this == "."

        /** 입력한 데이터 넣기 */
        fun input() {
            val br = BufferedReader(InputStreamReader(System.`in`))
            signalLength = br.readLine().toInt()
            lineLength = signalLength / 5
            val cmd = br.readLine()

            for (i in 0 until 5) {
                val cmdChunkLine = cmd.substring(i * lineLength, (i + 1) * lineLength)
                table[i].addAll(cmdChunkLine.split("").filter { it.isNotEmpty() })
            }
        }
    }
}
