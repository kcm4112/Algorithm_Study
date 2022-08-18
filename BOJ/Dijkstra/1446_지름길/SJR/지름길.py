import heapq
import sys

# 노드 개수
N, D = map(int, sys.stdin.readline().split())
graph = [[] for _ in range(D+1)]

for i in range(D):
    graph[i].append((i+1, 1))

for i in range(N):
    start, end, length = map(int, sys.stdin.readline().split())
    if end > D:
        continue
    graph[start].append((end, length))

# 거리 저장 배열
d = [float('inf')] * (D+1)
# 출발지의 거리는 0으로 세팅
d[0] = 0

q = []
heapq.heappush(q, (0, 0))

while q:
    node, weight = heapq.heappop(q)

    if d[node] < weight:
        continue
    for n, w in graph[node]:
        if weight + w < d[n]:
            d[n] = weight + w
            heapq.heappush(q, (n, d[n]))

print(d[D])