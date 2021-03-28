n = int(input())
dp = [[1,1,1] for _ in range(n)]
for i in range(1,n):
    dp[i][0] = dp[i-1][0] + dp[i-1][1] + dp[i-1][2]
    dp[i][1] = dp[i-1][0] + dp[i-1][2]
    dp[i][2] = dp[i-1][0] + dp[i-1][1]

case = dp[n-1][0]+dp[n-1][1]+dp[n-1][2] 
print( case % 9901)
#메모리 초과