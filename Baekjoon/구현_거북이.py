# #ver 1
# def move(mc): #move command
#     global dr,location,min_X,max_X,min_Y,max_Y
#     if mc == 'F':
#         location[0] += D[dr][0][0]
#         location[1] += D[dr][0][1]
#     else:
#         location[0] += D[dr][1][0]
#         location[1] += D[dr][1][1]
#     min_X = min(location[0],min_X)
#     min_Y = min(location[1],min_Y)
#     max_X = max(location[0],max_X)
#     max_Y = max(location[1],max_Y)

# def change_dr(cc): #change command
#     global dr
#     if cc == 'L':
#         dr = (dr-1)%4
#     else:
#         dr = (dr+1)%4

# #북동남서 각 4방향마다 F,B 명령을 받았을 때 움직일 좌표
# D = [[(0,1),(0,-1)],[(1,0),(-1,0)],[(0,-1),(0,1)],[(-1,0),(1,0)]] 

# for _ in range(int(input())): #테케 입력
#     order = list(input()) #명령 입력
#     min_X = max_X = min_Y = max_Y = 0 #x,y의 최소,최대 좌표 모두 0
#     dr = 0 #direction, 처음에는 북쪽, D[0]이 북쪽에 해당하므로 dr은 처음에 0
#     location =[0,0] #현재 좌표  
#     for i in order:
#         if i in ['F','B']:
#             move(i) #거북이를 이동시키는 함수
#         else:
#             change_dr(i) #거북이가 바라보는 방향을 바꿀 함수
#     res = abs(max_X-min_X) * abs(max_Y-min_Y) 
#     print(res)

#ver 2
def cal(): #move command
    global location,min_X,max_X,min_Y,max_Y
    min_X = min(location[0],min_X)
    min_Y = min(location[1],min_Y)
    max_X = max(location[0],max_X)
    max_Y = max(location[1],max_Y)

D = [(0,1),(1,0),(0,-1),(-1,0)] #북동남서

for _ in range(int(input())): #테케 입력
    order = input() #명령 입력
    min_X = max_X = min_Y = max_Y = 0 #x,y의 최소,최대 좌표 모두 0
    dr = 0 #direction, 처음에는 북쪽, D[0]이 북쪽에 해당하므로 dr은 처음에 0
    location =[0,0] #현재 좌표  
    for i in order:
        if i == 'L':
            dr = (dr-1)%4
        elif i == 'R':
            dr = (dr+1)%4
        elif i == 'F':
            location[0] += D[dr][0]
            location[1] += D[dr][1]
            cal()
        else:
            location[0] -= D[dr][0]
            location[1] -= D[dr][1]  
            cal() 
    res = abs(max_X-min_X) * abs(max_Y-min_Y) 
    print(res)

#ver 3 
D = [(0,1),(1,0),(0,-1),(-1,0)] #북동남서
for _ in range(int(input())): #테케 입력
    order = input() #명령 입력
    dr = 0 #direction, 처음에는 북쪽, D[0]이 북쪽에 해당하므로 dr은 처음에 0
    x = [0];y = [0] #x,y의 좌표 담는 리스트
    for i in order:
        if i == 'L':
            dr = (dr-1)%4
        elif i == 'R':
            dr = (dr+1)%4
        elif i == 'F':
            x.append(x[-1] + D[dr][0])
            y.append(y[-1] + D[dr][1])
        else:
            x.append(x[-1] - D[dr][0])
            y.append(y[-1] - D[dr][1])  
    res = abs(max(x)-min(x)) * abs(max(y)-min(y)) 
    print(res)