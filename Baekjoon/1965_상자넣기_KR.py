n = int(input())
box = list(map(int,input().split()))
dp = [1 for _ in range(n)]
for i in range(1,n):
    for j in range(i):
        if box[i] > box[j]:
            dp[i] = max(dp[i],dp[j]+1) #box[i]보다 작은 box[j]가 담을 수 있는 최대 상자의 수
print(max(dp))