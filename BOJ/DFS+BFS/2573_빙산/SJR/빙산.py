from collections import deque
from copy import deepcopy
import sys
input = sys.stdin.readline

n, m = map(int, input().split())

ice = [list(map(int, input().split())) for _ in range(n)]
year = 0

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

# 얼음이 다 녹았는지 확인 -> 다 녹았으면 True 반환, 아니면 False 반환
def check_melt():
    for i in range(n):
        for j in range(m):
            if ice[i][j] != 0:
                return False
    return True

# 주변에 0 개수가 몇 개인지 반환
def cnt_zero(x, y):
    cnt = 0
    for i in range(4):
        mx, my = x + dx[i], y + dy[i]
        if 0<=mx<n and 0<=my<m and ice[my][mx] == 0:
            cnt += 1
    return cnt

def bfs(x, y):
    q = deque()
    q.append([x, y])
    visited = [[0] * m for _ in range(n)]
    visited[y][x] = 1
    while q:
        tmp_x, tmp_y = q.popleft()
        for i in range(4):
            move_x, move_y = tmp_x + dx[i], tmp_y + dy[i]
            if 0 <= move_x < m and 0 <= move_y < n and visited[move_y][move_x] == 0 and ice[move_y][move_x] != 0:
                visited[move_y][move_x] = 1
                new_data[move_y][move_x] = 0
                q.append([move_x, move_y])

while 1:
    year += 1
    cnt = 0

    if check_melt() == True:
        print(0)
        break

    new_data = [[0] * m for _ in range(n)]
    for i in range(n):
        for j in range(m):
            if ice[i][j] != 0:
                # 주변에 0 개수 확인
                zero = cnt_zero(j, i)
                # 원래 자신의 값 - 주변 0의 개수
                value = ice[i][j] - zero
                # value가 음수가 되면 그 자리는 0으로 처리
                if value >= 0:
                    new_data[i][j] = value
                else:
                    new_data[i][j] = 0
    # 모든 원소를 다 처리한 후 한번에 ice 배열을 업데이트
    ice = deepcopy(new_data)

    for i in range(n):
        for j in range(m):
            # 빙하가 있다면 bfs로 보내서 주변 빙산 확인
            if new_data[i][j] != 0:
                # 처음 0이 아닌 빙산으로부터 동서남북으로 계속 파고든다
                bfs(j, i)
                cnt += 1

    # cnt가 2개 이상이라면 빙산은 분리된 것을 의미
    if cnt >= 2:
        print(year)
        break