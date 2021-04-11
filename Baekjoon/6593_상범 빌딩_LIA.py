from collections import deque

direct = [(0, 0, -1), (0, 0, 1), (0, 1, 0), (0, -1, 0), (-1, 0, 0), (1, 0, 0)]


def bfs(x, y, z):
    q = deque()
    q.append([x, y, z])
    check[x][y][z] += 1

    while q:
        cx, cy, cz = q.popleft()

        for i in range(6):
            nx, ny, nz = cx + direct[i][0], cy + direct[i][1], cz + direct[i][2]

            if nx < 0 or nx >= L or ny < 0 or ny >= R or nz < 0 or nz >= C or building[nx][ny][nz] == '#' or check[nx][
                ny][nz] > 0:
                continue
            if building[nx][ny][nz] == 'E':
                print("Escaped in", check[cx][cy][cz] + 1, "minute(s).")
                return

            check[nx][ny][nz] = check[cx][cy][cz] + 1
            q.append([nx, ny, nz])
    print("Trapped!")


while True:
    L, R, C = map(int, input().split())

    if L == R == C == 0:
        break

    building = [[[] * C for _ in range(R)] for _ in range(L)]
    check = [[[-1] * C for _ in range(R)] for _ in range(L)]

    for i in range(L):
        building[i] = [list(input()) for _ in range(R)]
        input()

    for i in range(L):
        for j in range(R):
            for z in range(C):
                if building[i][j][z] == 'S':
                    bfs(i, j, z)
