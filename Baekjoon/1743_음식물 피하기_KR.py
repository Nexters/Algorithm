import sys
input = sys.stdin.readline
from _collections import deque

def BFS(row,col):
    Chk[row][col] = 1 #체크표시
    cnt = 1 #카운트 시작
    q = deque([[row,col]]) 
    while q:
        r,c = q.popleft() #너비 탐색
        for dr,dc in D: #네방향을 보며
            nr,nc = r+dr,c+dc
            if 1>nr or 1>nc or nr>N or nc>M: continue #범위 바깥이면 제외
            if Chk[nr][nc] or not G[nr][nc]: continue #체크했거나 음식물이 없으면 제외
            Chk[nr][nc] = 1 #체크 표시
            q.append([nr,nc])
            cnt += 1 #카운트 +1
    return cnt
    
D = [(0,1),(0,-1),(1,0),(-1,0)] #네 방향
N,M,K = map(int,input().split()) #행,열,음식물 쓰레기 개수
G = [[0]*(M+1) for _ in range(N+1)] #음식물 떨어진 좌표 매트릭스
Chk = [[0]*(M+1) for _ in range(N+1)] #체크 매트릭스
size = 1 #음식물 쓰레기 최소 크기(K>=1)
for _ in range(K):
    R,C = map(int,input().split())
    G[R][C] = 1 #음식물 쓰레기 좌표 표시
for i in range(1,N+1):
    for j in range(1,M+1):
        if G[i][j] and not Chk[i][j]: #음식물이 있고 아직 체크되지 않았으면
            size = max(size,BFS(i,j)) #BFS함수의 리턴값을 대조하여 size 수정
print(size)