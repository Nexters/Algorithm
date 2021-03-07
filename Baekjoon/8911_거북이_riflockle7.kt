import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * 거북이
 *
 * https://www.acmicpc.net/problem/8911
 */
class `8911_거북이_riflockle7` {
    companion object {
        // 시계방향 (R 받을 시 ++ / L 받을 시 --)
        val direction = listOf(
                Point(0, 1),     // 북
                Point(1, 0),     // 동
                Point(0, -1),    // 남
                Point(-1, 0)     // 서
        )

        // 현재 거북이가 향하고 있는 방향
        var currentDirect = 0

        // 현재 거북이의 위치
        var currentPosition = Point(0, 0)

        @JvmStatic
        fun main(args: Array<String>) {
            val commandsTable = input()

            commandsTable.forEach { commands ->
                val table = start(commands)
                println(calculate(table))

                // 거북이 원위치
                currentDirect = 0
                currentPosition = Point(0, 0)
            }
        }

        private fun calculate(minMaxTable: Pair<Point, Point>): Int {
            val width = minMaxTable.second.x - minMaxTable.first.x
            val height = minMaxTable.second.y - minMaxTable.first.y

            return width * height
        }

        fun start(commandsStr: String): Pair<Point, Point> {
            val min = Point(0, 0)
            val max = Point(0, 0)

            commandsStr.forEach {
                val command = it.toString()
                // (0, 1 이면 max 값 control / 2, 3 이면 min 값 control)
                val maxControl = currentDirect / 2 == 0
                // (1, 3 이면 x 값 control / 2, 4 이면 y 값 control)
                val xControl = currentDirect % 2 == 1

                when (command) {
                    "F" -> {
                        currentPosition.x = currentPosition.x + direction[currentDirect].x
                        currentPosition.y = currentPosition.y + direction[currentDirect].y
                        if (maxControl) {
                            if (xControl)
                                max.x = Math.max(max.x, currentPosition.x)
                            else
                                max.y = Math.max(max.y, currentPosition.y)
                        } else {
                            if (xControl)
                                min.x = Math.min(min.x, currentPosition.x)
                            else
                                min.y = Math.min(min.y, currentPosition.y)
                        }
                    }
                    "B" -> {
                        currentPosition.x = currentPosition.x + direction[(currentDirect + 2) % 4].x
                        currentPosition.y = currentPosition.y + direction[(currentDirect + 2) % 4].y
                        if (!maxControl) {
                            if (xControl)
                                max.x = Math.max(max.x, currentPosition.x)
                            else
                                max.y = Math.max(max.y, currentPosition.y)
                        } else {
                            if (xControl)
                                min.x = Math.min(min.x, currentPosition.x)
                            else
                                min.y = Math.min(min.y, currentPosition.y)
                        }
                    }
                    "L" -> {
                        currentDirect = if (currentDirect == 0) 4 else currentDirect
                        currentDirect = (currentDirect - 1) % 4
                    }
                    "R" -> {
                        currentDirect = (currentDirect + 1) % 4
                    }
                }
            }

            return Pair(min, max)
        }

        /** 입력한 데이터 넣기 */
        fun input(): List<String> {
            val commandsTable = mutableListOf<String>()

            val br = BufferedReader(InputStreamReader(System.`in`))
            val testNum = br.readLine().toInt()

            for (commands in 0 until testNum) {
                val input = br.readLine()

                commandsTable.add(input)
            }

            return commandsTable
        }
    }

    data class Point(var x: Int, var y: Int)
}