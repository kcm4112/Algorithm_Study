/*
백준 2573 골드4 빙산
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <stack>
#include <queue>
using namespace std;
int n, m, day = 0, Icenum = 0, answer = 0;
vector<vector<int>> ice;
vector<vector<int>> visit;
vector<vector<int>> fact;
queue<pair<int, int>> q;
vector<int> result;
vector<int> x = { 0, 0, 1, -1 }, y = { 1, -1, 0, 0 };
void bfs();
void checkice(int row, int col);
int main()
{
    cin >> n >> m;
    ice.resize(n, vector<int>(m, 0));
    visit.resize(n, vector<int>(m, 0));
    fact.resize(n, vector<int>(m, 0));
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            int num;
            cin >> num;
            ice[i][j] = num;
            if (num != 0)
            {
                q.push(make_pair(i, j));
                visit[i][j] = 1; //빙산이 있는 곳은 1을 넣어줌.
            }
        }
    }
    bfs();
}
void bfs()
{
    int size = q.size();
    int Size = 0;
    while (q.empty() == 0)
    { //빌떄까지 반복

        int row, col;
        row = q.front().first, col = q.front().second;
        q.pop();
        Size++; //빙산하나 처리했다.
        for (int i = 0; i < 4; i++)
        {
            int dx = row + x[i], dy = col + y[i];
            if (dx < 0 || dx >= n || dy < 0 || dy >= m) //못가는 경우는 continue
            {
                continue;
            }
            if (ice[dx][dy] == 0 && ice[row][col] != 0 && visit[dx][dy] == 0)
            {                    //이동한곳이 바다고, 해당 공간이 빙산일때,visit은 년초에 그 장소가 빙산인지 바다인지를 뜻함.
                ice[row][col]--; //빙산을 깎는다.
            }
        }
        if (ice[row][col] != 0) //바람이 불었는데 빙산이 살아남았다면 큐에 넣어줌.
        {
            q.push(make_pair(row, col));
        }

        if (Size == size) //만약 초기 큐에 있는 빙산의 개수만큼 뺏다면 년차가 바뀌어야함.
        {
            day++;           //년차증가
            size = q.size(); //년차 바뀠으니 변수를 다시 초기화해줌.
            Size = 0;
            // cout << "now year : " << day << endl;
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < m; j++)
                {
                    if (ice[i][j] == 0)
                    {
                        visit[i][j] = 0;
                        continue;
                    }
                    if (fact[i][j] == 0)
                    {
                        checkice(i, j);
                        Icenum++;
                    }
                }
            }
            if (Icenum >= 2)
            {
                answer = day;
                break;
            }
            fact.assign(n, vector<int>(m, 0));
            Icenum = 0;
        }
    }
    cout << answer << endl;
}
void checkice(int row, int col)
{
    if (fact[row][col] == 0)
    { //검사안했다.
        fact[row][col] = 1;
        for (int i = 0; i < 4; i++)
        {
            int dx = row + x[i], dy = col + y[i];
            if (dx < 0 || dx >= n || dy < 0 || dy >= m)
            {
                continue;
            }

            if (ice[dx][dy] != 0 && fact[dx][dy] == 0)
            { //빙산이 있다.
                checkice(dx, dy);
            }
        }
    }
}