def firstOn(arr):
    cnt=1
    arr[0] = int(not arr[0]) #맨 처음 스위치를 눌렀으므로 첫번째와 두번째 전구 상태 바꾸기
    arr[1] = int(not arr[1])
    for i in range(1,N):
        if(arr[i-1] != want[i-1]): #원하는 상태와 다르면 스위치 on
            cnt+=1
            arr[i-1] = int(not arr[i-1]) #i-1,i번째 전구 상태 바꾸고
            arr[i] = int(not arr[i])
            if(i!=N-1): #범위 안에 있다면 i+1번째 전구 상태도 바꾸기
                arr[i+1] = int(not arr[i+1])
    if(arr==want):
        return cnt
    else:
        return -1

def firstOff(arr):
    cnt=0
    for i in range(1,N):
        if(arr[i-1] != want[i-1]): #원하는 상태와 다르면 스위치 on
            cnt+=1
            arr[i-1] = int(not arr[i-1])
            arr[i] = int(not arr[i])
            if(i != N-1):
                arr[i+1] = int(not arr[i+1])
    if(arr==want):
        return cnt
    else:
        return -1

N = int(input())
bulb = list(map(int,input())) 
want = list(map(int,input()))

tmp = []
for k in range(N):
    tmp.append(bulb[k])

on = firstOn(tmp) #맨 처음 스위치를 누르는 경우
off = firstOff(bulb) #맨 처음 스위치를 누르지 않는 경우

if(on>=0 and off>=0):
    print(min(on,off))
elif(on>=0 and off<0):
    print(on)
elif(on<0 and off>=0):
    print(off)
else:
    print(-1)