from _collections import deque

dh,dr,dc = [-1,1,0,0,0,0],[0,0,0,0,1,-1],[0,0,1,-1,0,0]

def BFS(SH,SR,SC,K):
    q = deque([(SH,SR,SC,K)])
    while q:
        h,r,c,k = q.popleft()
        building[h][r][c] = False
        for i in range(6):
            nh,nr,nc = h + dh[i], r + dr[i], c + dc[i]
            if 0<=nh<L and 0<=nr<R and 0<=nc<C and building[nh][nr][nc] != '#' and building[nh][nr][nc] != False:
                if building[nh][nr][nc] == '.':
                    building[nh][nr][nc] = False
                    q.append((nh,nr,nc,k+1))
                else:
                    return 'Escaped in {} minute(s).' .format(k+1)
    return 'Trapped!'

def start(): #시작 지점 찾기
    for h in range(H):
        for r in range(R):
            for c in range(C):
                if building[h][r][c] == 'S':
                    sh = h
                    sr = r
                    sc = c
                    print(BFS(sh,sr,sc,0))
                    return

while True:
    L,R,C = map(int,input().split())
    if L==0 and R==0 and C==0: break
    H = 0
    building = [[[] for _ in range(R)] for _ in range(L)]
    while H < L:
        for r in range(R):
            arr = list(map(str,input()))
            building[H][r] = arr
        empty = input()
        if empty=='':
            H += 1
    start()