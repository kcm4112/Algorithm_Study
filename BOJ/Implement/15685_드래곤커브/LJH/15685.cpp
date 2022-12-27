/*
백준 골드4 드래곤 커브
*/

#include <iostream>
#include <vector>
#include <algorithm>
#include <math.h>
#include <stack>
#include <deque>
#include <queue>
using namespace std;
int n, x, y, d, g;
vector<int> Y = {0, -1, 0, 1}; // 실제로 Y좌표ㅕ
vector<int> X = {1, 0, -1, 0}; // 실제로 X좌표
vector<vector<int>> visit;
stack<int> direct;
deque<int> result;
void make_curve();
void square();
int answer = 0;
int main()
{

    visit.resize(101, vector<int>(101, 0));
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        cin >> x >> y >> d >> g; // x,y는 시작점 d= 방향 g는 세대
        make_curve();
        square();
    }
    for (int i = 0; i < 100; i++)
    {
        for (int j = 0; j < 100; j++)
        {
            if (visit[i][j] == 1 && visit[i + 1][j] == 1 && visit[i + 1][j + 1] == 1 && visit[i][j + 1] == 1)
            {
                answer++;
            }
        }
    }
    cout << answer;
}
void make_curve()
{
    result.push_back(d); //0세대 처리
    for (int i = 1; i <= g; i++) //0세대를 위에서 처리했기에 1세대부터 반복문을 들어감.
    {
        stack<int> temp(result);                // temp에다가 result를 복사함.
        for (int j = 0; j < pow(2, i - 1); j++) // 세대별로 2^(세대-1)만큼 선이 추가됨.
        {
            result.push_back((temp.top() + 1) % 4); // 가장 최근에 방향벡터+1 값을 넣어줌.
            temp.pop();
        }
    }
}
void square()
{
    while (!result.empty())
    {
        int dir = result.front();
        visit[x][y] = 1;
        x += X[dir];
        y += Y[dir];
        // 계속 꼬리를 무는 식으로 좌표를 기록함.
        visit[x][y] = 1;
        // 항상 방문한 좌표는 방문처리를 해줌.
        result.pop_front();
    }
}