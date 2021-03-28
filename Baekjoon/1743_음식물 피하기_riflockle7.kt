package _2021._3_2

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * 음식물 피하기 - 백준
 * 1743_음식물 피하기_riflockle7
 *
 * https://www.acmicpc.net/problem/1743
 */
class `1743_음식물 피하기_riflockle7` {
    companion object {
        var table: MutableList<MutableList<Int>> = mutableListOf()
        var maxX: Int = -1
        var maxY: Int = -1

        val up = Point(0, 1)
        val down = Point(0, -1)
        val left = Point(-1, 0)
        val right = Point(1, 0)

        var answer = Int.MIN_VALUE

        @JvmStatic
        fun main(args: Array<String>) {
            input()

            for (y in 1..maxY) {
                for (x in 1..maxX) {
                    answer = start(x, y).coerceAtLeast(answer)
                }
            }

            print(answer)
        }

        fun start(x: Int, y: Int) : Int {
            if (x < 1 || y < 1 || x > maxX || y > maxY) return 0

            return if(table[y][x] == 1){
                var newAnswer = 1
                table[y][x] = 0

                newAnswer += start(x + up.x, y + up.y)
                newAnswer += start(x + down.x, y + down.y)
                newAnswer += start(x + left.x, y + left.y)
                newAnswer += start(x + right.x, y + right.y)

                newAnswer
            } else {
                0
            }
        }

        /** 입력한 데이터 넣기 */
        fun input() {
            val br = BufferedReader(InputStreamReader(System.`in`))

            val tableInfo = br.readLine().split(" ").map { it.toInt() }

            maxX = tableInfo[1]
            maxY = tableInfo[0]
            table.add(mutableListOf())          // 0 인덱스 계산하기 싫어 0 행 강제 주입
            for (y in 0 until tableInfo[0]) {
                val tuple = mutableListOf(0)    // 0 인덱스 계산하기 싫어 0 열 강제 주입
                for (x in 0 until tableInfo[1]) {
                    tuple.add(0)
                }
                table.add(tuple)
            }

            for (number in 0 until tableInfo[2]) {
                val point = br.readLine().split(" ").map { it.toInt() }
                table[point.first()][point.last()] = 1
            }
        }

        data class Point(val x: Int, val y: Int)
    }
}
