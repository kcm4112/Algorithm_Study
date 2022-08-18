import heapq
import sys

n = int(input())
m = int(input())

graph = [[] for _ in range(n+1)]
d = [float('inf')] * (n+1)

for i in range(m):
    u, v, w = map(int, sys.stdin.readline().split())
    graph[u].append((v, w))

start, end = map(int, input().split())


q = []
d[start] = 0
heapq.heappush(q, (start, 0))

while q:
    node, weight = heapq.heappop(q)

    if d[node] < weight:
        continue
    for n, w in graph[node]:
        if weight + w < d[n]:
            d[n] = weight + w
            heapq.heappush(q, (n, d[n]))

print(d[end])