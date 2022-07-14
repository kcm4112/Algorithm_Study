n, k = map(int, input().split())
key = []
dp = [0] * (k+1)
dp[0] = 1

for _ in range(n):
    key.append(int(input()))

for i in key:
    for j in range(1, k+1):
        if j - i >= 0:
            dp[j] += dp[j - i]

print(dp.pop())