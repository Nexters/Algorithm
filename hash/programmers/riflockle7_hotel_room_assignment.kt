/**
 * 2021.01.31 05:00
 * @see https://programmers.co.kr/learn/courses/30/lessons/64063
 */
class riflockle7_hotel_room_assignment {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            solution(10, longArrayOf(1, 3, 4, 1, 3, 1))
            println("---------------------------")
        }

        fun solution(k: Long, room_number: LongArray): LongArray {
            val answer = mutableListOf<Long>()
            val targetMinimumMap = HashMap<Long, Long>()

            room_number.forEach {
                answer.add(findHotelFromK(targetMinimumMap, it))
            }
            return answer.toLongArray()
        }

        fun findHotelFromK(table: MutableMap<Long, Long>, requiredK: Long): Long {
            return table[requiredK]?.let {
                val updatedK = findHotelFromK(table, it)
                table[requiredK] = updatedK
                updatedK
            } ?: kotlin.run {
                table[requiredK] = requiredK + 1
                requiredK
            }
        }
    }
}