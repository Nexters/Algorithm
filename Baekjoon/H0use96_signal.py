# 0: 0, 1, 6, 8
# 1: 2, 5, 9
# 2: 3, 4
# 3: 7

TESTCASE = {
    "size": 40,
    "data": "###..#..#.#..#..###..#..#.#..#..###..#.."
}

size = int(TESTCASE["size"])
data = str(TESTCASE["data"])
line = int(size/5)


def findBlank(lineIdx):
    blankIdx = []
    for i in range(0, 5):
        if data[lineIdx + (i*line)] == ".":
            blankIdx.append(i)
    return blankIdx

def main():
    result = ""
    curLine = 0
    while curLine < size:
        blankList = findBlank(curLine)
        blankCount = len(blankList)

        if blankCount == 0:
            curLine += 1
            blankList = findBlank(curLine)
            blankCount = len(blankList)

            if blankCount == 3:
                result += "0"
                curLine += 3
                continue

            if blankCount == 5:
                result += "1"
                curLine += 1
                continue

            if data[curLine + blankList[0] * line + 1] == ".":
                result += "6"
                curLine += 3
                continue

            else:
                result += "8"
                curLine += 3
                continue

        if blankCount == 1:
            if blankList[0] == 1:
                result += "2"
                curLine += 3
                continue

            blankList = findBlank(curLine + 2)
            blankCount = len(blankList)

            if blankCount == 0:
                result += "9"
                curLine += 3
                continue

            else:
                result += "5"
                curLine += 3
                continue

        if blankCount == 2:
            if blankList[1] - blankCount[0] == 1:
                result += "4"
                curLine += 3
                continue
            else:
                result += "3"
                curLine += 3
                continue
        else:
            result += "7"
            curLine += 3
            continue
    return result

print(main())