import sys

sys.setrecursionlimit(10 ** 6)

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def solution():
    n, m, k = map(int, input().split())

    board = [[0 for _ in range(m)] for _ in range(n)]
    visited = [[0 for _ in range(m)] for _ in range(n)]

    for i in range(k):
        x, y = map(int, input().split())
        board[x - 1][y - 1] = 1

    answer = 0
    for i in range(n):
        for j in range(m):
            if board[i][j] == 0: continue

            count = dfs(i, j, visited, board)
            answer = max(count, answer)

    print(answer)


def dfs(x, y, visited, board):
    visited[x][y] = 1
    count = 1

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if 0 <= nx < len(visited) and 0 <= ny < len(visited[0]):
            if visited[nx][ny] == 0 and board[nx][ny] > 0:
                visited[nx][ny] = 1
                count += dfs(nx, ny, visited, board)

    return count


solution()
