def check(arr):
    global Min
    Rule = ['WBWBWBWB' ,'BWBWBWBW']
    for k in range(2):
        cal = 0
        for i in range(8):
            if arr[i] != Rule[(i+k)%2]:
                for j in range(8):
                    if arr[i][j] != Rule[(i+k)%2][j]:
                        cal += 1
        if cal < Min:
            Min = cal
    return
N,M = map(int,input().split()) #세로,가로
Board = [input() for _ in range(N)] #입력
Min = 987654321 #다시 칠해야 하는 정사각형의 최소 개수
for p in range((N-8)+1):
    for q in range((M-8)+1):
        temp = []
        for x in range(p,p+8):
            temp.append(Board[x][q:q+8])
        check(temp)
print(Min)