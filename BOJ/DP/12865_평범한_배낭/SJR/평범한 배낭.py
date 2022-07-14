n, k = map(int, input().split())

weight = [0] * n
value = [0] * n
dp = [[0 for row in range(k+1)] for col in range(n+1)]
for i in range(n):
    weight[i], value[i] = map(int, input().split())

for i in range(1, len(dp)):
    for j in range(1, len(dp[0])):
        if weight[i-1] <= j:
            # i = 3, j = 7
            dp[i][j] = max(dp[i-1][j], dp[i-1][j - weight[i-1]] + value[i-1])
        else:
            dp[i][j] = dp[i-1][j]

print(dp[len(dp)-1][len(dp[0])-1])