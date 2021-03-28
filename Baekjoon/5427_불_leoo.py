"""
불: https://www.acmicpc.net/problem/5427
- G4
- simulation, bfs
- (주의)불이 이동할 좌표도 상근이는 가면 안된다.
"""
from collections import deque
def bfs(y,x):
    global fire
    visited = [[0 if v=='.' else 1 for v in row]for row in board]
    result = 0
    queue = deque([(y,x)])
    while queue:
        for _ in range(len(fire)):
            y,x = fire.popleft()
            for ny,nx in (y,x+1),(y,x-1),(y+1,x),(y-1,x):
                if not ((0<=ny<h) and (0<=nx<w)): continue
                if visited[ny][nx]: continue
                visited[ny][nx] = 1
                fire.append((ny,nx))

        for _ in range(len(queue)):
            pos = queue.popleft()
            if pos in goal: return result + 1
            y,x = pos
            for ny,nx in (y,x+1),(y,x-1),(y+1,x),(y-1,x):
                if not ((0<=ny<h) and (0<=nx<w)): continue
                if visited[ny][nx]: continue
                if [ny,nx] == start: continue
                visited[ny][nx] = 1
                queue.append((ny,nx))
        result += 1
    return -1

for _ in range(int(input())):
    w,h = map(int,input().split())
    board = [list(input()) for _ in range(h)]
    goal = []
    start = [-1,-1]
    fire = deque([])
    for y in range(h):
        for x in range(w):
            if board[y][x] == '#': continue
            if board[y][x] == '*': fire.append((y,x))
            elif board[y][x] == '@': start[0] = y; start[1] = x;board[y][x]='.'
            if y==0 or y== h-1 or x ==0 or x==w-1:goal.append((y,x))
    if goal == []: print("IMPOSSIBLE")
    else:
        result = bfs(*start)
        print(result) if result != -1 else print("IMPOSSIBLE")