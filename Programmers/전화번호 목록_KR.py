def solution(phone_book):
    phone_book.sort(key=len)
    for i in range(len(phone_book)-1):
        for j in range(i+1,len(phone_book)):
            if phone_book[i] in phone_book[j][0:len(phone_book[i])]:
                return False
    return True
#해시 문제지만 해시로 풀지못했다. 다시 풀어봐야징
