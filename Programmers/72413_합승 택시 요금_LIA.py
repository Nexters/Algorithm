'''
2021 kakao 합승 택시 요금
n: 지점의 개수
s: 출발, a: A 도착, b: B 도착
fares: 택시 요금
'''


def solution(n, s, a, b, fares):
    INF = int(1e9)
    graph = [[INF] * (n + 1) for _ in range(n + 1)]

    for i in range(1, n + 1):
        graph[i][i] = 0

    for x, y, z in fares:
        graph[x][y] = z
        graph[y][x] = z

    for k in range(1, n + 1):
        for i in range(1, n + 1):
            for j in range(1, n + 1):
                if i == j:
                    continue
                graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])

    answer = graph[s][a] + graph[s][b]
    for i in range(1, n + 1):
        answer = min(graph[s][i] + graph[i][a] + graph[i][b], answer)

    return answer


solution(6, 4, 6, 2,
         [[4, 1, 10], [3, 5, 24], [5, 6, 2], [3, 1, 41], [5, 1, 24], [4, 6, 50], [2, 4, 66], [2, 3, 22], [1, 6, 25]])
solution(7, 3, 4, 1, [[5, 7, 9], [4, 6, 4], [3, 6, 1], [3, 2, 3], [2, 1, 6]])
solution(6, 4, 5, 6, [[2, 6, 6], [6, 3, 7], [4, 6, 7], [6, 5, 11], [2, 5, 12], [5, 3, 20], [2, 4, 8], [4, 3, 9]])
