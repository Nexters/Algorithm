from collections import deque


def solution():
    n = int(input())
    p1, p2 = map(int, input().split())
    m = int(input())

    graph = [[] for _ in range(n + 1)]
    visited = [False] * (n + 1)
    for _ in range(m):
        x, y = map(int, input().split())
        graph[x].append(y)
        graph[y].append(x)

    dfs(graph, p1, p2, visited)


def dfs(graph, start, end, visited):
    q = deque([(start, 0)])
    visited[start] = True

    while q:
        temp = q.popleft()
        num = temp[0]
        count = temp[1]

        if num == end:
            print(count)
            return

        for i in graph[num]:
            if not visited[i]:
                visited[i] = True
                q.append((i, count + 1))

    print(-1)


solution()
