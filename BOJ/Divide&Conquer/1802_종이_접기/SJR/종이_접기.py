import sys
input = sys.stdin.readline

t = int(input())


def is_possible(ss):
    if len(ss) == 1:
        return True
    mid = len(ss) // 2
    for i in range(mid):
        if ss[i] == ss[-i-1]:
            return False
    return is_possible(ss[:mid])

for _ in range(t):
    string = input().rstrip()
    result = is_possible(string)
    if result:
        print('YES')
    else:
        print('NO')