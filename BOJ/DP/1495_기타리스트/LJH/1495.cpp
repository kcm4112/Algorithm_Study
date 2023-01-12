/*
실버1 기타리스트
*/
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n, s, m;
vector<int> volume;
vector<vector<int>> DP;
int main()
{
    int Max = -1;
    cin >> n >> s >> m;
    volume.resize(n + 1, 0);
    DP.resize(n + 1, vector<int>(m + 1, 0));
    for (int i = 1; i <= n; i++)
    {
        cin >> volume[i];
    }
    DP[0][s] = 1; //시작볼륨 s는 만들 수 있어야함.
    for (int i = 1; i <= n; i++)
    {
        for (int j = 0; j <= m; j++)
        {
            if (DP[i - 1][j] == 1) //i-1번째 곡에서 j 크기의 볼륨을 만들 수 있을 때
            {              
                if (j + volume[i] <= m)
                {                 
                    DP[i][j + volume[i]] = 1;
                }
                if (j - volume[i] >= 0)
                {                   
                    DP[i][j - volume[i]] = 1;
                }
            }
        }
    }
    for(int i=0; i<=m;i++){    
        if(DP[n][i]==1){
            Max=max(Max,i);
        }
    }  
    cout<<Max;  
    
}