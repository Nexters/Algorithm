import itertools
import copy

NUMBERS = [10, 6, 14, 9, 11, 13, 12, 8, 16, 15]


def getNumber(startIdx, data):
    count = 0
    for curLine in data:
        subData = curLine[startIdx:startIdx + 6]
        count += subData.count("1")
    return NUMBERS.index(count)


def printNumber(startIdx, data, result):
    for i in range(0, 7):
        frag = str(data[i][startIdx:startIdx + 6])
        result[i] += frag


if __name__ == '__main__':
    data = []
    for i in range(0, 7):
        data.append(input())

    lineLength = len(data[0])

    element = []
    for i in range(0, lineLength, 6):
        element.append(getNumber(i, data))

    origin = copy.deepcopy(element)
    element.sort()
    pList = list(itertools.permutations(element))
    nextIdx = pList.index(tuple(origin)) + 1

    if nextIdx == len(pList): print("The End"); exit();

    nextElement = list(pList[nextIdx])

    result = ["" for i in range(7)]

    for i in nextElement:
        startIdx = origin.index(i) * 6
        printNumber(startIdx, data, result)

    for l in result:
        print(l)
