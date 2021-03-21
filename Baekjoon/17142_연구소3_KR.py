import sys
input = sys.stdin.readline
from collections import deque

D =[(0,1),(0,-1),(1,0),(-1,0)]

def DFS(k,idx):
    if k == M: 
        BFS(ls) 
        return
    for v in range(idx,len(virus)):
        ls.append((virus[v]))
        DFS(k+1,v+1)
        ls.pop()

def BFS(ls):
    global Min,cnt
    check = [[0] * N for _ in range(N)];active = 0
    q = deque()
    for x,y in ls:
        q.append((x,y,0)) 
        check[x][y] = 1 
    while q:
        r,c,t = q.popleft() 
        if cnt == 0: 
            Min = 0 
            return
        if t >= Min: 
            break 
        for dr,dc in D: 
            nr,nc = r + dr, c + dc
            if 0 <= nr < N and 0 <= nc < N and lab[nr][nc]!=1 and not check[nr][nc]: 
                if not lab[nr][nc]:
                    active += 1
                    check[nr][nc] = 1 
                    q.append((nr, nc, t + 1))
                    if active == cnt: 
                        if t + 1 < Min: 
                            Min = t + 1
                        return
                elif lab[nr][nc] == 2: 
                    check[nr][nc] = 1 
                    q.append((nr, nc, t + 1))

N,M = map(int,input().rstrip().split()) 
lab = [list(map(int,input().rstrip().split())) for _ in range(N)] 
Min = 2500;virus, ls, cnt= [], [], 0 
for i in range(N):
    for j in range(N):
        if lab[i][j] == 2:
            virus.append((i,j)) 
        elif not lab[i][j]: 
            cnt += 1 
DFS(0,0) 
if Min == 2500: 
    print(-1) 
else: 
    print(Min) 
