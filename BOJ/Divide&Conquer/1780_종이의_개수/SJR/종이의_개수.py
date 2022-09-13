import sys
input = sys.stdin.readline

n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]
check = [0] * 3

def paper(r, c, n):
    num = graph[r][c]
    for i in range(r, r+n):
        for j in range(c, c+n):
            if graph[i][j] != num:
                for k in range(3):
                    for l in range(3):
                        paper(r+k*(n//3), c+l*(n//3), n//3)
                return

    if num == -1:
        check[0] += 1
    elif num == 0:
        check[1] += 1
    else:
        check[2] += 1

paper(0, 0, n)
print(*check, sep='\n')
