#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <limits.h>
using namespace std;
vector<vector<int>> graph;
vector<vector<vector<int>>> v;
queue<pair<pair<int, int>, int>> q; // x,y 좌표 , 말 처럼 움직인 횟수.
int k, w, h = 0;
int hmove[8][2] = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
int Mmove[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
void funct();
int answer = INT_MAX;
int main()
{
    cin >> k;
    cin >> h >> w;
    graph.resize(w, vector<int>(h, 0));
    v.resize(w, vector<vector<int>>(h, vector<int>(k + 1, INT_MAX)));
    for (int i = 0; i < w; i++)
    {
        for (int j = 0; j < h; j++)
        {
            cin >> graph[i][j];
        }
    }
    funct();
    for (int i = 0; i <= k; i++)
    {
        answer = min(answer, v[w - 1][h - 1][i]);
    }
    if (answer == INT_MAX)
        cout << -1 << endl;
    else
        cout << answer << endl;
}
void funct()
{

    q.push(make_pair(make_pair(0, 0), 0));
    v[0][0][0] = 0;
    while (!q.empty())
    {
        auto loc = q.front().first; //(row, col)
        int lim = q.front().second; //말처럼 이동한 횟수
        q.pop();
        if (lim + 1 <= k)
        { // 만약 k번 움직였다면 말의 움직임은 할 수 없음.
            for (int i = 0; i < 8; i++)
            {
                int temprow = loc.first + hmove[i][0];
                int tempcol = loc.second + hmove[i][1];
                if (temprow < 0 || temprow >= w || tempcol < 0 || tempcol >= h || graph[temprow][tempcol] == 1)
                { // 적절한 이동이 아니라면(배열을 넘어가거나, 장애물을 밟을 경우)
                    continue;
                }
                if (v[temprow][tempcol][lim + 1] != INT_MAX) //처음 방문한것이 아니라면. 
                {
                    continue;
                }
                q.push(make_pair(make_pair(temprow, tempcol), lim + 1));
                v[temprow][tempcol][lim + 1] = v[loc.first][loc.second][lim] + 1; //이전 좌표 이동횟수 + 1
            }
        }
        for (int i = 0; i < 4; i++)
        {
            int temprow = loc.first + Mmove[i][0];
            int tempcol = loc.second + Mmove[i][1];
            if (temprow < 0 || temprow >= w || tempcol < 0 || tempcol >= h || graph[temprow][tempcol] == 1)
            { // 적절한 이동이 아니라면(배열을 넘어가거나, 장애물을 밟을 경우)
                continue;
            }
            if (v[temprow][tempcol][lim] != INT_MAX) //처음 방문이 아니라면.
            {
                continue;
            }
            q.push(make_pair(make_pair(temprow, tempcol), lim));
            v[temprow][tempcol][lim] = v[loc.first][loc.second][lim] + 1; //이전 좌표 이동횟수 +1
        }
    }
}