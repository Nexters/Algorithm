def dist(chicken_arr):
    global Min
    Sum = 0
    for h in house: #각 집마다
        min_dist = 987654321
        for c in chicken_arr: #각 치킨집과의 거리 계산
            min_dist = min(min_dist,(abs(h[0]-c[0])+abs(h[1]-c[1])))
        Sum += min_dist
    Min = min(Min,Sum)
    return
        
def combine(cnt,idx):
    global M, chicken, mix
    if cnt == M:
        dist(mix) #M개의 조합이 만들어졌으면 거리 계산
        return
    for r in range(idx,len(chicken)):
        mix.append(chicken[r])
        combine(cnt+1,r+1)
        mix.pop()

N,M = map(int,input().split())
city = [list(map(int,input().split())) for _ in range(N)]
chicken = [];house = []
#치킨집, 집 표시
for i in range(N):
    for j in range(N):
        if city[i][j] == 2:
            chicken.append([i,j])
        elif city[i][j] == 1:
            house.append([i,j])
mix = [];Min = 987654321
combine(0,0) #조합 함수

print(Min)