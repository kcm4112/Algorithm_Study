#include <iostream>
#include <vector>
#include <algorithm>
#include <limits.h>
using namespace std;
int n, m;
vector<vector<vector<int> > >dp;
vector<vector<int> > graph;
void funct();
int main()
{
    cin >> n >> m;
    graph.resize(n, vector<int>(m, 0));
    dp.resize(n,vector<vector<int> >(m,vector<int>(3,INT_MAX))); //0은 
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> graph[i][j];
            for(int k=0; k<3; k++){
                dp[i][j][k]=graph[i][j];
            }
        }
    }
    funct();
    int answer= INT_MAX;
    for(int i=0; i<m; i++){
        for(int j=0; j<3; j++){
            if(answer>dp[n-1][i][j]){
                answer=dp[n-1][i][j];
            }
        }
    }
    cout<<answer<<endl;
}
void funct(){
    for(int i=0; i<n; i++){
        for(int j=0; j<m;j++){
            if(i==0){
                for(int k=0; k<3; k++){
                    dp[i][j][k]=graph[i][j];
                }
                continue;
            }
            //왼쪽 끝단
            if(j==0){
                dp[i][j][0]= graph[i][j]+min(dp[i-1][j+1][1],dp[i-1][j+1][2]);
                dp[i][j][1]= graph[i][j]+min(dp[i-1][j][0],dp[i-1][j][2]);
                dp[i][j][2]=INT_MAX;
            }
            else if(j==m-1){ //오른쪽 끝단
                dp[i][j][1]= graph[i][j]+min(dp[i-1][j][0],dp[i-1][j][2]);
                dp[i][j][2]= graph[i][j]+min(dp[i-1][j-1][0],dp[i-1][j-1][1]);
                dp[i][j][0]=INT_MAX;
            }
            else{
                dp[i][j][0] = graph[i][j]+min(dp[i-1][j+1][1],dp[i-1][j+1][2]);
                dp[i][j][1] = graph[i][j] + min(dp[i-1][j][0],dp[i-1][j][2]);
                dp[i][j][2]=graph[i][j]+min(dp[i-1][j-1][0],dp[i-1][j-1][1]);
            }
        }
    }
}