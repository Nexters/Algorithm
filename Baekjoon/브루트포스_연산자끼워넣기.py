#14888. 연산자 끼워넣기 silver 1

#ver1 pypy로 통과(python은 시간초과)
def backtracking(k):
    global Min,Max
    if k == N-1:
        # print(" ".join(seq))
        num = arr[0]
        for j in range(N-1):
            if seq[j] == '+':
                num += arr[j+1]
            elif seq[j] == '-':
                num -= arr[j+1]
            elif seq[j] == '*':
                num *= arr[j+1]
            else:
                if num < 0:
                    num = -(-(num) // arr[j+1]) 
                else:
                    num //= arr[j+1]
                    
        # print(num)
        if num > Max:
            Max = num
        if num < Min:
            Min = num
        return
    for i in range(N-1):
        if not chk[i]:
            chk[i] = 1
            seq.append(cal[i])
            backtracking(k+1)
            chk[i] = 0
            seq.pop()

N = int(input()) #수의 개수
arr = list(map(int,input().split())) #[A1, A2, ... An]
cal = list(map(int,input().split())) #[2,1,1,1]
chk = [0] * sum(cal) #[0,0,0,0,0]
cal = ['+']*cal[0] + ['-']*cal[1] + ['*']*cal[2] + ['%']*cal[3] #['+','+','-','*','%']
seq = []
Max = -987654321
Min = 987654321
backtracking(0)
print(Max)
print(Min)


#ver2 python 통과
def backtracking(k,num):
    global Min,Max
    if k == N-1:
        if num < Min:
            Min = num
        if num > Max:
            Max = num
        return
    for i in range(N-1):
        if chk[i]: continue
        chk[i] = 1
        if cal[i] == '+':
            backtracking(k+1, num+A[k+1])
        elif cal[i] == '-':
            backtracking(k+1, num-A[k+1])
        elif cal[i] == '*':
            backtracking(k+1, num*A[k+1])
        else:
            if num < 0:
                backtracking(k+1, -((-num)//A[k+1]))
            else:
                backtracking(k+1, num//A[k+1])
        chk[i] = 0

N = int(input()) #수의 개수
A = list(map(int,input().split())) #[A1, A2, ... An]
cnt = list(map(int,input().split())) #[2,1,1,1]
chk = [0] * (N-1) #[0,0,0,0,0]
cal = ['+']*cnt[0] + ['-']*cnt[1] + ['*']*cnt[2] + ['%']*cnt[3] #['+','+','-','*','%']
seq = []
Max = -987654321
Min = 987654321
backtracking(0,A[0])
print(Max)
print(Min)