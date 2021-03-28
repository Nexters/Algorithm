package _2021._3_1

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

/**
 * 전광판의 숫자 - 백준
 * (76% 에서 틀렸다고 걸러지는 데 순열도 문제가 아니고, 강제 값으로 세팅해도 문제가 아님)
 *
 * https://www.acmicpc.net/problem/16159
 */
class `16159_전광판의 숫자_riflockle7` {
    companion object {
        var lineLength = 0
        var columnCursor = 0

        var table: List<StringBuilder> = mutableListOf(
                StringBuilder(),
                StringBuilder(),
                StringBuilder(),
                StringBuilder(),
                StringBuilder(),
                StringBuilder(),
                StringBuilder()
        )

        var answerTable: List<StringBuilder> = mutableListOf(
                StringBuilder(),
                StringBuilder(),
                StringBuilder(),
                StringBuilder(),
                StringBuilder(),
                StringBuilder(),
                StringBuilder()
        )

        var digits = mutableListOf<Int>()

        @JvmStatic
        fun main(args: Array<String>) {
            input()

            columnCursor = 0
            val newDigits = nextPermutation()

            if (newDigits.isEmpty()) {
                println("The end")
            } else {
                newDigits.forEach { it.inputNumber() }
                answerTable.forEach { cmd -> println(cmd) }

//                newDigits.forEach { print("$it") }
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

        /** 전광판에 대응하는 숫자값 가져오기 */
        fun getNumber(): Int {
            return when {
                null.isZero() -> 0
                null.isOne() -> 1
                null.isTwo() -> 2
                null.isThree() -> 3
                null.isFour() -> 4
                null.isFive() -> 5
                null.isSix() -> 6
                null.isSeven() -> 7
                null.isEight() -> 8
                else -> 9
            }
        }

        /** 알맞은 숫자 answerTable 에 추가하기 */
        fun Int.inputNumber() {
            when {
                this.isZero() -> inputZero()
                this.isOne() -> inputOne()
                this.isTwo() -> inputTwo()
                this.isThree() -> inputThree()
                this.isFour() -> inputFour()
                this.isFive() -> inputFive()
                this.isSix() -> inputSix()
                this.isSeven() -> inputSeven()
                this.isEight() -> inputEight()
                this.isNine() -> inputNine()
            }
        }

        fun Int?.isZero(): Boolean {
            return if (this != null)
                this == 0
            else {
                table[0].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000000" &&
                        table[1].substring(columnCursor * 6, (columnCursor + 1) * 6) == "001100" &&
                        table[2].substring(columnCursor * 6, (columnCursor + 1) * 6) == "010010" &&
                        table[3].substring(columnCursor * 6, (columnCursor + 1) * 6) == "010010" &&
                        table[4].substring(columnCursor * 6, (columnCursor + 1) * 6) == "010010" &&
                        table[5].substring(columnCursor * 6, (columnCursor + 1) * 6) == "001100" &&
                        table[6].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000000"
            }
        }

        fun inputZero() {
            answerTable[0].append("000000")
            answerTable[1].append("001100")
            answerTable[2].append("010010")
            answerTable[3].append("010010")
            answerTable[4].append("010010")
            answerTable[5].append("001100")
            answerTable[6].append("000000")
        }

        fun Int?.isOne(): Boolean {
            return if (this != null)
                this == 1
            else
                table[0].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000000" &&
                        table[1].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000100" &&
                        table[2].substring(columnCursor * 6, (columnCursor + 1) * 6) == "001100" &&
                        table[3].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000100" &&
                        table[4].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000100" &&
                        table[5].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000100" &&
                        table[6].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000000"
        }

        fun inputOne() {
            answerTable[0].append("000000")
            answerTable[1].append("000100")
            answerTable[2].append("001100")
            answerTable[3].append("000100")
            answerTable[4].append("000100")
            answerTable[5].append("000100")
            answerTable[6].append("000000")
        }

        fun Int?.isTwo(): Boolean {
            return if (this != null)
                this == 2
            else
                table[0].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000000" &&
                        table[1].substring(columnCursor * 6, (columnCursor + 1) * 6) == "011110" &&
                        table[2].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000010" &&
                        table[3].substring(columnCursor * 6, (columnCursor + 1) * 6) == "011110" &&
                        table[4].substring(columnCursor * 6, (columnCursor + 1) * 6) == "010000" &&
                        table[5].substring(columnCursor * 6, (columnCursor + 1) * 6) == "011110" &&
                        table[6].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000000"
        }

        fun inputTwo() {
            answerTable[0].append("000000")
            answerTable[1].append("011110")
            answerTable[2].append("000010")
            answerTable[3].append("011110")
            answerTable[4].append("010000")
            answerTable[5].append("011110")
            answerTable[6].append("000000")
        }

        fun Int?.isThree(): Boolean {
            return if (this != null)
                this == 3
            else
                table[0].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000000" &&
                        table[1].substring(columnCursor * 6, (columnCursor + 1) * 6) == "011100" &&
                        table[2].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000010" &&
                        table[3].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000100" &&
                        table[4].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000010" &&
                        table[5].substring(columnCursor * 6, (columnCursor + 1) * 6) == "011100" &&
                        table[6].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000000"
        }

        fun inputThree() {
            answerTable[0].append("000000")
            answerTable[1].append("011100")
            answerTable[2].append("000010")
            answerTable[3].append("000100")
            answerTable[4].append("000010")
            answerTable[5].append("011100")
            answerTable[6].append("000000")
        }

        fun Int?.isFour(): Boolean {
            return if (this != null)
                this == 4
            else
                table[0].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000000" &&
                        table[1].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000100" &&
                        table[2].substring(columnCursor * 6, (columnCursor + 1) * 6) == "001100" &&
                        table[3].substring(columnCursor * 6, (columnCursor + 1) * 6) == "010100" &&
                        table[4].substring(columnCursor * 6, (columnCursor + 1) * 6) == "111110" &&
                        table[5].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000100" &&
                        table[6].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000000"
        }

        fun inputFour() {
            answerTable[0].append("000000")
            answerTable[1].append("000100")
            answerTable[2].append("001100")
            answerTable[3].append("010100")
            answerTable[4].append("111110")
            answerTable[5].append("000100")
            answerTable[6].append("000000")
        }

        fun Int?.isFive(): Boolean {
            return if (this != null)
                this == 5
            else
                table[0].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000000" &&
                        table[1].substring(columnCursor * 6, (columnCursor + 1) * 6) == "011110" &&
                        table[2].substring(columnCursor * 6, (columnCursor + 1) * 6) == "010000" &&
                        table[3].substring(columnCursor * 6, (columnCursor + 1) * 6) == "011100" &&
                        table[4].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000010" &&
                        table[5].substring(columnCursor * 6, (columnCursor + 1) * 6) == "010010" &&
                        table[6].substring(columnCursor * 6, (columnCursor + 1) * 6) == "001100"
        }

        fun inputFive() {
            answerTable[0].append("000000")
            answerTable[1].append("011110")
            answerTable[2].append("010000")
            answerTable[3].append("011100")
            answerTable[4].append("000010")
            answerTable[5].append("010010")
            answerTable[6].append("001100")
        }

        fun Int?.isSix(): Boolean {
            return if (this != null)
                this == 6
            else
                table[0].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000000" &&
                        table[1].substring(columnCursor * 6, (columnCursor + 1) * 6) == "010000" &&
                        table[2].substring(columnCursor * 6, (columnCursor + 1) * 6) == "010000" &&
                        table[3].substring(columnCursor * 6, (columnCursor + 1) * 6) == "011110" &&
                        table[4].substring(columnCursor * 6, (columnCursor + 1) * 6) == "010010" &&
                        table[5].substring(columnCursor * 6, (columnCursor + 1) * 6) == "011110" &&
                        table[6].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000000"
        }

        fun inputSix() {
            answerTable[0].append("000000")
            answerTable[1].append("010000")
            answerTable[2].append("010000")
            answerTable[3].append("011110")
            answerTable[4].append("010010")
            answerTable[5].append("011110")
            answerTable[6].append("000000")
        }

        fun Int?.isSeven(): Boolean {
            return if (this != null)
                this == 7
            else
                table[0].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000000" &&
                        table[1].substring(columnCursor * 6, (columnCursor + 1) * 6) == "011110" &&
                        table[2].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000010" &&
                        table[3].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000100" &&
                        table[4].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000100" &&
                        table[5].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000100" &&
                        table[6].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000000"
        }

        fun inputSeven() {
            answerTable[0].append("000000")
            answerTable[1].append("011110")
            answerTable[2].append("000010")
            answerTable[3].append("000100")
            answerTable[4].append("000100")
            answerTable[5].append("000100")
            answerTable[6].append("000000")
        }

        fun Int?.isEight(): Boolean {
            return if (this != null)
                this == 8
            else
                table[0].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000000" &&
                        table[1].substring(columnCursor * 6, (columnCursor + 1) * 6) == "011110" &&
                        table[2].substring(columnCursor * 6, (columnCursor + 1) * 6) == "010010" &&
                        table[3].substring(columnCursor * 6, (columnCursor + 1) * 6) == "011110" &&
                        table[4].substring(columnCursor * 6, (columnCursor + 1) * 6) == "010010" &&
                        table[5].substring(columnCursor * 6, (columnCursor + 1) * 6) == "011110" &&
                        table[6].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000000"
        }

        fun inputEight() {
            answerTable[0].append("000000")
            answerTable[1].append("011110")
            answerTable[2].append("010010")
            answerTable[3].append("011110")
            answerTable[4].append("010010")
            answerTable[5].append("011110")
            answerTable[6].append("000000")
        }

        fun Int?.isNine(): Boolean {
            return if (this != null)
                this == 9
            else
                table[0].substring(columnCursor * 6, (columnCursor + 1) * 6) == "011110" &&
                        table[1].substring(columnCursor * 6, (columnCursor + 1) * 6) == "010010" &&
                        table[2].substring(columnCursor * 6, (columnCursor + 1) * 6) == "010010" &&
                        table[3].substring(columnCursor * 6, (columnCursor + 1) * 6) == "011110" &&
                        table[4].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000010" &&
                        table[5].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000010" &&
                        table[6].substring(columnCursor * 6, (columnCursor + 1) * 6) == "000010"
        }

        fun inputNine() {
            answerTable[0].append("011110")
            answerTable[1].append("010010")
            answerTable[2].append("010010")
            answerTable[3].append("011110")
            answerTable[4].append("000010")
            answerTable[5].append("000010")
            answerTable[6].append("000010")
        }

        /** 입력한 데이터 넣기 */
        fun input() {
            val br = BufferedReader(InputStreamReader(System.`in`))

            for (i in 0 until 7) {
                val cmd = br.readLine()
                lineLength = cmd.length

                table[i].append(cmd)
            }
            while (columnCursor * 6 < lineLength) {
                digits.add(getNumber())
                columnCursor++
            }

//            digits.addAll(br.readLine().split("").filter { it.isNotEmpty() }.map { it.toInt() })
        }
    }
}
