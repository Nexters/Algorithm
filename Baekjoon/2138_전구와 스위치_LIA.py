n = int(input())
curr = list(map(int, input()))
goal = list(map(int, input()))


def on_switch(light):
    count = 0

    for i in range(1, len(light)):
        if light[i - 1] == goal[i - 1]:
            continue
        else:
            count += 1
            light[i - 1] ^= 1
            light[i] ^= 1
            if i + 1 < len(light):
                light[i + 1] ^= 1

    if goal == light:
        return count
    return int(1e9)


count_first_off = on_switch(curr[:])
curr[0] ^= 1
curr[1] ^= 1
count_first_on = on_switch(curr[:]) + 1

answer = min(count_first_on, count_first_off)

if answer >= int(1e9):
    print(-1)
else:
    print(answer)
