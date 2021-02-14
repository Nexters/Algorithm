import sys
input = sys.stdin.readline
sys.setrecursionlimit(100000)

D = [(0,1),(0,-1),(1,0),(-1,0)]

def dfs(r,c):
    global house
    house += 1
    complex[r][c] = '0'
    for dr,dc in D:
        nr, nc = r + dr, c + dc
        if 0<=nr<N and 0<=nc<N and complex[nr][nc]=='1':
            dfs(nr,nc)

N = int(input())
complex = [list(map(str,input())) for _ in range(N)]
cnt = 0;ans = []
for i in range(N):
    for j in range(N):
        if complex[i][j]=='1':
            house = 0
            cnt += 1
            dfs(i,j)
            ans.append(house)

ans.sort()
print(cnt)
for i in ans:
    print(i)

# def DFS(r,c,num):
#     chk[r][c] = num
#     for dr,dc in D:
#         nr = r+dr
#         nc = c+dc
#         if Map[r+dr][r+dc] and not chk[r+dr][r+dc]:
#             DFS(dr,dc,num)
#     return

# D = [(0,1),(0,-1),(1,0),(-1,0)] #동서남북
# N = int(input())
# Map = [list(map(int,input())) for _ in range(N)] #단지 지도 입력
# chk = [[0]*N for _ in range(N)] #체크 지도 입력
# cnt = 0

# for i in range(N):
#     for j in range(N):
#         if Map[i][j] and not chk[i][j]: #아파트가 있고 아직 체크되지 않은 곳이면
#             cnt += 1
#             DFS(i,j,cnt)

# print(cnt)