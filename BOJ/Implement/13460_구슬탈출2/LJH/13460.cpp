#include <iostream>
#include <queue>
#include <algorithm>
#include <vector>
using namespace std;
int n, m;
vector<vector<char>> graph;
int x[4] = {1, -1, 0, 0};
int y[4] = {0, 0, 1, -1};
pair<int, int> finish;
int ans = -1;
queue<pair<pair<int, int>, int>> r;
queue<pair<pair<int, int>, int>> b;
int visit[11][11][11][11];
void funct();
void move(int dir, int &row1, int &col1, int &distance);
int main()
{
    ios_base::sync_with_stdio(0);

    cin.tie(0);
    cin >> n >> m;
    int rx, ry, bx, by = 0;
    graph.resize(n, vector<char>(m, 0));
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> graph[i][j];
            switch (graph[i][j])
            {
            case 'O':
            {
                finish.first = i;
                finish.second = j;
                break;
            }
            case 'R':
            {
                rx = i, ry = j;
                break;
            }
            case 'B':
            {
                bx = i, by = j;
                break;
            }
            }
        }
    }
    r.push(make_pair(make_pair(rx, ry), 0));
    b.push(make_pair(make_pair(bx, by), 0));
    funct();
    cout << ans << endl;
}

void funct()
{ // bfs

    while (!r.empty())
    {
        auto red = r.front().first;
        auto blue = b.front().first;
        int cnt = r.front().second;
        if (cnt >= 10)
        {
            ans = -1;
            return;
        }
        r.pop();
        b.pop();
        visit[red.first][red.second][blue.first][blue.second] = 1;
        for (int dir = 0; dir < 4; dir++) // dir 0 -> 아래, 1 -> 위 , 2 -> 오른쪽 , 3->왼쪽
        {
            int r1 = red.first;
            int r2 = blue.first;
            int c1 = red.second;
            int c2 = blue.second;
            int rcheck = 0, bcheck = 0;
            move(dir, r1, c1, rcheck);
            move(dir, r2, c2, bcheck);
            if (graph[r2][c2] == 'O')
            { // 파랑구슬 풍덩
                continue;
            }
            if (graph[r1][c1] == 'O')
            {
                ans = cnt + 1;
                return;
            }
            // 아닐 경우
            if (r1 == r2 && c1 == c2)
            { // 좌표가 같은경우
                // 만약 같은 경우 거리가 덜 간 구슬이 앞에 있어야 겟다. 이말이다.
                if (rcheck > bcheck)
                { // 빨간 구슬이 더 많이 이동했다면 최종 도착지는 파란놈이 먼저 온거
                    r1 -= x[dir], c1 -= y[dir];
                }
                else
                {
                    r2 -= x[dir], c2 -= y[dir];
                }
            }

            if (visit[r1][c1][r2][c2] == 1) // 이미 방문했다면
            {
                continue;
            }
            r.push(make_pair(make_pair(r1, c1), cnt + 1));
            b.push(make_pair(make_pair(r2, c2), cnt + 1));
        }
    }
}
void move(int dir, int &row1, int &col1, int &distance)
{
    while (1)
    {

        if (graph[row1 + x[dir]][col1 + y[dir]] == '#')
        { // 벽을 만나거나. 구멍을 만낫을때는 그만!!!
            break;
        }
        else if (graph[row1 + x[dir]][col1 + y[dir]] == 'O')
        {
            row1 += x[dir], col1 += y[dir];
            distance++;
            break;
        }
        else
        {
            row1 += x[dir], col1 += y[dir];
            distance++;
        }
    }
}
