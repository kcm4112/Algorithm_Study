import sys
input = sys.stdin.readline
n = int(input())
dp = [0]*(1001)
dp[1], dp[3], dp[4] = 1, 1, 1

# SK 짝, CY 홀
if n >= 5:
    for i in range(5, n+1):
        if not dp[i-1]:
            dp[i] = 1
        if not dp[i-3]:
            dp[i] = 1
        if not dp[i-4]:
            dp[i] = 1

if dp[n]:
    print("SK")
else:
    print("CY")