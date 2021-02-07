/**
 * 위장
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42578?language=kotlin
 */
class riflockle7_disguise {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(solution(arrayOf(
                    arrayOf("crow_mask", "face"),
                    arrayOf("blue_sunglasses", "eyewear"),
                    arrayOf("blue_sunglasses", "sock"),
                    arrayOf("smoky_makeup", "headgear")
            )))
            answer = 0

            println(solution(arrayOf(
                    arrayOf("crow_mask", "face"),
                    arrayOf("blue_sunglasses", "eyewear"),
                    arrayOf("smoky_makeup", "headgear")
            )))
            answer = 0

            println(solution(arrayOf(
                    arrayOf("yellow_hat", "headgear"),
                    arrayOf("blue_sunglasses", "eyewear"),
                    arrayOf("green_turban", "headgear")
            )))
            answer = 0

            println(solution(arrayOf(
                    arrayOf("crow_mask", "face"),
                    arrayOf("blue_sunglasses", "face"),
                    arrayOf("smoky_makeup", "face")
            )))
            answer = 0
        }

        var answer = 0

        fun solution(clothes: Array<Array<String>>): Int {
            val table = classify(clothes)

            // 각 옷 type 별 개수를 알려주는 map
            val typeNums = mutableListOf<Int>()

            // 1개만 입는 경우
            table.keys.forEach { key ->
                typeNums.add(table[key]?.size ?: 0)
                answer += table[key]?.size ?: 0
            }

            // 2, 3..... 개만 입는 경우
            typeNums.forEachIndexed { i, typeNum ->
                // [1,2], [1,3], [1,4]... 에 대한 case 를 계산하기 위해 반복문을 돌린다.
                for (index in i until typeNums.size)
                    mutlipleCase(typeNum, index + 1, typeNums)
            }

            return answer
        }

        /**
         * 2, 3..... 개 에 대해 case 를 계산하는 재귀 함수
         *
         * @param newAnswer 이전 case 에 대한 경우의 수
         * @param startIndex 새로운 경우의 수
         *                   (ex. 이전 case 가 [1(face), 2(eyewear)] 경우의 수를 체크했다면
         *                        이번 case 는 [1(face), 2(eyewear), [startIndex](type[startIndex])] 의 경우를 계산함
         * @param typeNums 각 type 별 옷 개수 목록
         */
        fun mutlipleCase(newAnswer: Int, startIndex: Int, typeNums: MutableList<Int>) {
            // 만약 [startIndex] 값이 범위를 벗어나는 값이라면 로직을 진행하지 않는다.
            if (startIndex >= typeNums.size) return

            val newCaseNum = newAnswer * typeNums[startIndex]
            answer += newCaseNum

            // [1(face), 2(eyewear), [startIndex + 1](type[startIndex + 1])] 의 경우도 체크하기 위해 반복문을 돌린다.
            for (newStartIndex in startIndex + 1 until typeNums.size) {
                mutlipleCase(newCaseNum, newStartIndex, typeNums)
            }
        }

        /**
         * 코드 내에서 알맞게 적용시킬 수 있도록 데이터 분류 및 정제 작업을 진행한다.
         *
         * @return [MutableMap] 형태로 정제된 값
         *      [
         *          "face" -> ["a", "b"],
         *          "eyewear" -> ["c"],
         *          "sock" -> ["d", "e", "f"],
         *          ....
         *      ]
         * */
        fun classify(clothes: Array<Array<String>>): MutableMap<String, List<String>> {
            val table: MutableMap<String, List<String>> = mutableMapOf()

            clothes.forEach { cloth ->
                val clothType = cloth[1]
                val clothName = cloth[0]
                // 이미 해당 type 에 속하는 옷이 있을 경우 원래의 값에 새로운 값을 추가시켜준다.
                table[clothType]?.let {
                    val newClothes = mutableListOf<String>().apply { addAll(it) }
                    table.put(clothType, newClothes.apply { add(clothName) })
                } ?:
                // 빈 값이라면 새로운 list 를 만들고 그 값을 추가시킨 것을 새로 넣어준다.
                kotlin.run {
                    table.put(clothType, mutableListOf<String>().apply { add(clothName) })
                }
            }

            return table
        }
    }
}