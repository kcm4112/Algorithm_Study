/*
실버1
16174 점프왕 쪨리(Large)
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;
int n;
vector<vector<int>> graph;
vector<vector<int>> visit;
queue<pair<int, int>> q;
vector<int> x = { 1, 0 };
vector<int> y = { 0, 1 };
string win = "HaruHaru";
string lose = "Hing";
bool result = false;
void bfs();
int main()
{
    cin >> n;
    graph.resize(n, vector<int>(n, 0)); //입력 행렬 
    visit.resize(n, vector<int>(n, 0)); //방문 행렬
    for (int i = 0; i < n; i++) //행렬 입력받기
    {
        for (int j = 0; j < n; j++)
        {
            cin >> graph[i][j];
        }
    }
    q.push(make_pair(0, 0)); //출발점 queue에 넣기
    visit[0][0] = 1; //출발점 방문 체크
    bfs();
    if (result) //도달할 수 있는 경우
    {
        cout << win;
    }
    else //도달할 수 없는 경우
    {
        cout << lose;
    }
}
void bfs()
{
    while (!q.empty())
    {
        int tempx = q.front().first;
        int tempy = q.front().second;
        q.pop();
        if (tempx == n - 1 && tempy == n - 1) //만약 도착점이라면
        {
            result = true;
            return;
        }

        for (int i = 0; i < 2; i++) //오른쪽 아래,
        {
            int fx = tempx + x[i] * graph[tempx][tempy];
            int fy = tempy + y[i] * graph[tempx][tempy];
            if (fx < 0 || fy < 0 || fx >= n || fy >= n) //적절한 좌표가 아니라면 pass
            {
                continue;
            }
            if (visit[fx][fy] == 0) //이동가능한 좌표고, 만약 방문을 안했다면
            {
                q.push(make_pair(fx, fy)); //queue에 푸쉬하고, 방문처리를 해줌.
                visit[fx][fy] = 1;
            }
        }
    }
}