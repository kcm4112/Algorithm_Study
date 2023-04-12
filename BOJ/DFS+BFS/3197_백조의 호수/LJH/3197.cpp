#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <stack>
using namespace std;
vector<vector<char>> graph;
queue<pair<int, int>> q, water;
vector<pair<int, int>> lis;
int dir[4][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
int w, h,day=0;
void melt_ice();
void funct();
int main()
{
    cin >> w >> h;
    graph.resize(w, vector<char>(h, 0));
    for (int i = 0; i < w; i++)
    {
        for (int j = 0; j < h; j++)
        {
            cin >> graph[i][j];
            if (graph[i][j] == 'L')
                lis.push_back(make_pair(i, j));
            if (graph[i][j] != 'X')
                water.push(make_pair(i, j));
        }
    }
    funct();
}
void funct()
{
    vector<vector<int> >v;
    v.assign(w, vector<int>(h, 0));
    q.push(make_pair(lis[0].first, lis[0].second)); //처음 백조 위치로 탐색 시작
    v[lis[0].first][lis[0].second] = 1;
    while (1)
    {
        queue<pair<int, int>> q_2; //새로운 백조의 위치를 담을 큐
        // 백조 탐색.
        while (!q.empty())
        {
            int row = q.front().first;
            int col = q.front().second;
            q.pop();
            for (int i = 0; i < 4; i++) //백조 움직이기.
            {
                int temprow = row + dir[i][0], tempcol = col + dir[i][1];
                if (temprow < 0 || tempcol < 0 || temprow >= w || tempcol >= h)  //적절한 이동이 아니라면 
                    continue;
                if (v[temprow][tempcol] != 0) // 처음 방문한 것이 아니라면, 넘어감.
                    continue;
                if (graph[temprow][tempcol] == 'L')
                { // 만약 이동한 곳이 백조라면 종료.
                    cout << day << endl;
                    return;
                }
                if (graph[temprow][tempcol] == 'X')
                    q_2.push(make_pair(row,col));
                if (graph[temprow][tempcol] == '.') // 물이라면
                {
                    q.push(make_pair(temprow, tempcol));
                    v[temprow][tempcol] = 1;
                }
            }
        }
        melt_ice(); // 얼음을 녹임.
        q = q_2; //백조가 가다가 막힌 지점까지 백조의 위치를 옮겨줌.
        day++; //날짜가 지났으므로, 날짜를 증가시켜줌.
    }
}
void melt_ice()
{
    queue<pair<int, int>> water_2;
    while (!water.empty())
    {
        int r = water.front().first;
        int c = water.front().second;
        bool flag = true;
        water.pop();
        for (int i = 0; i < 4; i++)
        {
            int tempr = r + dir[i][0], tempc = c + dir[i][1];
            if (tempr < 0 || tempc < 0 || tempr >= w || tempc >= h)
                continue;
            if (graph[tempr][tempc] == 'X') // 얼음을 만난다면 녹임
            { 
                graph[tempr][tempc] = '.';
                water_2.push(make_pair(tempr, tempc));
            }
        }
    }
    water = water_2; //새로운 물의 위치를 옮겨줌.
}