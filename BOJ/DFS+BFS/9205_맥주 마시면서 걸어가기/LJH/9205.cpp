/*
9205 실버1 맥주 마시면서 걸어가기
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <math.h>
using namespace std;
int t, n, festivalX, festivalY;
vector<pair<int, int>> market;
queue<pair<int, int>> q;
void bfs(int x, int y);
int main()
{
    cin >> t;
    int homeX, homeY, step; //상근이 집
    for (int i = 0; i < t; i++)
    {
        market.resize(0); // vector초기화
        cin >> n;
        cin >> homeX >> homeY; //상근이 집
        for (int j = 0; j < n; j++)
        {
            int a, b;
            cin >> a >> b;
            market.push_back(make_pair(a, b));
        }
        cin >> festivalX >> festivalY;
        market.push_back(make_pair(festivalX, festivalY));
        bfs(homeX, homeY);
        //q초기화? 해줘야하나?
        while (!q.empty())
        {
            q.pop();
        }
    }
}
void bfs(int x, int y)
{
    q.push(make_pair(x, y)); // 기존 좌표 넣음/

    while (!q.empty())
    {
        auto fX = q.front().first;
        auto fY = q.front().second;
        q.pop();
        if (fX == festivalX && fY == festivalY)
        {
            cout << "happy" << endl;
            return;
        }
        for (int j = 0; j < market.size(); j++) {
            int tempx, tempy;
            tempx = abs(fX - market[j].first);
            tempy = abs(fY - market[j].second);
            if (tempx + tempy <= 1000)//거리가 1000이하면 갈 수 있는 모든 좌표를 입력함.
            {
                q.push(make_pair(market[j].first, market[j].second));
                market.erase(market.begin() + j); //j 지움.
                j--;
            }
            else { //1000이상이면? 가지 못해요

            }
        }
    }
    //q가 완전히 빌 때 까지 도달하지 못하면 sad를 출력함.
    cout << "sad" << endl;
    return;
}