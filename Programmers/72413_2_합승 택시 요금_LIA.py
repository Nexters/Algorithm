'''
2021 kakao 합승 택시 요금
n: 지점의 개수
s: 출발, a: A 도착, b: B 도착
fares: 택시 요금

다익스트라로 재풀이
'''
import heapq


def solution(n, s, a, b, fares):
    INF = int(1e9)
    total_fares = [[INF] * (n + 1) for _ in range(n + 1)]
    graph = [[] for _ in range(n + 1)]

    for c, d, f in fares:
        graph[c].append((d, f))  # 인덱스, 비용
        graph[d].append((c, f))

    for i in range(1, n + 1):
        q = []
        total_fares[i][i] = 0
        heapq.heappush(q, (0, i))  # 비용, 시작점

        while q:
            fare, idx = heapq.heappop(q)

            if fare <= total_fares[i][idx]:
                for g in graph[idx]:  # 인덱스, 비용
                    if fare + g[1] < total_fares[i][g[0]]:
                        total_fares[i][g[0]] = fare + g[1]
                        heapq.heappush(q, (total_fares[i][g[0]], g[0]))

    answer = total_fares[s][a] + total_fares[s][b]

    for i in range(1, n + 1):
        answer = min(answer, total_fares[s][i] + total_fares[i][a] + total_fares[i][b])
    return answer


solution(6, 4, 6, 2,
         [[4, 1, 10], [3, 5, 24], [5, 6, 2], [3, 1, 41], [5, 1, 24], [4, 6, 50], [2, 4, 66], [2, 3, 22], [1, 6, 25]])
solution(7, 3, 4, 1, [[5, 7, 9], [4, 6, 4], [3, 6, 1], [3, 2, 3], [2, 1, 6]])
solution(6, 4, 5, 6, [[2, 6, 6], [6, 3, 7], [4, 6, 7], [6, 5, 11], [2, 5, 12], [5, 3, 20], [2, 4, 8], [4, 3, 9]])
