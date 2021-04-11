if __name__ == "__main__":
    size = int(input())

    first = 3
    second = 7
    result = 0

    if size == 1:
        print(first)
    elif size == 2:
        print(second)
    else:
        for i in range(2, size):
            result = 2 * second + first
            first = second
            second = result
        print(result % 9901)
