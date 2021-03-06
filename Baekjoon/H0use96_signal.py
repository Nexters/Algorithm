NUMBERS = [
    ["0", "###", "#.#", "#.#", "#.#", "###"],
    ["2", "###", "..#", "###", "#..", "###"],
    ["3", "###", "..#", "###", "..#", "###"],
    ["4", "#.#", "#.#", "###", "..#", "..#"],
    ["5", "###", "#..", "###", "..#", "###"],
    ["6", "###", "#..", "###", "#.#", "###"],
    ["7", "###", "..#", "..#", "..#", "..#"],
    ["8", "###", "#.#", "###", "#.#", "###"],
    ["9", "###", "#.#", "###", "..#", "###"],
]


def compareNumber(startIdx, line, number):
    for i in range(0, 5):
        idx = startIdx + i * line
        curStr = data[idx:idx + 3]
        if curStr != number[i + 1]:
            return False
    return True


def isNumberOne(startIdx):
    return data[startIdx] == "#"


if __name__ == '__main__':

    size = int(input())
    data = input()
    totalLine = int(size / 5)

    curLine = 0
    result = ""
    while curLine < totalLine:
        for i in range(0, 10):
            if i == 9:
                if isNumberOne(curLine):
                    result += "1"
                    curLine += 2
                    break
                else:
                    curLine += 1
                    break

            if compareNumber(curLine, totalLine, NUMBERS[i]):
                result += NUMBERS[i][0]
                curLine += 4
                break
    print(result)
