import sys

input = sys.stdin.readline

n = int(input())
in_car = {}

for i in range(n):
    car = input()
    in_car[car] = i

out_car = []

for i in range(n):
    car = input()
    out_car.append(car)

answer = 0

for i in range(n - 1):
    for j in range(i + 1, n):
        if in_car[out_car[i]] > in_car[out_car[j]]:
            answer += 1
            break

print(answer)