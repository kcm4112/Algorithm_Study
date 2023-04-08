#include <iostream>
#include <vector>
#include <tuple>
#include <queue>
#include <algorithm>
#include <limits.h>
using namespace std;
int n, m, k;
int graph[1001][1001];
int v[1001][1001][11][2] = {
    0,
};
int dir[4][2] = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
int ans = INT_MAX;
void funct();
int main()
{
    cin >> n >> m >> k;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            scanf("%1d", &graph[i][j]);
        }
    }
    v[0][0][0][1] = 1;
    funct();
    if (ans == INT_MAX)
        cout << -1 << endl;
    else
        cout << ans << endl;
}
void funct()
{
    queue<tuple<int, int, int, bool>> q;
    q.push(make_tuple(0, 0, 0, 1)); // queue에는 row, col, 벽, 낮/밤 여부가 들어감. 낮은 true, 밤은 false.
    while (!q.empty())
    {
        int r, c, wall;
        bool sun;
        tie(r, c, wall, sun) = q.front();
        q.pop();
        if (r == n - 1 && c == m - 1)
        {
            ans = min(ans, v[r][c][wall][sun]);
            return;
        }
        for (int i = 0; i < 4; i++)
        {
            int nr = r + dir[i][0];
            int nc = c + dir[i][1];
            if (nr < 0 || nc < 0 || nr >= n || nc >= m)
            { // 적절한 이동이 아니죠.
                continue;
            }
            if (v[nr][nc][wall][!sun] != 0)
            { // 처음 방문이 아니라면.
                continue;
            }                                                                                 
            if (graph[nr][nc] == 1 && sun == 1 && wall + 1 <= k && v[nr][nc][wall + 1][!sun] == 0)// 벽이고, 처음방문.
            {
                v[nr][nc][wall + 1][!sun] = v[r][c][wall][sun] + 1;
                q.push(make_tuple(nr, nc, wall + 1, !sun));
            }
            if (graph[nr][nc] == 0 && v[nr][nc][wall][!sun] == 0)
            { // 낮,밤 상관없이 벽이 아니면 이동가능.
                v[nr][nc][wall][!sun] = v[r][c][wall][sun] + 1;
                q.push(make_tuple(nr, nc, wall, !sun));
            }
        }
        if (v[r][c][wall][!sun] == 0) //정지상황
        {
            v[r][c][wall][!sun] = v[r][c][wall][sun] + 1;
            q.push(make_tuple(r, c, wall, !sun));
        }
    }
}