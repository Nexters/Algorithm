from itertools import combinations
import sys

n, m = map(int, input().split())
city = []

for i in range(n):
    city.append(list(map(int, input().split())))

house = []
chicken = []
for i in range(n):
    for j in range(n):
        if city[i][j] == 1:
            house.append((i, j))
        elif city[i][j] == 2:
            chicken.append((i, j))

comb = list(combinations(chicken, m))

answer = sys.maxsize
for i in comb:
    total = 0
    for h in house:
        min_dir = sys.maxsize
        for x, y in i:
            min_dir = min(min_dir, abs(h[0] - x) + abs(h[1] - y))

        total += min_dir
    answer = min(answer, total)

print(answer)
