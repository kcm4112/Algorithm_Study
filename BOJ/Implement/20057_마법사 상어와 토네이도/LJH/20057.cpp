/*
백준 골드4 20057 마법사 상어와 토네이도
*/
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int n;
vector<vector<int>> g;
vector<int> x{0, 1, 0, -1};
vector<int> y{-1, 0, 1, 0};
vector<double> z{0.01, 0.07, 0.1, 0.02, 0.05};
int out = 0, step = 0, dist = 1;
void location(int a, int b, int temp, int k);
int main()
{
    cin >> n;
    g.resize(n, vector<int>(n, 0));
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> g[i][j];
        }
    }
    int nowR = n / 2, nowC = n / 2, afterR, afterC, move = 0;
    bool check = true;
    while (check)
    {                                 // 1,1까지 이동한 뒤 소멸해야함.
        int csand = g[nowR][nowC];    //현재 좌표에서 모래
        afterR = nowR + x[move];      //이동한 x좌표.
        afterC = nowC + y[move];      //이동한 y좌표
        int temp = g[afterR][afterC]; //이동한 자리에 있던 모래
        g[afterR][afterC] = csand; //이동한 자리에 있던 모래를 기존의 모래로 채운다.
        int sum = 0; //모래의 양
        if (move == 0)
        { //왼쪽으로 1칸
            step += 1;
            for (int k = 0; k < 3; k++)
            {
                location(nowR - 1, nowC - k, temp, k);
                location(nowR + 1, nowC - k, temp, k);
                sum += temp * z[k];
                sum += temp * z[k];
            }
            location(afterR + 2, afterC, temp, 3);
            location(afterR - 2, afterC, temp, 3);
            location(nowR + 3 * x[move], nowC + 3 * y[move], temp, 4); // a + 진행방향
            sum += temp * z[3];
            sum += temp * z[3];
            sum += temp * z[4];
            // sum에는 이제 다 분배된 모래가 들어있음. 이제 나머지는 알파에 넣어야함.
            if (afterR + x[move] < 0 || afterR + x[move] >= n || afterC + y[move] < 0 || afterC + y[move] >= n)
            {
                out += temp - sum;
            }
            else
                g[afterR + x[move]][afterC + y[move]] += temp - sum;
        }
        else if (move == 1)
        { //아래로 한칸
            step += 1;
            for (int k = 0; k < 3; k++)
            {
                location(nowR + k, nowC - 1, temp, k);
                location(nowR + k, nowC + 1, temp, k);
                sum += temp * z[k];
                sum += temp * z[k];
            }

            location(afterR, afterC + 2, temp, 3);
            location(afterR, afterC - 2, temp, 3);
            location(nowR + 3 * x[move], nowC + 3 * y[move], temp, 4);
            sum += temp * z[3];
            sum += temp * z[3];
            sum += temp * z[4];
            // sum에는 이제 다 분배된 모래가 들어있음. 이제 나머지는 알파에 넣어야함.
            if (afterR + x[move] < 0 || afterR + x[move] >= n || afterC + y[move] < 0 || afterC + y[move] >= n)
            {
                out += temp - sum;
            }
            else
                g[afterR + x[move]][afterC + y[move]] += temp - sum;
        }
        else if (move == 2)
        { // right
            step += 1;
            for (int k = 0; k < 3; k++)
            {
                location(nowR - 1, nowC + k, temp, k);
                location(nowR + 1, nowC + k, temp, k);
                sum += temp * z[k];
                sum += temp * z[k];
            }
            location(afterR + 2, afterC, temp, 3);
            location(afterR - 2, afterC, temp, 3);
            location(nowR + 3 * x[move], nowC + 3 * y[move], temp, 4);
            sum += temp * z[3];
            sum += temp * z[3];
            sum += temp * z[4];
            // sum에는 이제 다 분배된 모래가 들어있음. 이제 나머지는 알파에 넣어야함.
            if (afterR + x[move] < 0 || afterR + x[move] >= n || afterC + y[move] < 0 || afterC + y[move] >= n)
            {
                out += temp - sum;
            }
            else
                g[afterR + x[move]][afterC + y[move]] += temp - sum;
        }
        else if (move == 3)
        { // up
            step += 1;
            for (int k = 0; k < 3; k++)
            {
                location(nowR - k, nowC + 1, temp, k);
                location(nowR - k, nowC - 1, temp, k);
                sum += temp * z[k];
                sum += temp * z[k];
            }
            location(afterR, afterC + 2, temp, 3);
            location(afterR, afterC - 2, temp, 3);
            location(nowR + 3 * x[move], nowC + 3 * y[move], temp, 4);
            sum += temp * z[3];
            sum += temp * z[3];
            sum += temp * z[4];
            // sum에는 이제 다 분배된 모래가 들어있음. 이제 나머지는 알파에 넣어야함.
            if (afterR + x[move] < 0 || afterR + x[move] >= n || afterC + y[move] < 0 || afterC + y[move] >= n)
            {
                out += temp - sum;
            }
            else
                g[afterR + x[move]][afterC + y[move]] += temp - sum;
        }
        
        //다 이동하면 이제 현재 좌표를 옮겨준다.
        nowR = afterR, nowC = afterC;
        if (nowR == 0 && nowC == 0) //만약 탈출 지점까지 왔다면 while문을 탈출해야함.
        {
            break;
        }
        if (move % 2 == 1 && step == dist) //방향이 2번 바뀔때마다 거리를 1씩 증가시켜줘야함.
        {
            dist = dist + 1; // dist만큼 방향을 전진 시켜야함.
            move = (move + 1) % 4;
            step = 0;
        }
        if (step == dist)
        {
            move = (move + 1) % 4;
            step = 0;
        }
    }
    cout << out; //빠져나간 모래의 양
}
void location(int a, int b, int temp, int k)
{
    if (a < 0 || a >= n || b < 0 || b >= n) //좌표가 이상하다면
        out += temp * z[k];
    else
        g[a][b] += temp * z[k];
}
