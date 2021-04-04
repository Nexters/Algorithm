import itertools

if __name__ == "__main__":
    data = []
    sleep_max = 0
    total_count, max_count = map(int, input().split(' '))
    check_data = [False] * total_count
    for _ in range(total_count):
        data.append(int(input()))

    index_arr = [i for i in range(total_count)]
    candidates = list(itertools.combinations(index_arr, max_count))

    for candidate in candidates:
        result = 0
        for i in range(max_count - 1, 0, -1):
            if candidate[i] - candidate[i - 1] == 1:
                result += data[candidate[i]]
        sleep_max = max(sleep_max, result)

    print(sleep_max)