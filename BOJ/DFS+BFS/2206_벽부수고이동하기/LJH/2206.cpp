/*
2206 골드3 벽부수고 이동하기
*/
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;
int n, m;
vector<vector<int>> graph;
vector<vector<vector<int>>> visit; //3차원 배열
queue<pair<pair<int, int>, int>> q;
vector<int> x = { -1, 1, 0, 0 };
vector<int> y = { 0, 0, -1, 1 };
int bfs();
int main()
{
    cin >> n >> m;
    graph.resize(n + 1, vector<int>(m + 1, 0));
    visit.resize(n + 1, vector<vector<int>>(m + 1, vector<int>(2, 0)));
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= m; j++)
        {
            scanf("%1d", &graph[i][j]);
        }
    }
    q.push(make_pair(make_pair(1, 1), 1)); //초기 출발점을 넣습니다.
    visit[1][1][1] = 1;         //방문 처리
    cout << bfs();
}
int bfs()
{
    //갈 수 있는 모든 방향을 넣어볼까?
    while (!q.empty())
    {
        int tempx = q.front().first.first;
        int tempy = q.front().first.second;
        int block = q.front().second;
        if (tempx == n && tempy == m) { //도착하면 그때의 거쳐간 벽의 개수를 return 해줌.
            return visit[tempx][tempy][block];
        }
        q.pop();
        // q에 있는 좌표를 추출.
        for (int i = 0; i < 4; i++)
        {
            int fx = tempx + x[i];
            int fy = tempy + y[i];
            if (fx <= 0 || fx > n || fy <= 0 || fy > m)
            { //이동할려는 좌표가 배열을 벗어날때
                continue;
            }

            if (graph[fx][fy] == 0 && visit[fx][fy][block] == 0) //이동할 수 있고, 아직 방문을 안한곳이면?
            { //일단 갈 수 있다면 그 좌표는 모두 q에 넣기
                q.push(make_pair(make_pair(fx, fy), block));
                visit[fx][fy][block] = visit[tempx][tempy][block] + 1;
            }
            if (block == 1 && graph[fx][fy] == 1) // block 0이면 나는 이미 벽을 뚫었다. 1이면 벽을 안뚫었다.
            { //아직 벽을 깨지 않았고, 이동하려는 좌표가 벽이라면
                q.push(make_pair(make_pair(fx, fy), 0));
                visit[fx][fy][0] = visit[tempx][tempy][block] + 1;
            }
        }
    }
    return -1;
}