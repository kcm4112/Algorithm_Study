n, m = map(int, input().split())

dna = []

for i in range(n):
    dna.append(list(map(str, input())))

cnt = 0
result = ''
for i in range(m):
    a, c, g, t = 0, 0, 0, 0
    for j in range(n):
        if dna[j][i] == 'T':
            t += 1
        elif dna[j][i] == 'A':
            a += 1
        elif dna[j][i] == 'G':
            g += 1
        elif dna[j][i] == 'C':
            c += 1
    if max(a, c, g, t) == a:
        result += 'A'
        cnt += c + g + t
    elif max(a, c, g, t) == c:
        result += 'C'
        cnt += a + g + t
    elif max(a, c, g, t) == g:
        result += 'G'
        cnt += a + c + t
    elif max(a, c, g, t) == t:
        result += 'T'
        cnt += c + g + a

print(result)
print(cnt)
