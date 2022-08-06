/*
백준 2606 실버1 바이러스
*/
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int n, t, ans = 0;
vector<vector<int>> graph;
vector<int> visit;
void Dfs(int vir);
int main()
{
    cin >> n >> t;
    visit.resize(n + 1, 0);
    graph.resize(n + 1, vector<int>(n + 1, 0));
    for (int i = 0; i < t; i++)
    {
        int s, f;
        cin >> s >> f;
        graph[s][f] = 1, graph[f][s] = 1;
    }
    visit[1] = 1;
    Dfs(1);
    cout << ans;
}
void Dfs(int vir) //무조건 1번 컴퓨터가 바이러스라고 가정함.
{
    for (int j = 0; j <= n; j++)
    {
        if (graph[vir][j] == 1 && visit[j] == 0)
        { // 0이 방문 안한 상태
            visit[j] = 1;
            ans++;
            Dfs(j);
        }
    }
}