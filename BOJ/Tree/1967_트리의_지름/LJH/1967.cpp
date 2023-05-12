#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int n;
vector<int>V;
vector<pair<int,int> >node[10001];
int maxVal=-1, maxnode=0;
void dfs(int now, int val);
int main(){
    cin>>n; //정점의 개수
    V.resize(n+1,0);
    int s,e,w;
    for(int i=0; i<n-1; i++){
        cin>>s>>e>>w; //출발,도착,가중치
        node[s].push_back(make_pair(e,w));
        node[e].push_back(make_pair(s,w));
    }
    dfs(1,0); //루트에서 가장 먼 노드를 찾음.
    V.assign(n+1,0);
    maxVal=-1;
    dfs(maxnode,0);
    cout<<maxVal<<endl;
}
void dfs(int now, int val){

    V[now]=1; //방문 처리
    if(maxVal < val){ //최대값이 갱신될 경우
        maxVal=val;
        maxnode=now;
    }
    for(int i=0; i<node[now].size();i++){ //현재 지점에서 갈 수 있는 노드.
        if(V[node[now][i].first]==1) //이미 방문한 경우
            continue;
        dfs(node[now][i].first,val+node[now][i].second);
    }
}