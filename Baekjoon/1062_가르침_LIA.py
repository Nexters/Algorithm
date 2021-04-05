from itertools import combinations


def check(learn_word):
    count = 0
    for word in words:
        flag = True
        for w in word:
            if w not in learn_word:
                flag = False
                break

        if flag:
            count += 1

    return count


n, k = map(int, input().split())
words = []  # 전체 단어
word_set = {'a', 'n', 't', 'i', 'c'}  # 학습한 단어 set
answer = 0

for i in range(n):
    temp_word = list(input()[4:-4])
    words.append(temp_word)
    word_set.update(temp_word)

if k < 5 or k == 26:
    print(0 if k < 5 else n)
    exit(0)

comb = combinations(word_set, k)
for i in comb:
    if 'a' in i and 'n' in i and 't' in i and 'i' in i and 'c' in i:
        answer = max(check(i), answer)

print(answer)
