/*
실버 1 효율적힌 해킹 dfs bfs
*/
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int n, m, c = 0, Max = 0;
vector<int> graph[10001];
void dfs(int v);
vector<int> visit;
vector<int> vertex;
int main()
{
    int start, finish;
    cin >> n >> m;
    vertex.resize(n + 1);
    for (int i = 0; i < m; i++)
    { //간선 입력
        cin >> start >> finish;
        graph[start].push_back(finish);
    }
    for (int i = 1; i <= n; i++)
    {
        visit = vector<int>(n + 1, 0);
        visit[i]++;
        dfs(i);
    }
    for (int i = 1; i <= n; i++)
    {
        if (vertex[i] == Max)
        {
            cout << i << " ";
        }
    }
}
void dfs(int v)
{
    vertex[v]++;
    Max = max(Max, vertex[v]);
    for (int i = 0; i < graph[v].size(); i++)
    {
        int next = graph[v][i];
        cout << v << " " << next << endl;
        if (!visit[next]) //첫 방문.
        {
            visit[next]++;
            dfs(next);
        }
    }
}