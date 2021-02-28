import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.properties.Delegates

// TODO
class riflockle7_complex_numbering {
    companion object {
        var count by Delegates.notNull<Int>()
        lateinit var table: Array<MutableList<Int>>
        var answer = 0

        @JvmStatic
        fun main(args: Array<String>) {
            input()

            for (i in 1..count)
                for (j in 1..count)
                    check(i, j)

            print(answer)
        }

        fun check(row: Int, column: Int, alreadyChecked: Boolean = false) {
            if (row < 1 || column < 1 || row > count || column > count) return

            // 만약 집이라면 +1 을 하고 해당 값을 0으로 바꾼 후, 동서남북 검사를 한다
            if (table[row][column] != 0) {
                if (!alreadyChecked) answer++
                table[row][column] = 0

                check(row + 1, column, true)
                check(row - 1, column, true)
                check(row, column + 1, true)
                check(row, column - 1, true)
            } else
                return
        }

        /** 입력한 데이터 넣기 */
        fun input() {
            val br = BufferedReader(InputStreamReader(System.`in`))
            count = br.readLine().toInt()

            table = Array(count + 1) { mutableListOf<Int>() }

            for (i in 1..count) {
                val chunk = br.readLine().split("\n").map {
                    it.split("").map {
                        if (it == "") -1
                        else it.toInt()
                    }
                }[0].toMutableList().apply { removeAt(lastIndex) }
                table[i].addAll(chunk)
            }

            print("")
        }
    }
}