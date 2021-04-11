import itertools

if __name__ == "__main__":
    base = "antic"
    word_count, letter_count = map(int, input().split(' '))
    words = []
    letters = [False] * 26
    letter_count -= 5
    maxResult = 0
    filtered_result = 0

    origin_words = []
    for _ in range(word_count):
        origin_words.append(input())

    if letter_count >= 21:
        print(word_count)
        exit()
    if letter_count <= 0:
        print(0)
        exit()

    # 데이터 전처리
    for origin in origin_words:
        origin = origin[4:len(origin) - 4]
        result = []
        for c in origin:
            if c in base:
                continue
            if c not in result:
                letters[ord(c) - ord('a')] = True
                result.append(c)

        if len(result) == 0:
            filtered_result += 1
        else:
            words.append(result)
    filter_letters = []
    for i in range(26):
        if letters[i]:
            filter_letters.append(chr(i+ord('a')))



    combinations = list(itertools.combinations(filter_letters, letter_count))

    for candidate in combinations:
        count = 0
        for word in words:
            flag = True
            for letter in word:
                if letter not in candidate:
                    flag = False
                    break
            if flag:
                count += 1
        maxResult = max(maxResult, count)

    print(maxResult+filtered_result)