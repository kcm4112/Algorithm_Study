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
void bfs();
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
            q.push(make_pair(i, j));
        }
    }
    while (1)
    {
        bfs();
        if (flag == true)
        {
            d++;
        }
        else
            break;
    }
    cout << d << endl;
}
void bfs()
{
    int Size = q.size();
    flag = false;
    cnt = 0;
    sum = 0;
    while (Size != 0)
    {
        auto a = q.front(); // a.firts에는 row, a.second에는 col이 저장됨.

        q.pop();
        q.push(make_pair(a.first, a.second));
        Size--;
        for (int i = 0; i < 4; i++)
        {
            int mx = a.first + x[i], my = a.second + y[i];
            if (mx < 0 || mx >= n || my < 0 || my >= n)
            {
                continue;
            }
            if (abs(ary[a.first][a.second] - ary[mx][my]) >= L && abs(ary[a.first][a.second] - ary[mx][my]) <= R)
            {
                //인구수가 l~r 사이라면 국경을 열어야함.
                flag = true;
                if (visit[a.first][a.second] == 0)
                {
                    visit[a.first][a.second] = 1; //국경선을 하나라도 열었으면
                }
            }
        }
        //한 나라에 대한 국경선 열기 끝.
    }
    if (flag == true)
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (visit[i][j] == 1)
                {
                    m.insert(make_pair(make_pair(i, j), 0));
                    sum += ary[i][j];
                    path(i, j);
                    int SSS = m.size();
                    int people = sum / SSS;
                    for (auto iter = m.begin(); iter != m.end(); iter++)
                    {
                        ary[iter->first.first][iter->first.second] = people;
                    }
                    sum = 0;
                    m.clear();
                }
            }
        }
    }
    visit.assign(n, vector<int>(n, 0));
}
void path(int r, int c)
{
    if (visit[r][c] == -1)
    {
        return;
    }
    visit[r][c] = -1;
    for (int i = 0; i < 4; i++)
    {
        int dx = r + x[i], dy = c + y[i];
        if (dx < 0 || dx >= n || dy < 0 || dy >= n)
        {
            continue;
        }
        if (abs(ary[r][c] - ary[dx][dy]) < L || abs(ary[r][c] - ary[dx][dy]) > R) {
            continue;
        }
        if (visit[dx][dy] == 1)
        {
            m.insert(make_pair(make_pair(dx, dy), 0));
            sum += ary[dx][dy];
            path(dx, dy);
        }
    }
}