from collections import deque
n, k = map(int, input().split())
MAX = 100000
dis = [0] * (MAX + 1)

q = deque()
q.append(n)

while q:
    x = q.popleft()
    if x == k:
        print(dis[x])
        break
    for tmp in (x-1, x+1, x*2):
        if 0 <= tmp <= MAX and not dis[tmp]:
            dis[tmp] = dis[x] + 1
            q.append(tmp)