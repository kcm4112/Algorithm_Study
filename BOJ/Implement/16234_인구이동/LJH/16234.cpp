/*
백준 골드5 인구이동
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <map>
using namespace std;
int n, L, R, d = 0, cnt, sum = 0;
#define INF 100000
vector<vector<int>> ary;
vector<int> x = { 1, -1, 0, 0 }, y = { 0, 0, 1, -1 };
queue<pair<int, int>> q;
vector<vector<int>> visit;
bool flag;
void bfs(int r, int c);
void path(int r, int c);
map<pair<int, int>, int> m;
int main()
{
    ios_base::sync_with_stdio(false), cin.tie(0), cout.tie(0);
    cin >> n >> L >> R;
    ary.resize(n, vector<int>(n, 0));
    visit.resize(n, vector<int>(n, 0));
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> ary[i][j];
        }
    }
    while (1)
    {
        flag = false;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (visit[i][j] == 0)
                {
                    m.insert(make_pair(make_pair(i, j), 0));
                    bfs(i, j);
                    sum = 0, cnt = 0;
                }
            }
        }
        visit.assign(n, vector<int>(n, 0));
        if (flag == true) {
            d++;
        }
        else
            break;
    }
    cout << d << endl;
}
void bfs(int i, int j)
{
    q.push(make_pair(i, j));
    visit[i][j] = 1;
    while (!q.empty())
    {
        auto a = q.front(); // a.firts에는 row, a.second에는 col이 저장됨.
        q.pop();
        sum = sum + ary[a.first][a.second];
        cnt = cnt + 1;
        for (int i = 0; i < 4; i++)
        {
            int mx = a.first + x[i], my = a.second + y[i];
            if (mx < 0 || mx >= n || my < 0 || my >= n)
            {
                continue;
            }
            if (visit[mx][my] != 0)
            {
                continue;
            }
            if (abs(ary[a.first][a.second] - ary[mx][my]) >= L && abs(ary[a.first][a.second] - ary[mx][my]) <= R)
            {
                //인구수가 l~r 사이라면 국경을 열어야함.
                q.push(make_pair(mx, my));
                m.insert(make_pair(make_pair(mx, my), 0));
                visit[mx][my] = 1;
                flag = true;
            }
        }
    }
    if (flag == true)
    {
        int people = sum / cnt;
        for (auto iter = m.begin(); iter != m.end(); iter++)
        {
            ary[iter->first.first][iter->first.second] = people;
        }
    }
    m.clear();
}