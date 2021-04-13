import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.abs

/**
 * 치킨배달 - 백준
 * 15686_치킨배달_riflockle7
 *
 * https://www.acmicpc.net/problem/15686
 */
class `15686_치킨배달_riflockle7` {
    companion object {
        const val EMPTY = 0;
        const val HOUSE = 1;
        const val CHICKEN = 2;

        var minimumChicken = Integer.MIN_VALUE
        val chickens = mutableListOf<Point>()
        val houses = mutableListOf<Point>()

        var answer = Int.MAX_VALUE

        @JvmStatic
        fun main(args: Array<String>) {
            input()

            // 치킨집을 솎아낸다 (조합 5 C 1)
            val comb = Combination(chickens.size, minimumChicken)
            val chickenIndexes = chickens.mapIndexed { index, point -> index }.toIntArray()
            comb.combination(chickenIndexes, 0, 0, 0)
            val chickenHousesList = comb.result

            // 각 집마다 어느 치킨집이 더 가까운지 확인한 후 그 값을 가산한다
            chickenHousesList.forEach { chickenIndexHouses ->
                val chickenHouses = chickenIndexHouses.map { chickens[it] }
                var tempAnswer = 0

                houses.forEach { houses ->
                    val target = chickenHouses.minBy { getInstance(houses, it) }!!
                    tempAnswer += getInstance(houses, target)
                }

                answer = Math.min(answer, tempAnswer)
            }

            print(answer)
        }

        /** a Point 와 b Point 간의 거리를 구한다. */
        fun getInstance(a: Point, b: Point): Int {
            return abs(b.x - a.x) + abs(b.y - a.y)
        }

        /** 입력한 데이터 넣기 */
        fun input() {
            val br = BufferedReader(InputStreamReader(System.`in`))

            val (N, M) = br.readLine().split(" ").map { it.toInt() }
            minimumChicken = M

            for (y in 0 until N) {
                br.readLine().split(" ").map { it.toInt() }.forEachIndexed { x, value ->
                    when (value) {
                        HOUSE -> houses.add(Point(x, y))
                        CHICKEN -> chickens.add(Point(x, y))
                    }
                }
            }
        }

        /** x, y 좌표 */
        data class Point(
                val x: Int,
                val y: Int
        )

        /** 조합 구하기 시스템 */
        class Combination(val n: Int, val r: Int) {
            val now: IntArray = IntArray(r) { 0 }
            val result: MutableList<MutableList<Int>> = mutableListOf()

            fun combination(arr: IntArray, depth: Int, index: Int, target: Int) {
                if (depth == r) {
                    val temp = ArrayList<Int>()
                    for (i in now.indices) {
                        temp.add(arr[now[i]])
                    }
                    result.add(temp)
                    return
                }
                if (target == n) return
                now[index] = target
                combination(arr, depth + 1, index + 1, target + 1)
                combination(arr, depth, index, target + 1)
            }
        }
    }
}
