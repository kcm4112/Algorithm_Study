from collections import deque
from sys import stdin

input = stdin.readline

N = int(input())
graph = [list(map(int, input().split())) for _ in range(N)]
g_min = min(map(min, graph))
g_max = max(map(max, graph))

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def bfs(x, y, safe):
    q = deque()
    q.append((x, y))
    visited[x][y] = 1

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < N and 0 <= ny < N:
                if graph[nx][ny] >= safe and visited[nx][ny] == 0:
                    visited[nx][ny] = 1
                    q.append([nx, ny])


max_safe_area = g_min

for safe in range(g_min, g_max + 1):
    visited = [[0] * N for _ in range(N)]
    tmp = 0
    for i in range(N):
        for j in range(N):
            if graph[i][j] >= safe and visited[i][j] == 0:
                bfs(i, j, safe)
                tmp += 1
    if tmp > max_safe_area:
        max_safe_area = tmp

print(max_safe_area)