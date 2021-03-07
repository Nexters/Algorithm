#ver1(내 풀이)
def solution(answers):
    answer = []
    chk = [0,0,0]
    sol = [[1,2,3,4,5], [2,1,2,3,2,4,2,5], [3,3,1,1,2,2,4,4,5,5]]
    for i in range(len(sol)):
        for j in range(len(answers)):
            if sol[i][j%len(sol[i])] == answers[j]:
                chk[i] += 1
    Max = max(chk)
    if chk.count(Max) == 1:
        answer.append((chk.index(Max))+1)
    else:
        for k in range(3):
            if chk[k] == Max:
                answer.append(k+1)
    return answer

#ver2(다른 사람 풀이)
def solution(answers):
    pattern1 = [1,2,3,4,5]
    pattern2 = [2,1,2,3,2,4,2,5]
    pattern3 = [3,3,1,1,2,2,4,4,5,5]
    score = [0, 0, 0]
    result = []

    for idx, answer in enumerate(answers):
        if answer == pattern1[idx%len(pattern1)]:
            score[0] += 1
        if answer == pattern2[idx%len(pattern2)]:
            score[1] += 1
        if answer == pattern3[idx%len(pattern3)]:
            score[2] += 1

    for idx, s in enumerate(score):
        if s == max(score):
            result.append(idx+1)

    return result