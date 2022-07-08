/*
행렬을 뒤집어서 출발점을 0,0 -> N,M으로 하되 구멍뚫린 도로의 위치를 잘 특정해서 넣어줘야함.
구멍 좌표는 x,y를 서로 바꿔주면됨.
다리 개수 정하기
*/
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
long long int n, m, T, finishx, finishy, startx, starty;
int main()
{
    cin >> n >> m;
    int road[m + 1][n + 1][2];
    long long int DP[m + 1][n + 1]; //도로의 정보를 저장.
    for (int i = 0; i <= m; i++)
    {
        for (int j = 0; j <= n; j++)
        {
            DP[i][j] = 0;
            for (int k = 0; k < 2; k++)
            {
                road[i][j][k] = 0;
            }
        }
    }
    // location(finishx, finishy);                             //도착점을 설정
    // startx = n, starty = 0;                                 //출발점을 설정
    cin >> T;
    for (int i = 0; i < T; i++)
    {
        int a, b, c, d;
        cin >> a >> b >> c >> d;
        //행렬을 회전시킬거임 그러면 x,y 좌표는 반대가 되용.
        //(a,b)->(c,d)
        // DP에서 3차원 좌표상 0은 막힌 곳 없다. 1은 왼쪽으로 막힘. 2는 아래로 가는 길 막힘.
        if (a == c)
        { // x좌표가 같다면 아래으로 가는 길 막힘
            if (b > d)
                road[b][c][1] = -1;
            else
                road[d][c][1] = -1;
            // cout<<"아래  "<<d<<c<<endl;
        }
        if (b == d)
        { // y좌표가 같다면 왼쪽로 가는 길 막힘
            if (a > c)
                road[d][a][0] = -1;
            else
                road[d][c][0] = -1;
            //cout<<"왼 "<<d<<c<<endl;
        }
        // cout<<d<<" "<<c<<endl;
        // location(a, b); //
        // location(c, d);
    }

    // DP[i][j] =DP[i-1][j]+DP[i][j-1]이 최소의 경로겟지?
    //근데 만약 그 값보다 크다면? 그때는 최적의 경로가 아니야
    DP[0][0] = 1;
    for (int i = 0; i <= m; i++)
    {
        for (int j = 0; j <= n; j++)
        {
            if (i - 1 >= 0 && road[i][j][1] != -1) //아래로 가는 길안막혀잇을떄)
            {
                DP[i][j] = DP[i][j] + DP[i - 1][j];
            }
            if (j - 1 >= 0 && road[i][j][0] != -1) //왼쪽 안막혀잇을때)
            {
                DP[i][j] = DP[i][j] + DP[i][j - 1];
            }
        }
    }
    // int x,y;
    // cin>>x>>y;
    // cout << DP[x][y];
    cout << DP[m][n];
}