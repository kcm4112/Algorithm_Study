/*
골드3 게리멘더링2*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <limits.h>
using namespace std;
int n, sum = 0;
vector<vector<int>> graph;
void standard(int x, int y, int d1, int d2);
int answer = INT_MAX;
void funct(int x, int y);
vector<pair<int, int>> p;
int main()
{
    cin >> n;
    graph.resize(n + 1, vector<int>(n + 1, 0));
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            cin >> graph[i][j];
            sum += graph[i][j];
        }
    }        
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            if (i == 1 | j == 1 || i == n || j == n)
            { // 가로 세로, 끝에는 안댐.
                continue;
            }
            funct(i, j);
        }
    }
    cout << answer << endl;
}
void funct(int x, int y)
{ // 기준점을 잡았으면
    for (int i = 1; i <= n; i++)
    { // d1
        for (int j = 1; j <= n; j++)
        { // d2
            if (x + i + j > n || y - i < 1 || y + j > n)
                continue;
            standard(x, y, i, j);
        }
    }
}
void standard(int x, int y, int d1, int d2)
{
    vector<vector<int>> mark;
    mark.resize(n + 1, vector<int>(n + 1, 5));
    int big = -1, small = INT_MAX;
    vector<int> people(5, 0);
    int cnt = 0;    
    for (int i = 1; i < x + d1; i++) //1번구 
    { 
        if (i >= x)
            cnt++;
        for (int j = 1; j <= y - cnt; j++)
        {           
            mark[i][j] = 1;     
            people[0]+=graph[i][j];                  
        }
    }
    cnt=0;
    for (int i = 1; i <= x + d2; i++) //2번구
    { 
        if (i > x)
            cnt++;
        for (int j = y + cnt+1; j <= n; j++)
        {
            mark[i][j] = 2;    
            people[1]+=graph[i][j];               
        }
    }
    cnt = 0;
    for(int i= n; i>=x+d1; i--){ //3번구
        if(i<x+d2+d1) cnt++;
        for(int j=1; j<y+d2-d1-cnt;j++){
            mark[i][j]=3;
            people[2]+=graph[i][j];
        }
    }
    cnt=0;
    for(int i= n; i>x+d2;i--){ //4번구
        if(i<=x+d2+d1) cnt++;
        for(int j= y+d2-d1+cnt; j<=n; j++){
            mark[i][j]=4;
            people[3]+=graph[i][j];
        }
    }
    int rest=0;
    for(int i=0; i<4; i++){
        rest+=people[i];
    }
    people[4]=sum-rest; //5번구는 나머지 지역
    for (int i = 0; i < people.size(); i++)
    {
        if (people[i] > big)
        {
            big = people[i];
        }
        if (people[i] < small)
        {
            small = people[i];
        }
    }
    answer=min(answer,big-small);
}