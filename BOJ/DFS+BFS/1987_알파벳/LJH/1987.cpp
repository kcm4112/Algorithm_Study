/*
골드4 1987 알파벳
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <stack>
using namespace std;
int r, c;
vector<vector<char>> graph;
vector<vector<int>> visit;
vector<int> alph(26, 0);
vector<int> x = { 1, -1, 0, 0 };
vector<int> y = { 0, 0, 1, -1 };
stack<pair<int, int>> s;
int C = 1, result = 0;
void dfs(int count);
int main()
{
    cin >> r >> c;
    graph.resize(r, vector<char>(c, 0)); //입력행렬
    visit.resize(r, vector<int>(c, 0)); //방문행렬
    for (int i = 0; i < r; i++)
    {
        for (int j = 0; j < c; j++)
        {
            cin >> graph[i][j];
        }
    }
    s.push(make_pair(0, 0)); //출발점
    dfs(C);
    cout << result; //최대값 출력하기
}
void dfs(int count)
{
    result = max(result, count); //최대값 계속해서 갱신
    while (!s.empty())
    {
        int fx = s.top().first;
        int fy = s.top().second;
        s.pop();
        visit[fx][fy] = 1;            //현재 지점 방문처리
        alph[graph[fx][fy] - 65] = 1; //현재 지점 방문처리

        for (int i = 0; i < 4; i++)
        {
            int mx = fx + x[i];
            int my = fy + y[i];
            if (mx < 0 || my < 0 || mx >= r || my >= c) //적절하지 않은 이동
            {
                continue;
            }
            if (alph[graph[mx][my] - 65] == 1 || visit[mx][my] == 1) //한번 방문한 알파벳이거나, 이미 방문한 지역이라면
            {
                continue;
            }
            alph[graph[mx][my] - 65] = 1; //방문한곳 알파벳 기록
            s.push(make_pair(mx, my)); //stack에 push
            dfs(count + 1);
            //dfs 빠져나왓다면 다시 초기화 시켜주기
            visit[mx][my] = 0;            //이동시킨점 다시 되돌리기
            alph[graph[mx][my] - 65] = 0; //이동시킨점 다시 되돌리기.
        }
    }
}