package programmers

import java.util.*
import kotlin.math.ceil

/**
 * 2021.01.22
 * @see https://programmers.co.kr/learn/courses/30/lessons/42586?language=kotlin
 * queue 사용
 */

class FeatureDevelop {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val answer = solution(intArrayOf(93, 30, 55), intArrayOf(1, 30, 5))
            val answer2 = solution(intArrayOf(95, 90, 99, 99, 80, 99), intArrayOf(1, 1, 1, 1, 1, 1))
            print(answer2)
        }

        private fun solution(progresses: IntArray, speeds: IntArray): IntArray {
            val answer = mutableListOf<Int>()
            val queue: Queue<Int> = LinkedList()

            (progresses zip speeds).forEach {
                val dayCost = ceil((100 - it.first) / it.second.toFloat()).toInt()

                // 만약 queue 최상단 값보다 값이 크고 비어있지 않다면
                // 지금까지의 queue 크기를 answer 에 넣은뒤 값 비움
                if(queue.isNotEmpty() && queue.peek() < dayCost) {
                    answer.add(queue.size)
                    queue.clear()
                }

                // 값 추가
                queue.offer(dayCost)
            }

            if (queue.size != 0) answer.add(queue.size)

            return answer.toIntArray()
        }
    }
}