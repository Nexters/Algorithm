import sys
input = sys.stdin.readline
from _collections import deque

def BFS(row,col):
    Chk[row][col] = 1
    cnt = 1
    q = deque([[row,col]])
    while q:
        r,c = q.popleft()
        for dr,dc in D:
            nr,nc = r+dr,c+dc
            if 1>nr or 1>nc or nr>N or nc>M: continue
            if Chk[nr][nc] or not G[nr][nc]: continue
            Chk[nr][nc] = 1
            q.append([nr,nc])
            cnt += 1
    return cnt
    
D = [(0,1),(0,-1),(1,0),(-1,0)]
N,M,K = map(int,input().split())
G = [[0]*(M+1) for _ in range(N+1)]
Chk = [[0]*(M+1) for _ in range(N+1)]
size = 1
for _ in range(K):
    R,C = map(int,input().split())
    G[R][C] = 1
for i in range(1,N+1):
    for j in range(1,M+1):
        if G[i][j] and not Chk[i][j]:
            size = max(size,BFS(i,j))
print(size)