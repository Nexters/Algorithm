import sys
sys.setrecursionlimit(100000)

data = []
check_data = []
total_count, max_count = 0, 0
sleep_max = 0


def tracking(depth, index):
    global sleep_max
    if depth == max_count:
        checked = []
        result = 0
        # 선택된 인덱스 추출
        for i in range(index):
            if check_data[i]:
                checked.append(i)

        for i in range(1, len(checked)):
            if checked[i] - checked[i - 1] == 1:
                result += data[checked[i]]

        sleep_max = max(result, sleep_max)
        return

    for i in range(index, len(check_data)):
        check_data[i] = True
        tracking(depth + 1, i + 1)
        check_data[i] = False


if __name__ == "__main__":
    total_count, max_count = map(int, input().split(' '))
    check_data = [False] * total_count
    for _ in range(total_count):
        data.append(int(input()))

    tracking(0, 0)
    print(sleep_max)
