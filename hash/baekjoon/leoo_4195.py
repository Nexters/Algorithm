"""
친구 네트워크
- q) https://www.acmicpc.net/problem/4195
- a) https://www.acmicpc.net/source/25603034

- hash
- union find

type() vs isinstance()
- type은 상속까지 검사한다. 더 많은 검사가 필요해 더 느리다.
- isinstance는 지정된 객체 형태까지만 검사한다. 더 빠르긴 하지만, test 용도를 권고한다. (primitive type에서는 사용가능할듯)
"""
import sys
import typing

sys.setrecursionlimit(10 ** 9)
input = sys.stdin.readline


def root(k):
    if isinstance(friends[k], int):
        return k
    friends[k] = root(friends[k])
    return friends[k]


def union(names):
    for name in names:
        if name not in friends.keys():
            friends[name] = 1

    r1, r2 = map(root, names)
    if r1 == r2:
        return friends[r1]
    friends[r1] += friends[r2]
    friends[r2] = r1
    return friends[r1]


for tc in range(int(input())):
    result = []
    f = int(input())
    friends: typing.Dict[str, typing.Union[int, str]] = dict()
    for _ in range(f):
        result.append(union(input().split()))
    print('\n'.join(map(str, result)))
