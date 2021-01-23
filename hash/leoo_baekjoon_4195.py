"""
친구 네트워크
- q) https://www.acmicpc.net/problem/4195
- a) https://www.acmicpc.net/source/25603034

- hash
- union find
"""
import sys
import typing

sys.setrecursionlimit(10 ** 9)
input = sys.stdin.readline


def root(k):
    if type(friends[k]) is int:
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
