/*
백준 골드4 1261 알고스팟
*/
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <vector>
#include <algorithm>
#include <stack>
#include <queue>
#include <climits>
using namespace std;
int n, m, c = 0;
vector<vector<int>> graph;
vector<vector<int>> visit;
vector<int> x = { 1, -1, 0, 0 };
vector<int> y = { 0, 0, 1, -1 };
queue<pair<int, int>> q;
int result = INT_MAX;
void bfs();
int main()
{
    cin >> n >> m;
    graph.resize(m + 1, vector<int>(n + 1, 0));
    visit.resize(m + 1, vector<int>(n + 1, result));

    for (int i = 1; i <= m; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            scanf("%1d", &graph[i][j]);
        }
    }
    q.push(make_pair(1, 1));
    visit[1][1] = 0;
    bfs();
    cout << visit[m][n];
}
void bfs()
{
    while (!q.empty())
    {
        int fx = q.front().first;
        int fy = q.front().second;
        q.pop();
        for (int i = 0; i < 4; i++)
        {
            int tempx = fx + x[i];
            int tempy = fy + y[i];
            if (tempx <= 0 || tempx > m || tempy <= 0 || tempy > n)
            {
                continue;
            }
            if (graph[tempx][tempy] == 1)
            { //이동할려는 좌표가 벽이다.
                if (visit[tempx][tempy] > visit[fx][fy] + 1)
                { //이미 저장된 벽의 개수보다 더 작다면 벽의 개수를 업데이트하고, queue에 넣어줌.
                    visit[tempx][tempy] = visit[fx][fy] + 1;
                    q.push(make_pair(tempx, tempy));
                }
            }
            else
            {
                if (visit[tempx][tempy] > visit[fx][fy])
                { //이미 저장된 벽의 개수보다 더 작다면 벽의 개수를 업데이트하고,queue에 넣어줌.
                    visit[tempx][tempy] = visit[fx][fy];
                    q.push(make_pair(tempx, tempy));
                }
            }
        }
    }
}