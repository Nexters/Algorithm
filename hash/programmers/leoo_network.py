"""
네트워크
- https://programmers.co.kr/learn/courses/30/lessons/43162

- category
    - union_find    O(n^2) / 2
    - bfs           O(n^2)

"""

# union find version
from sys import setrecursionlimit
from typing import List, Union

setrecursionlimit(10 ** 9)


def solution(n, computers):
    parents: List[Union[int, None]] = [None, ] * n

    def find(idx):
        if parents[idx] is None:
            return idx
        parents[idx] = find(parents[idx])
        return parents[idx]

    def union(a, b):
        r1, r2 = find(a), find(b)
        if not r1 == r2:
            parents[r2] = r1

    for i in range(n):
        for j in range(0, i):
            if computers[i][j]:
                union(i, j)
    result = sum((1 for p in parents if p is None))
    return result


"""
# bfs version O(n^2)
from collections import deque


def solution(n, computers):
    visited = [0] * n

    def bfs(i):
        visited[i] = 1
        queue = deque([i])

        # 1. 모든 노드와 연결을 가지는 경우 O(n^2) 이지만, 그럴 경우 bfs 호출 부분은 O(1)
        # 2. 모든 노드가 연결을 가지지 않는 경우 O(n) 이고, 이 경우 bfs 호출 부분은 O(n) 
        while queue:
            i = queue.popleft()
            for j, conn in enumerate(computers[i]):
                if not visited[j] and conn:
                    visited[j] = 1
                    queue.append(j)

    result = 0
    for i in range(n):
        if not visited[i]:
            bfs(i)
            result += 1
    return result


expect = 2
input_args = (3, [[1, 1, 0], [1, 1, 0], [0, 0, 1]])
assert solution(*input_args) == expect

expect = 1
input_args = (3, [[1, 1, 0], [1, 1, 1], [0, 1, 1]])
assert solution(*input_args) == expect
"""
