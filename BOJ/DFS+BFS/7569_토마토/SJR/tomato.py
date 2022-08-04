from collections import deque

m, n, h = map(int, input().split())
tomato = []

xx = [-1, 1, 0, 0, 0, 0]
yy = [0, 0, -1, 1, 0, 0]
zz = [0, 0, 0, 0, -1, 1]

q = deque()

# 3차원 배열은 z, y, x 순
for i in range(h):
    tomato.append([])
    for j in range(n):
        tomato[i].append(list(map(int, input().split())))
        for k in range(m):
            if tomato[i][j][k] == 1:
                q.append([i, j, k])

while q:
    z, y, x = q.popleft()
    # x, y, z 모두 증가, 감소 2개씩 총 6개
    for i in range(6):
        x_move = x + xx[i]
        y_move = y + yy[i]
        z_move = z + zz[i]

        # 범위 밖 -> pass
        if 0<=x_move<m and 0<=y_move<n and 0<=z_move<h and tomato[z_move][y_move][x_move] == 0:
            q.append([z_move, y_move, x_move])
            tomato[z_move][y_move][x_move] = tomato[z][y][x] + 1

day = 0
for i in tomato:
    for j in i:
        for k in j:
            if k == 0:
                print(-1)
                exit()
        day = max(day, max(j)-1)

print(day)
