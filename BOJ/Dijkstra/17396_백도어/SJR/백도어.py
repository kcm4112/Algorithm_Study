import heapq
import sys


# 노드 개수
N, m = map(int, sys.stdin.readline().split())
graph = [[] for _ in range(N+1)]
# 거리 저장 배열
d = [float('inf')] * (N+1)
ward = list(map(int, sys.stdin.readline().split()))
ward[-1] = 0

for i in range(m):
    start, end, length = map(int, sys.stdin.readline().split())
    graph[start].append((end, length))
    graph[end].append((start, length))


def dijkstra(start):
    q = []
    heapq.heappush(q, (start, 0))
    d[start] = 0
    while q:
        node, weight = heapq.heappop(q)
        if d[node] < weight:
            continue
        for v, w in graph[node]:
            cost = weight + w
            if cost < d[v] and ward[v] == 0:
                d[v] = cost
                heapq.heappush(q, (v, cost))

dijkstra(0)

print(d[N-1] if d[N-1] != float('inf') else -1)