"""
단지번호붙이기
- q) https://www.acmicpc.net/problem/2667
- a) https://www.acmicpc.net/source/26349758

- dfs / bfs
- test
    $ cat input.txt | python leoo_2667.py
"""

from collections import deque


def is_not_out(y, x):
    return all(0 <= i < N for i in (y, x))


def bfs(y, x):
    cnt = 1
    board[y][x] = 0
    queue = deque([(y, x)])

    while queue:
        cy, cx = queue.popleft()
        for dy, dx in DIR:
            ny, nx = cy + dy, cx + dx
            if is_not_out(ny, nx) and board[ny][nx]:
                cnt += 1
                board[ny][nx] = 0
                queue.append((ny, nx))
    return cnt


N = int(input())
DIR = ((0, 1), (0, -1), (1, 0), (-1, 0))
board = [[int(x) for x in input()] for _ in range(N)]
results = []
for y in range(N):
    for x in range(N):
        if board[y][x]:
            results.append(bfs(y, x))

print(len(results))
print('\n'.join(map(str, sorted(results))))
