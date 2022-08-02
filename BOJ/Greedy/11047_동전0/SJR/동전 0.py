import sys

n, k = map(int, sys.stdin.readline().split())
coin = []
cnt = 0
for i in range(n):
    coin.append(int(sys.stdin.readline()))

while k > 0:
    for x in reversed(coin):
        if x <= k:
            k -= x
            cnt += 1
            break

print(cnt)