import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * 6593_상범 빌딩_riflockle7
 *
 * 삽질 내역
 * 1. 로깅 내용 활성화로 인한 출력 오류
 * 2. 기존에는 이전의 방향처리 값을 받아 해당 값을 없애고 forEach 로 인한 메모리 초과 (visited 파라미터 형식으로 넘기도록 진행)
 * 3. 33% 에서 틀렸습니다가 나옴 (Trapped 오타 수정)
 * 4. bfs 문제를 dfs 로 풀고 있었음
 * 5. 33% 에서 틀렸습니다가 나옴 (Trapped 오타 수정 재반복..)
 *
 * https://www.acmicpc.net/problem/6593
 */
class `6593_상범 빌딩_riflockle7` {
    companion object {
        var answer = Int.MAX_VALUE
        var Z = -1
        var Y = -1
        var X = -1
        var start: Point? = null
        var end: Point? = null
        var table: MutableList<MutableList<MutableList<Boolean>>> = mutableListOf()

        val direction = listOf(
                Point(1, 0, 0),
                Point(-1, 0, 0),
                Point(0, 1, 0),
                Point(0, -1, 0),
                Point(0, 0, 1),
                Point(0, 0, -1)
        )

        @JvmStatic
        fun main(args: Array<String>) {
            val br = BufferedReader(InputStreamReader(System.`in`))
            while (true) {
                if (!input(br))
                    break

                answer = bfs(start!!)

                if (answer != Int.MAX_VALUE)
                    println("Escaped in $answer minute(s).")
                else
                    println("Trapped!")
                clear(br)
            }
        }

        fun bfs(start: Point): Int {
            val queue: Queue<Point> = LinkedList()
            var time = 0

            queue.add(start)
            table[start.z][start.y][start.x] = false

            while (!queue.isNullOrEmpty()) {
                var size = queue.size

                while (size-- > 0) {
                    val item = queue.poll()

                    direction.forEach {
                        val newStart = item.copy(x = item.x + it.x, y = item.y + it.y, z = item.z + it.z)

                        if (newStart.isValid()) {
                            if (newStart.isArrived())
                                return time + 1
                            else if (!newStart.isVisited()) {
                                table[newStart.z][newStart.y][newStart.x] = true
                                queue.add(newStart)
                            }
                        }
                    }
                }

                time++
            }

            return answer
        }

        fun clear(br: BufferedReader) {
            answer = Int.MAX_VALUE
            X = -1
            Y = -1
            Z = -1
            start = null
            end = null
            table = mutableListOf()
            br.readLine()
        }

        fun Point.isVisited() = table[z][y][x]

        fun Point.isValid(): Boolean {
            return x in 0 until X && y in 0 until Y && z in 0 until Z
        }

        fun Point.isArrived(): Boolean = (this.x == end?.x && this.y == end?.y && this.z == end?.z)

        /** 입력한 데이터 넣기 */
        fun input(br: BufferedReader): Boolean {
            val cmd = br.readLine()
            if (cmd == "0 0 0")
                return false
            else {
                val (N, R, C) = cmd.split(" ").map { it.toInt() }
                Z = N
                Y = R
                X = C
            }

            for (zIndex in 0 until Z) {
                val floors = mutableListOf<MutableList<Boolean>>()

                for (yIndex in 0 until Y) {
                    val line = br.readLine().split("").filter { it != "" }.mapIndexed { xIndex, value ->
                        if (value == "S")
                            start = Point(xIndex, yIndex, zIndex)
                        else if (value == "E")
                            end = Point(xIndex, yIndex, zIndex)

                        value == "#"
                    }.toMutableList()

                    floors.add(line)
                }

                if (zIndex != Z - 1)
                    br.readLine()
                table.add(floors)
            }

            return true
        }

        /** x, y, z 좌표 */
        data class Point(val x: Int, val y: Int, val z: Int)
    }
}
