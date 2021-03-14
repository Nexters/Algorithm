import sys

trash_data = []
direction = [[1, 0], [-1, 0], [0, 1], [0, -1]]
max_count = 0

sys.setrecursionlimit(1000 * 1000)


def checkArea(count, point):
    for i in range(0, len(direction)):
        next_point = list(map(lambda x, y: x + y, point, direction[i]))
        next_point_index = trash_data.index(next_point) if next_point in trash_data else -1

        if next_point_index == -1:
            continue
        trash_data.pop(next_point_index)
        count = checkArea(count, next_point)

    return count + 1


if __name__ == '__main__':
    size_info = input()
    split_data = map(lambda x: int(x), size_info.split(" "))
    row, col, trash_count = split_data

    for i in range(0, trash_count):
        data_input = input()
        trash_data.append(list(map(lambda x: int(x), data_input.split(" "))))

    while len(trash_data):
        current_node = trash_data.pop()
        current_count = checkArea(0, current_node)
        max_count = max(max_count, current_count)

    print(max_count)
