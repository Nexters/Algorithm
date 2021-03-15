package _2021._3_2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * 촌수계산 - 백준
 *
 * https://www.acmicpc.net/problem/2644
 * https://velog.io/@diddnjs02/%EC%BD%94%EB%94%A9%ED%85%8C%EC%8A%A4%ED%8A%B8%EB%B0%B1%EC%A4%80-%EC%B4%8C%EC%88%98-%EA%B3%84%EC%82%B0
 */
class `2644_촌수계산_riflockle7` {
    companion object {
        var familyNumber = 0
        var target = Pair(0, 0)
        var relationNum = 0
        var relations = mutableListOf<Pair<Int, Int>>()
        var visitTable = mutableListOf<MutableList<Boolean>>()
        var answer = -1


        @JvmStatic
        fun main(args: Array<String>) {
            input()

            bfs(target.first, 0)
            print(answer)
        }

        fun bfs(start: Int, count: Int) {
            // 이미 도착지에 온 경우 종료 처리
            if (start == target.second) {
                answer = count
                return
            }

            // 도착지에 아직 안 온 경우 아직 방문하지 못했던 곳 모두 찾기
            val queue: Queue<Pair<Int, Int>> = LinkedList()
            val filteredRelations = relations.filter { it.first == start || it.second == start && !isVisit(it) }
            queue.addAll(filteredRelations)

            // 방문하지 못했던 곳 목록을 기반으로 다음 장소로 이동하기
            queue.forEach {
                visit(it)
                val newCount = count + 1

                if (it.first != start) {
                    bfs(it.first, newCount)
                } else if (it.second != start) {
                    bfs(it.second, newCount)
                }
            }
        }

        // 방문 처리 하기
        fun visit(relation: Pair<Int, Int>) {
            visitTable[relation.first][relation.second] = true
            visitTable[relation.second][relation.first] = true
        }

        // 방문했는지 확인
        fun isVisit(relation: Pair<Int, Int>): Boolean {
            return visitTable[relation.first][relation.second] || visitTable[relation.second][relation.first]
        }

        /** 입력한 데이터 넣기 */
        fun input() {
            val br = BufferedReader(InputStreamReader(System.`in`))

            // 가족 전체 수 입력
            familyNumber = br.readLine().toInt()

            // 구해야 할 시작점, 도착점 입력
            br.readLine().split(" ")
                    .map { it.toInt() }
                    .let { target = Pair(it.first(), it.last()) }

            // 관계 수 및 관계 명세 입력
            relationNum = br.readLine().toInt()
            for (index in 0 until relationNum) {
                val relation = br.readLine().split(" ").map { it.toInt() }
                relations.add(Pair(relation.first(), relation.last()))
            }

            // 방문 기록 table 구축 (초기값은 전부 true 로 처리)
            for (index in 0..familyNumber) {
                visitTable.add(MutableList(familyNumber + 1) { true })
            }
            // 이후 입력한 relation 정보에 따라 미방문 처리
            relations.forEach {relation ->
                visitTable[relation.first][relation.second] = false
                visitTable[relation.second][relation.first] = false
            }
        }
    }
}
