n = int(input())
box = list(map(int, input().split()))

dp = [0] * len(box)
dp[0] = 1
for i in range(1, len(box)):
    dp[i] = 1
    for j in range(i):
        if box[i] > box[j]:
            dp[i] = max(dp[j] + 1, dp[i])

print(max(dp))