package programmers

import java.util.*

/**
 * 2021.01.30 03:07
 * @see https://programmers.co.kr/learn/courses/30/lessons/42583
 */
class riflockle7_passing_bridge_truck {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(solution(2, 10, intArrayOf(7, 4, 5, 6)))
            println("---------------------------")
            println(solution(100, 100, intArrayOf(10)))
            println("---------------------------")
            println(solution(100, 100, intArrayOf(10, 10, 10, 10, 10, 10, 10, 10, 10, 10)))
            println("---------------------------")
        }

        /** 처음 bridge 의 0 은 맨 마지막 트럭이 나가는 데 걸리는 시간 소모를 나타냄 */
        fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
            val trucks = LinkedList(truck_weights.toList())
            val bridges: Queue<Int> = LinkedList(List(bridge_length) { 0 })    // bridge 만큼 트럭

            var answer = 0

            while (bridges.isNotEmpty()) {
                bridges.poll()

                if (trucks.isNotEmpty()) {
                    if (bridges.sum() + trucks.peek() <= weight) {
                        bridges.add(trucks.poll())
                    } else bridges.add(0)
                }

                answer++
            }

            return answer
        }

        /** 위의 식을 변형하여 forEach 로 바꾸고, 의미 없는 0을 넣어주는 대신 다리 길이를 더해주는 방법으로 코드를 변경.. 했으나 실패 */
        fun solutionButFail(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
            val bridgeTrucks: Queue<Int> = LinkedList()

            var currentWeight = 0
            var duplicated = 0
            var answer = 0

            truck_weights.forEach { newTruckWeight ->
                // 다리의 트럭이 나가야 하는 시나리오 계산
                // (현재 다리에 트럭이 꽉찼거나, 하중이 새로운 트럭 유입을 버티지 못할 경우)
                while ((bridgeTrucks.size >= bridge_length) || (weight - (currentWeight + newTruckWeight) < 0)) {
                    val exitTruckWeight = bridgeTrucks.poll()
                    currentWeight -= exitTruckWeight

                    answer += (bridge_length - duplicated)
                    duplicated--
                }

                // 다리에 트럭이 들어오는 시나리오 계산
                // (들어올 수 있는 상황인지 확인하고 트럭을 넣어줌)
                if (bridgeTrucks.size < bridge_length && currentWeight + newTruckWeight <= weight) {
                    currentWeight += newTruckWeight
                    bridgeTrucks.offer(newTruckWeight)
                    duplicated++
                    answer++
                }
            }

            answer += bridge_length

            return answer
        }
    }
}