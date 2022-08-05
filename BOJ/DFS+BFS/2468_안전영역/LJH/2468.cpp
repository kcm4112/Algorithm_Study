#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;
int n, Big = 0, answer = 0;
vector<vector<int>> graph;
vector<vector<int>> visit; // 0은 잠긴곳. -1은 안잠긴곳. 1은 방문한곳.
queue<pair<int, int>> q;
vector<int> x = { 1, -1, 0, 0 };
vector<int> y = { 0, 0, 1, -1 };
void bfs();
void checkrain(int row, int high);
int block = 0;
int main()
{
    cin >> n;
    graph.resize(n, vector<int>(n, 0));
    visit.resize(n, vector<int>(n, -1));
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            int num = 0;
            cin >> num;
            graph[i][j] = num;
            if (num > Big)
            {
                Big = num;
            }
        }
    }
    int high = 0;
    while (high <= Big)
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {

                if (graph[i][j] <= high)
                {
                    visit[i][j] = 0; //잠긴곳은 0
                }
                else
                {
                    q.push(make_pair(i, j));
                }
            }
        }
        //큐에 다 넣기.
        bfs();
        if (block < answer)
        {
            block = answer;
        }
        high++;
        answer = 0;
        visit.assign(n, vector<int>(n, -1));
    }
    cout << block;
}
void bfs()
{

    while (!q.empty())
    {
        int Row = q.front().first, Col = q.front().second;
        q.pop();
        if (visit[Row][Col] != 1) //만약 큐에서 추출한 원소가 방문을 이미 했다면 체크해줄필요가없음.
        {
            checkrain(Row, Col);
            answer++;
        }
    }
}
void checkrain(int row, int col)
{
    if (visit[row][col] == 1)
    {
        return;
    }
    visit[row][col] = 1;
    for (int i = 0; i < 4; i++)
    {
        int dx = row + x[i], dy = col + y[i];
        if (dx < 0 || dx >= n || dy < 0 || dy >= n || visit[dx][dy] == 1 || visit[dx][dy] == 0)
        {
            continue;
        }
        checkrain(dx, dy);
    }
}