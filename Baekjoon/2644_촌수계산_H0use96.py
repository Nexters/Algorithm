
# 6 (p) 1 (p) 0 (s) 2

def checkSons(relations, cur_index, target_index):

    if relations[cur_index][target_index] == 1:
        print(f"{cur_index} START CHON UP: 1")
        return 1

    row = relations[cur_index]

    sons = []
    for i in range(0, len(row)):
        if row[i] == 1:
            sons.append(i)

    print(f"{cur_index} sons: {sons}")

    for son in sons:
        print(f"{cur_index} move to Son {son}!")
        result = checkSons(relations, son, target_index)
        print(f"{cur_index} checkSon {son} : {result}")
        if result != 0:
            print(f"{cur_index} CHON UP: {result + 1}")
            return result + 1
    return 0


def checkParent(relations, cur_index, target_index):
    parent_index = 0
    for row in relations:
        if row[cur_index] == 1:
            row[cur_index] = 0
            break
        parent_index += 1

    if parent_index == len(relations):
        return 0

    print(f"{cur_index} move to parent {parent_index}")
    result = checkSons(relations, parent_index, target_index)
    print(f"{cur_index} check parent {parent_index} : {result}")

    if result != 0:
        print(f"{cur_index} CHON UP: {result+1}")
        return result + 1
    else:
        print(f"{cur_index} CHON UP: {result + 1}")
        return checkParent(relations, parent_index, target_index) + 1


if __name__ == "__main__":
    total_people = int(input())
    target_relation = list(map(lambda x: int(x) - 1, input().split(" ")))
    relation_count = int(input())

    relations = [[0 for i in range(0, total_people)] for i in range(0, total_people)]

    for i in range(0, relation_count):
        cur = list(map(lambda x: int(x) - 1, input().split(" ")))
        relations[cur[0]][cur[1]] = 1

    result = checkSons(relations, target_relation[0], target_relation[1])

    print("sons : ", result)

    if result > 0:
        print(result)
        exit()

    result = checkParent(relations, target_relation[0], target_relation[1])

    if result > 0:
        print(result+1)
    else:
        print(-1)

