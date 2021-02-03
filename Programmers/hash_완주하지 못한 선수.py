def solution(participant, completion):
    answer = ''
    Sum = {}
    for p in participant:
        if p in Sum.keys():
            Sum[p] += 1
        else:
            Sum[p] = 1
    for c in completion:
        Sum[c] += 1
    for key, value in Sum.items():
        if Sum[key]%2 !=0:
            answer = key
            break
    return answer
