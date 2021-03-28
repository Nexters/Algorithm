"""
환승 https://www.acmicpc.net/problem/5214
- G1
- simulation, bfs

"""
from collections import deque
MIS = lambda : map(int,input().split())

def bfs(i,d):
    queue = deque([i])
    s_visited[i] = 1
    while queue:
        d += 1
        for _ in range(len(queue)):
            ni = queue.popleft()
            if ni == N-1: return d
            for h in s2h[ni]:
                if h_visited[h]:continue
                h_visited[h] = 1
                for s in h2s[h]:
                    if s_visited[s]:continue
                    s_visited[s] = 1
                    queue.append(s)
    return -1

def simul():
    for h in range(M):
        for s in MIS():
            s2h[s - 1].append(h)
            h2s[h].append(s - 1)
    return bfs(0,0)


N,K,M = MIS()
s2h = [[] for _ in range(N)]
h2s = [[] for _ in range(M)]
s_visited = [0] * N
h_visited = [0] * M

if N == 1:print(1)
else: print(-1) if K == 1 else print(simul())