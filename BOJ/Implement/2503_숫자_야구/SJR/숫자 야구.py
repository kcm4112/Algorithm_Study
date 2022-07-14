import itertools

num = list(itertools.permutations(('1', '2', '3', '4', '5', '6', '7', '8', '9'), 3))
n = int(input())
result = []

for _ in range(n):
    put, strike, ball = map(int, input().split())
    # int -> str -> list 해야 함
    key = list(str(put))
    # 배열에서 제외한 원소 개수
    cnt = 0
    for i in range(len(num)):
        s, b = 0, 0
        i -= cnt
        for j in range(len(key)):
            if num[i][j] == key[j]:
                s += 1
            elif key[j] in num[i]:
                b += 1
        if strike != s or ball != b:
           num.remove(num[i])
           cnt += 1

print(len(num))