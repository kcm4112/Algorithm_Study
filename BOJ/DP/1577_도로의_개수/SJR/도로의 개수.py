# 가로 n, 세로 m
n, m = map(int, input().split())
k = int(input())
road = set()
dp = [[0] * (n+1) for _ in range(m+1)]
dp[0][0] = 1

# 끊어진 길 넣기
for i in range(k):
    a1, b1, a2, b2 = map(int, input().split())
    if a1 > a2 : a1, a2 = a2, a1
    if b1 > b2 : b1, b2 = b2, b1
    road.add(((a1, b1), (a2, b2)))

# 세로 겉에 길
for i in range(1, m+1):
    if ((0, i-1), (0, i)) in road:
        continue
    dp[i][0] = dp[i-1][0]

# 가로 겉에 길
for i in range(1, n+1):
    if ((i-1, 0), (i, 0)) in road:
        continue
    dp[0][i] = dp[0][i-1]

for i in range(1, n+1):
    for j in range(1, m+1):
        if ((i-1, j), (i, j)) in road and ((i, j-1), (i, j)) in road:
            continue
        if ((i-1, j), (i, j)) in road:
            dp[j][i] = dp[j-1][i]
        elif ((i, j-1), (i, j)) in road:
            dp[j][i] = dp[j][i-1]
        else:
            dp[j][i] = dp[j][i-1] + dp[j-1][i]

print(dp[m][n])