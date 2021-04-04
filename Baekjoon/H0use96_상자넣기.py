if __name__ == "__main__":
    size = int(input())
    data = list(map(int, input().split(" ")))

    box_counts = [0] * size
    for cur_index in range(size-1, -1, -1):
        max_count = box_counts[cur_index]
        for i in range(cur_index, size):
            if data[i] > data[cur_index]:
                max_count = max(max_count, box_counts[i])
        box_counts[cur_index] = max_count + 1

    print(max(box_counts))