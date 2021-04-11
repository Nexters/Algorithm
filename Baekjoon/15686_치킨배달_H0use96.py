import itertools

size, store_max_count = map(int, input().split(" "))
data = [list(map(int, input().split(" "))) for _ in range(0, size)]

store = []
house = []

for w in range(size):
    for k in range(size):
        if data[w][k] == 1:
            house.append((w, k))
        elif data[w][k] == 2:
            store.append((w, k))

distance_table = [[0] * len(house) for _ in range(len(store))]

for store_index in range(len(store)):
    for house_index in range(len(house)):
        distance_table[store_index][house_index] = \
            abs(store[store_index][0] - house[house_index][0]) \
            + abs(store[store_index][1] - house[house_index][1])

combinations = (map(list, itertools.combinations(store, store_max_count)))
min_distance = 9999999

for groups in combinations:
    sum = 0
    for group in groups:
        for si in group:
            for hi in range(len(house)):
                sum = sum + distance_table[si][hi]
    min_distance = min(min_distance, sum)
    print(min_distance)

print(min_distance)