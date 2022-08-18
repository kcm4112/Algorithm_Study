import sys
import heapq

INF = float('inf')
V, E = map(int, sys.stdin.readline().split())
K = int(sys.stdin.readline())
graph = [[] for _ in range(V + 1)]
d = [INF] * (V + 1)
q = []


for i in range(E):
    u, v, w = map(int, sys.stdin.readline().split())
    graph[u].append((v, w))

def dijkstra(start):
    d[start] = 0
    heapq.heappush(q, (0, start))

    while q:
        weight, node = heapq.heappop(q)

        if d[node] < weight:
            continue

        for n, w in graph[node]:
            next_w = weight + w

            if next_w < d[n]:
                d[n] = next_w
                heapq.heappush(q, (next_w, n))

dijkstra(K)

for i in d[1:]:
    print(i if i != INF else "INF")