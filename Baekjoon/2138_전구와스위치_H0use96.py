DIRECTION = [-1, 0, 1]


def check(size, start, end, count):
    for i in range(1, size):
        if start[i - 1] != end[i - 1]:
            count += 1
            if i != size-1:
                for w in range(3):
                    start[i + DIRECTION[w]] = not start[i + DIRECTION[w]]
            else:
                for w in range(2):
                    start[i + DIRECTION[w]] = not start[i + DIRECTION[w]]

    if start[size-1] != end[size-1]:
        return -1
    else:
        return count

if __name__ == "__main__":
    size = int(input())
    start = list(map(lambda x: True if x == '1' else False, input()))
    end = list(map(lambda x: True if x == '1' else False, input()))
    start_from_zero = list.copy(start)

    after_zero = check(size, start, end, 0)

    count = 1
    start_from_zero[0] = not start_from_zero[0]
    start_from_zero[1] = not start_from_zero[1]

    from_zero = check(size, start_from_zero, end, count)

    result = list(filter(lambda x: x > -1, [from_zero, after_zero]))

    print(min(result) if len(result) != 0 else -1)
