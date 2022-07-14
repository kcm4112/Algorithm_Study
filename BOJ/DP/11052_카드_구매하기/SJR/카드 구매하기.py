import sys
input = sys.stdin.readline

n = int(input())
temp = list(map(int, input().split()))
dp = [[0] * (n+1) for _ in range(n+1)]
money = []

for i in range(n):
    money.append(temp[i])

for i in range(1, n+1):
    for j in range(1, n+1):
        if j >= i:
            dp[i][j] = max(dp[i-1][j], money[i-1] + dp[i][j-i])
        else:
            dp[i][j] = dp[i-1][j]

print(dp[n][n])