/*
백준 골드 5 최소비용 구하기
*/
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
#define INF 987654321
int n, m, S, F;
vector<vector<int>> graph;
vector<int> d;
vector<int> visit;
vector<int> location;
void Dijkstra(int start);
int smallIdx(int start);
int main()
{
    cin >> n >> m;
    graph.resize(n + 1, vector<int>(n + 1, INF));
    d.resize(n + 1, INF);
    visit.resize(n + 1);
    location.resize(n + 1);
    for (int i = 0; i < m; i++)
    {
        int s, f, v;
        cin >> s >> f >> v;
        graph[s][f] = min(graph[s][f], v);
    }
    cin >> S >> F;
    d[S] = 0;
    for (int i = 1; i <= n; i++)
    {
        location[i] = S;
        graph[i][i] = 0;
    }
    Dijkstra(S);
    cout << d[F];
    //출발지와 도착점이 정해진다.
    //처음 출발지를 기준으로 distance 배열을 만들자
    //출발점 기준으로 distance넣기.
}
void Dijkstra(int start)
{
    if (visit[start] == 1)
    {
        return;
    }
    visit[start] = 1;
    for (int i = 1; i <= n; i++)
    {
        //만약 d[i]가 크다면 넣어줘야겟죠?
        if (visit[i] == 1)
        {
            continue;
        }
        if (d[i] > graph[start][i])
        {
            d[i] = min(d[i], graph[start][i] + d[start]); //현재 지점이 start, 이전에 있던 곳이 location[start]
            location[i] = start;
        }
        // d[i]가 출발노드에서 가는 거리보다 작다면 업데이트 해줄 필요가 없음.
    }
    int arrive = smallIdx(start);
    if (arrive == 0)
    {
        return;
    }
    Dijkstra(arrive);
}
int smallIdx(int start)
{
    int MAX = INF;
    int sidx = 0;
    for (int i = 1; i < d.size(); i++)
    {
        if (i == start || visit[i] == 1)
        {
            continue;
        }
        if (d[i] < MAX)
        {
            sidx = i;
            MAX = d[i];
        }
    }
    return sidx;
}