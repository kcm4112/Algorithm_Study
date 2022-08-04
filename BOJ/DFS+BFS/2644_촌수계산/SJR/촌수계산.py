from collections import deque

n = int(input())
a, b = map(int, input().split())
m = int(input())
visit = [0] * (n+1)
cnt = [0] * (n+1)

cousin = [[] for _ in range(n+1)]
q = deque()

for _ in range(m):
    x, y = map(int, input().split())
    cousin[x].append(y)
    cousin[y].append(x)

q.append(a)

while q:
    tmp = q.popleft()
    visit[tmp] = 1
    if tmp == b:
        break

    for i in range(1, n+1):
        if visit[i] == 0 and tmp in cousin[i]:
            q.append(i)
            cnt[i] = cnt[tmp] + 1

if cnt[b] == 0:
    print(-1)
else:
    print(cnt[b])