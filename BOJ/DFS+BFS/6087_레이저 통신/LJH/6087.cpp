#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <limits.h>
using namespace std;
int w, h;
vector<vector<char> > graph;
vector<pair<int, int> > answer;
queue<pair<pair<int, int>, int> > q;
vector<vector<vector<int> > > v;
void funct();
int Move[4][2] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
int ans = INT_MAX;
int main()
{
    cin >> h >> w;
    graph.resize(w, vector<char>(h, 0));
    v.resize(w, vector<vector<int> >(h, vector<int>(4, INT_MAX))); // w*h*4 배열
    for (int i = 0; i < w; i++)
    {
        for (int j = 0; j < h; j++)
        {
            cin >> graph[i][j];
            if (graph[i][j] == 'C')
                answer.push_back(make_pair(i, j));
        }
    }
    funct();
    cout << ans<< endl;
}
void funct()
{
    q.push(make_pair(make_pair(answer[0].first, answer[0].second), 0)); // 레이저 좌표와 모든 방향을 넣어줌.
    q.push(make_pair(make_pair(answer[0].first, answer[0].second), 1));
    q.push(make_pair(make_pair(answer[0].first, answer[0].second), 2));
    q.push(make_pair(make_pair(answer[0].first, answer[0].second), 3));
    for (int i = 0; i < 4; i++) //초기 출발점은 0으로 세팅한다.
    {
        v[answer[0].first][answer[0].second][i] = 0;
    }
    while (!q.empty())
    {
        auto loc = q.front().first; //x,y 좌표
        int dir = q.front().second; //방향
        if (loc.first == answer[1].first && loc.second == answer[1].second) //도착지점에 도착한다면 최소값을 기록.
        {
            ans=min(ans,v[loc.first][loc.second][dir]);
        }
        q.pop();
        for (int i = 0; i < 4; i++)
        {
            int temprow = loc.first + Move[i][0];
            int tempcol = loc.second + Move[i][1];
            if (temprow < 0 || tempcol < 0 || temprow >= w || tempcol >= h) //배열을 넘어가는 경우
                continue;
            if (graph[temprow][tempcol] == '*') //벽에 막힌 경우
                continue; 
            if (i != dir) //방향이 다를 경우 : 거울을 추가해야함.
            {
                if (v[temprow][tempcol][i] > v[loc.first][loc.second][dir] + 1)
                {
                    v[temprow][tempcol][i] = v[loc.first][loc.second][dir] + 1;
                    q.push(make_pair(make_pair(temprow, tempcol), i));
                }
            }
            else //방향이 같을 경우 : 거울을 추가 안해도 됨.
            {
                if(v[temprow][tempcol][i]>v[loc.first][loc.second][dir])
                {   
                    v[temprow][tempcol][i] = v[loc.first][loc.second][dir];
                    q.push(make_pair(make_pair(temprow, tempcol), i));
                }
            }
        }
    }
}