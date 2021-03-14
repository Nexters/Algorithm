import sys
input = sys.stdin.readline
from _collections import deque

def BFS(start,arr):
    global N,b
    person = start
    q = deque(arr)
    while q:
        chon,cnt = q.popleft()
        if chon == b:
            return cnt
        for j in range(1,N+1):
            if Fam[chon][j] and not Chk[chon][j] and not Chk[j][chon]:
                Chk[chon][j] = Chk[j][chon] = 1
                q.append([j,cnt+1])
    return -1

N = int(input())
Fam = [[0]*(N+1) for _ in range(N+1)]
Chk = [[0]*(N+1) for _ in range(N+1)]
a,b = map(int,input().split())
m = int(input())
for _ in range(m):
    x,y  = map(int,input().split())
    Fam[x][y] = Fam[y][x] = 1

cal = []
for i in range(1,N+1):
    if Fam[a][i]:
        cal.append([i,1])
        Chk[a][i] = Chk[i][a] = 1
print(BFS(a,cal))
