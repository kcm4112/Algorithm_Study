n = int(input())
work = []

for i in range(n):
    d, w = map(int, input().split())
    work.append([d, w])

# 점수순으로 내림차순 정렬
work.sort(reverse=True, key=lambda x: x[1])

day = {}

for date, score in work:
    tmp = date
    while tmp >= 1:
        if tmp not in day:
            day[tmp] = score
            break
        else:
            tmp -= 1

result = 0

for i in day.values():
    result += i

print(result)
