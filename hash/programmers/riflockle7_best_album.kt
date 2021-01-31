/**
 * 2021.01.31 06:00
 * https://programmers.co.kr/learn/courses/30/lessons/42576
 */
class riflockle7_best_album {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            solution(arrayOf("classic", "pop", "classic", "classic", "pop", "pop", "rock", "rock", "classic", "rock"), intArrayOf(500, 600, 150, 800, 2500, 2500, 2500, 2600, 10000, 2500))
            println("---------------------------")
            solution(arrayOf("pop", "pop", "pop", "rap", "rap"), intArrayOf(45, 50, 40, 60, 70))
            println("---------------------------")
            solution(arrayOf("classic", "classic", "classic", "classic", "pop"), intArrayOf(500, 150, 800, 800, 2500))
            println("---------------------------")
        }

        fun solution(genres: Array<String>, plays: IntArray): IntArray {
            // genre 의 index 숫자별로 genre 별로 index 를 groupBy 한다.
            // 현재 데이터 생김새 Pair<String, List<Int>>
            // "(classic, [0, 2, 3, 8])"
            // "(pop, [1, 4, 5])"
            // "(rock, [6, 7, 9])"
            return genres.indices.groupBy { genreIndex -> genres[genreIndex] }.toList()
                    // groupBy 로 모아진 index 를 기반으로 plays 데이터와 매치시켜 가장 많이 재생된 장르 순대로 정렬한다.
                    // 현재 데이터 생김새 Pair<String, List<Int>>
                    // "(classic, [0, 2, 3, 8])"
                    // "(rock, [6, 7, 9])"
                    // "(pop, [1, 4, 5])"
                    .sortedByDescending { genreInfo ->
                        genreInfo.second.sumBy { playIndex -> plays[playIndex] }
                    }

                    // 각 장르내 음악 id 목록을 내림차순으로 정렬하고 (음악 id 를 plays 데이터와 각각 매칭시켜 대소 비교 진행)
                    // 가장 상위의 2개 음악 id 를 가져온다.
                    // Pair<String, List<Int>> -> 데이터가 2개 있는 list 2개 ([8,3][7,6])
                    .map { it.second.sortedByDescending { plays[it] }.take(2) }
                    // Collection 여러개를 하나의 List 형태로 합친다.

                    .flatten()
                    // 정답 리턴
                    .toIntArray()
        }

        fun solutionWhyError(genres: Array<String>, plays: IntArray): IntArray {
            val answers = mutableListOf<Int>()

            // 'classic' -> (장르 총 재생 갯수, 장르에 해당하는 id 와 재생횟수 묶음 목록)
            val table = mutableMapOf<String, GenreInfo>()

            // 초기화
            genres.forEachIndexed { index, genre ->
                table[genre]?.let {
                    table[genre] = GenreInfo(
                            it.playedNum + plays[index],
                            it.list.apply { add(Pair(index, plays[index])) }
                    )
                } ?: kotlin.run {
                    table[genre] = GenreInfo(
                            plays[index],
                            mutableListOf(Pair(index, plays[index]))
                    )
                }
            }

            table.toList().sortedByDescending { (_, value) -> value.playedNum }.take(2)
                    .forEach { genre ->
                        val musics = genre.second.list.sortedWith(compareBy { it.first })

                        musics.sortedByDescending { (_, value) -> value }.take(2).forEach { music ->
                            answers.add(music.first)
                        }
                    }

            return answers.toIntArray()
        }

        data class GenreInfo(
                val playedNum: Int,
                val list: MutableList<Pair<Int, Int>>
        )
    }
}