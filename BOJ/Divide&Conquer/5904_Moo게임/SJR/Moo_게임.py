import sys
input = sys.stdin.readline
n = int(input())
moo = ['m', 'o', 'o']
length = []
length.append(3)
for i in range(1, n+1):
    length.append(0)
    length[i] = length[i-1] * 2 + i + 3
    if length[i] >= n:
        break

pivot = n-1
while True:
    if i == 0:
        print(moo[pivot])
        break
    if pivot >= length[i-1] + i + 3:
        pivot -= (length[i-1] + i + 3)
        i -= 1
    elif pivot >= length[i-1]:
        if pivot == length[i-1]:
            print('m')
        else:
            print('o')
        break
    else:
        i -= 1