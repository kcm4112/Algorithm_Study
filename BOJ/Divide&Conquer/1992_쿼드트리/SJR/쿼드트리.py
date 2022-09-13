import sys
input = sys.stdin.readline

n = int(input())
arr = [list(map(int, input().rstrip())) for _ in range(n)]

def division(x, y, size):
    if size == 1:
        print(arr[x][y], end='')
        return
    num = arr[x][y]

    for i in range(x, x+size):
        for j in range(y, y+size):
            if num != arr[i][j]:
                print("(", end='')
                size //= 2
                division(x, y, size)
                division(x, y+size, size)
                division(x+size, y, size)
                division(x+size, y+size, size)
                print(")", end='')
                return
    print(arr[x][y], end='')
    return

division(0, 0, n)