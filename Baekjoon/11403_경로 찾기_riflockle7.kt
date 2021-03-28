package _2021._3_2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

/**
 * 경로 찾기 - 백준
 *
 * https://www.acmicpc.net/problem/11403
 */
class `11403_경로 찾기_riflockle7` {
    companion object {
        var vertexNumber: Int = -1
        val table = mutableListOf<List<Int>>()

        @JvmStatic
        fun main(args: Array<String>) {
            input()
            for (row in 0 until vertexNumber) {
                val visited = MutableList(vertexNumber) { false }
                dfs(row, visited)
                printLine(visited)
            }
        }

        fun dfs(top: Int, visited: MutableList<Boolean>) {
            for (i in 0 until vertexNumber) {
                if (table[top][i] == 1 && !visited[i]) {
                    visited[i] = true
                    dfs(i, visited)
                }
            }
        }

        fun printLine(visited: MutableList<Boolean>) {
            val stringBuilder = StringBuilder("")
            for (i in 0 until vertexNumber) {
                if (visited[i]) stringBuilder.append("1 ") else stringBuilder.append("0 ")
            }
            println(stringBuilder.toString())
        }

        /** 입력한 데이터 넣기 */
        fun input() {
            val br = BufferedReader(InputStreamReader(System.`in`))

            vertexNumber = br.readLine().toInt()

            for (rowIndex in 0 until vertexNumber) {
                val rows = br.readLine().split(" ").map { it.toInt() }
                table.add(rows)
            }
        }
    }
}
