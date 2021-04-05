import sys

sys.setrecursionlimit(10 ** 6)

n, b = map(int, input().split())
recovery = []
dp = [[[0] * 3001 for _ in range(3001)]] * 2

for _ in range(n):
    recovery.append(int(input()))


def solution(sleep, count, idx):
    if idx == n:
        return 0

    if dp[sleep][count][idx] != 0:
        return max(solution(1, count, idx + 1))

    if sleep:
        return max(solution(1, count - 1, idx + 1), solution(0, count, idx + 1) + recovery[idx])
    else:
        return max(solution(1, count - 1, idx + 1), solution(0, count, idx + 1))


answer = max(solution(0, b, 0), solution(1, b, 0))
print(answer)
