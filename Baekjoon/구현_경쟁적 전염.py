#silver 1
from _collections import deque

def BFS(ls):
    q = deque(ls)
    while q:
        Info = q.popleft()
        if Info[3] == S: #시간이 됐으면 끝내기
            break
        for dr,dc in D: #네 방향을 보며
            nr,nc = Info[1]+dr, Info[2]+dc
            if nr<0 or nr>=N or nc<0 or nc>=N: continue #범위를 벗어나면 제외
            if V[nr][nc]: continue #이미 바이러스가 있으면 제외
            V[nr][nc] = Info[0] #바이러스 증식
            q.append([V[nr][nc],nr,nc,Info[3]+1]) #시간+1해서 append 

D = [(0,1),(0,-1),(1,0),(-1,0)] #남북동서 4방향
N,K = map(int,input().split()) #2차원 배열 크기, 바이러스의 번호 범위 1~K
V = [list(map(int,input().split())) for _ in range(N)] #바이러스 정보 
S,X,Y = map(int,input().split()) #S초 뒤, V[X-1][Y-1]의 바이러스 번호 출력
chk = []
for i in range(N):
    for j in range(N):
        if V[i][j]: #만약 바이러스가 있으면
            chk.append([V[i][j],i,j,0]) #바이러스 번호, 좌표, 시간 append
chk.sort(key=lambda x:x[0]) #바이러스 번호 기준 오름차순 정렬
BFS(chk) 
print(V[X-1][Y-1])