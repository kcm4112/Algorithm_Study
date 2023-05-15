#include <iostream>
#include <vector>
#include <algorithm>
#include <math.h>

using namespace std;
int n;
int answer = 0;
bool V[1000001];
vector<vector<int> >dp;
vector<int> graph[1000001];
void funct(int idx);
int main()
{
    cin >> n; // n은 정점의 개수
    int s, e;
    dp.resize(n+1,vector<int>(2,0));
    for (int i = 0; i < n - 1; i++)
    {
        cin >> s >> e;
        graph[s].push_back(e); // s->e
        graph[e].push_back(s); // e->s
    }

    funct(1);
    answer=min(dp[1][0],dp[1][1]);
    cout<<answer<<endl;
}
void funct(int idx){
    V[idx]=true; //방문처리.
    dp[idx][0]=1; //
    for(int i=0; i<graph[idx].size();i++){
        int next=graph[idx][i];
        if(V[next])
            continue;
        funct(next);
        dp[idx][0]+=min(dp[next][0], dp[next][1]); //idx가 얼리어답터일때. 최소 얼리어답터 수 .
        dp[idx][1]+=dp[next][0]; //idx가 얼리어답터가 아닐때, 최소 얼리어답터 수
    }
}