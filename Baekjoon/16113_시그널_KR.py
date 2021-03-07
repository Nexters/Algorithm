import sys
input = sys.stdin.readline

def interpret(idx):
    if Signal[2][idx]=='.':
        return '7'
    elif Signal[2][idx:idx+3] == ['#','.','#']:
        return '0'
    elif Signal[1][idx:idx+2] == ['.','.']:
        if Signal[3][idx] == '#':
            return '2'
        else:
            return '3'
    elif Signal[3][idx:idx+2] == ['.','.']:
        if Signal[1][idx+2] == '#':
            return '9'
        else:
            return '5'
    else:
        if Signal[1][idx+2] == '#':
            return '8'
        else:
            return '6'
    return

N = int(input())
s = input()
line = N//5 #열의 수
Signal = [];res = ''
for i in range(0,N,line):
    Signal.append(list(s[i:i+line]))
print(Signal)
one = ['#','.'] #숫자 1
#0,6,9는 8에서 한칸 빠짐
#2,3,5는 8에서 두칸 빠짐
#4는 첫번째 행에서 가운데 한칸 빠짐
#7은 3번째 5번째 행 두칸씩 빠짐
#1은 그냥 한 열 통째로

#해결방법
# 1. 한 배열마다 인덱스를 돌면서 '#'가 나오면 그 인덱스부터 +3으로 for문을 돌림
# 2. 만약 '#'의 수가 한개라면 1, 두개라면 4
# 3. 나머지 경우는 조건문으로 보기

idx = 0
while True:
    if idx >= line:
        break
    flag = False
    for i in range(idx,line):
        if Signal[0][i] == '#':
            if i+2<=line and Signal[0][i:i+2] == one and Signal[2][i:i+2] == one:
                res += '1'
                idx = i+2
                flag = True
                break
            elif i+2>line and Signal[0][i:].count('#')== 1:
                res += '1'
                idx = i+2
                flag = True
                break
            elif Signal[0][i:i+3] == ['#','.','#']:
                res += '4'
            else: #다 '#','#','#'인 경우
                res += interpret(i) #스트링 형식의 숫자 리턴
            flag = True
            idx = i+3
            break
    if not flag:
        idx += 1
            
print(res)


# 40
# ###..#.##.#..#.####..#.##.#..#.####..#.#
# 답 : 811 -> output 84

# 35
# #...#.##...#.##...#.##...#.##...#.#
# 답 : 111 -> output 14

# 55
# ....#...#.#....#...#.#....#...#.#....#...#.#....#...#.#
# 답 : 111 -> output 14

# 80
# #..###...#..#.#.#..#.#...#..#.#.#..#.#...#..#.#.#..#.#...#..#.#.#..###...#..#.#.
# 답 : 10111 -> output 14