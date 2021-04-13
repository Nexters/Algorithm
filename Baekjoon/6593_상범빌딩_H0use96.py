from queue import Queue

if __name__ == "__main__":

    DIRECTION = [[1, 0, 0], [-1, 0, 0], [0, 1, 0], [0, -1, 0], [0, 0, 1], [0, 0, -1]]

    while True:
        floor, row, col = map(int, input().split(' '))
        if floor == 0 and row == 0 and col == 0:
            break
        end = None
        data = []
        visit = []
        locations = Queue()

        for _ in range(floor):
            data.append([list(input()) for _ in range(row)])
            input()

        visit = [[[0 for _ in range(col)] for _ in range(row)] for _ in range(floor)]

        for f in range(floor):
            for r in range(row):
                for c in range(col):
                    selected = data[f][r][c]
                    if selected == "S":
                        locations.put([0, [f, r, c]])
                        visit[f][r][c] = 1
                    elif selected == "E":
                        end = [f, r, c]

        result = -1
        while not locations.empty():
            current = locations.get()
            current_location = current[1]

            if result != -1:
                break

            for i in range(len(DIRECTION)):
                f, r, c = list(map(lambda l, d: int(l + d), current_location, DIRECTION[i]))

                if f < 0 or f >= floor:
                    continue
                if c < 0 or c >= col:
                    continue
                if r < 0 or r >= row:
                    continue

                if data[f][r][c] == "E":
                    result = current[0] + 1
                    locations.empty()
                    break

                if data[f][r][c] == "." and visit[f][r][c] == 0:
                    visit[f][r][c] = 1
                    locations.put([current[0] + 1, [f, r, c]])

        if result == -1:
            print("Trapped!")
        else:
            print(f"Escaped in {result} minute(s).")
