import sys
input = sys.stdin.readline
sys.setrecursionlimit(100000)

#ver1
#아이디어: 배열 순회하며 1이 나오면 house 카운트 시작, 단지 cnt += 1. 단지가 있는것으로 확인되면 바로 0으로 만들어주기
D = [(0,1),(0,-1),(1,0),(-1,0)] #동서남북

def dfs(r,c):
    global house
    house += 1 #단지 내 집 수 +1
    Map[r][c] = '0' #0으로 만들어주기(중요)
    for dr,dc in D: #네 방향을 보며
        nr, nc = r + dr, c + dc
        if 0<=nr<N and 0<=nc<N and Map[nr][nc]=='1': #범위 내에 집이 있으면
            dfs(nr,nc)

N = int(input())
Map = [list(map(str,input())) for _ in range(N)] #단지 지도 입력
cnt = 0;ans = [] #총 단지 수, 단지내 집의 수를 담을 배열
for i in range(N):
    for j in range(N):
        if Map[i][j]=='1': #만약 집이 있으면
            house = 0 #단지내 집 수 카운팅 시작
            cnt += 1 #총 단지 수 +1
            dfs(i,j) #dfs
            ans.append(house) #한 단지의 집을 다 세고나면 합산된 집의 수(house)를 ans에 append

ans.sort() #오름차순 정렬이므로 sort()
print(cnt)
for i in ans:
    print(i)

#ver2
#아이디어: N*N의 체크배열을 하나 더 만들고, 배열을 순회하며 체크표시
D = [(0,1),(0,-1),(1,0),(-1,0)]

def dfs(r,c, num):
    chk[r][c]=num #num으로 만들어줌
    for dr,dc in D: #동서남북을 보며
        nr, nc = r + dr, c + dc
        if 0<=nr<N and 0<=nc<N and Map[nr][nc] and not chk[nr][nc]: #배열의 범위 내에 집이 있고 아직 체크가 안되었으면
            dfs(nr,nc,num) #dfs

N = int(input())
Map = [list(map(int,input())) for _ in range(N)] #단지 지도 입력
chk = [[0]*N for _ in range(N)] #같은 크기의 chk 배열 생성
cnt = 0;ans=[] #총 단지 수, 단지내 집의 수를 담을 배열
for i in range(N):
    for j in range(N):
        if Map[i][j] and not chk[i][j]: #집이 있고 아직 체크가 안되어있으면
            cnt += 1 #단지 수 +1
            dfs(i,j,cnt) #dfs 

print(cnt)
res = sum(chk,[]) #2차원배열을 1차원으로 만드는 방법
for i in range(1,cnt+1):
    ans.append(res.count(i))
ans.sort()
for n in ans:
    print(n)