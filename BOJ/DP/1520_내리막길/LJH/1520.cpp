#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int n,m;
vector<vector<int> >road;
struct cmp{
    bool operator()(pair<int,pair<int,int> >&a,pair<int,pair<int,int> >&b){
        return a.first<b.first;
    }
};
priority_queue< pair<int,pair<int,int> >,vector<pair<int,pair<int,int> > >,cmp>  pq;
int dir[4][2]={{0,1},{0,-1},{1,0},{-1,0}};
int dp[501][501]={0};
void bfs(){
    pq.push(make_pair(road[0][0],make_pair(0,0)));
    dp[0][0]=1;
    while(!pq.empty()){
        int row= pq.top().second.first,col=pq.top().second.second;
        pq.pop();
        for(int i=0; i<4; i++){
            int trow=row+dir[i][0], tcol= col+dir[i][1];
            if(trow<0 || tcol<0 || trow>=n || tcol>=m){
                continue;
            }
            if(road[row][col]>road[trow][tcol]) //높이가 더 낮은 지점으로만 이동함.
            {
                if(dp[trow][tcol]==0) //첫 방문이라면 pq에 넣음.
                {
                    pq.push(make_pair(road[trow][tcol],make_pair(trow,tcol)));
                }                               
                dp[trow][tcol]+=dp[row][col];
            }
        }
    }
}
int main(){
    cin>>n>>m;
    road.resize(n,vector<int>(m,0));
    for(int i=0; i<n; i++){
        for(int j=0; j<m;j++){
            cin>>road[i][j];
        }
    }
    bfs();
    cout<<dp[n-1][m-1]<<endl;
}