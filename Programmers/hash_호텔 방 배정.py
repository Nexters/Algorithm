# 2019 카카오 개발자 겨울 인턴십
# 스터디 1월 5주차 공통 문제

#ver1 -효율성 fail
def solution(k, room_number):
    answer = []
    chk = [0] * k
    for num in room_number:
        if not chk[num-1]:
            answer.append(num)
            chk[num-1] =  1
        else:
            for j in range(num,k+1):
                if not chk[j]:
                    answer.append(j+1)
                    chk[j] = 1
                    break
    return answer

#ver2 - 효율성 fail
def solution(k, room_number):
    answer = []
    chk = {}
    for num in room_number:
        if num in chk.keys():
            temp = num
            while True:
                temp += 1
                if temp not in chk.keys():
                    break
            answer.append(temp)
            chk[temp] = 1
        else:
            answer.append(num)
            chk[num] = 1
    return answer