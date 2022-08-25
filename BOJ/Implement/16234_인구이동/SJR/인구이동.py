import sys
import math
from collections import deque

input = sys.stdin.readline

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

n, l, r = map(int, input().split())

country = []
for i in range(n):
    country.append(list(map(int, input().split())))

def bfs(i, j):
    q = deque()
    q.append((i, j))
    visit[i][j] = True
    # 연합
    union = [(i, j)]
    count = country[i][j]   # 총 연합된 국가 수

    while q:
        x, y = q.popleft()
        for d in range(4):
            nx = x + dx[d]
            ny = y + dy[d]
            if nx < 0 or ny < 0 or nx >= n or ny >= n:
                continue
            if visit[nx][ny]:
                continue
            if l <= abs(country[nx][ny] - country[x][y]) <= r:
                union.append((nx, ny))
                visit[nx][ny] = True
                q.append((nx, ny))
                count += country[nx][ny]

    # 인구수 계산
    for x, y in union:
        country[x][y] = math.floor(count / len(union))

    return len(union)

result = 0

while True:
    visit = [[False] * n for _ in range(n)]
    flag = False  # 인구 이동 존재 유무 플래그

    for i in range(n):
        for j in range(n):
            if not visit[i][j]:
                if bfs(i, j) > 1:
                    flag = True
    if not flag:
        break
    result += 1

print(result)