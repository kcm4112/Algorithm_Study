#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int T,N,K;

int answer=0;
int main(){
    cin>>T;
    for(int i=0; i<T;i++){
        cin>>N>>K; //n은 건물의 개수, k는 규칙의 개수
        vector<int>D(N+1,0);//건물의 비용
        vector<int>ACM(N+1,0);//건물을 짓는데 드는 최소 비용을 저장하는 배열
        vector<int>vertex(N+1,0);//간선의 개수를 저장하는 배열
        queue<int>q;
        vector<int>build[1001]; //순서를 인접리스트로 저장.
        answer=0;
        for(int j=1; j<=N;j++){
            cin>>D[j];
        }
        for(int x=0; x<K; x++){
            int s,e;
            cin>>s>>e; //s를 지어야 e를 지을 수 있다.
            build[s].push_back(e);
            vertex[e]++; //e로 가는 정점을 하나 추가.
        }
        cin>>answer;
        for(int x=1; x<=N; x++){
            if(vertex[x]==0) //인접하는 간선이 없다는 뜻이죠.
            {
                q.push(x);
                ACM[x]=D[x];
            }
        }
        while(!q.empty()){
            int node=q.front(); //현재 node를 기준
            q.pop();
            for(int i=0; i<build[node].size();i++){
                int next = build[node][i]; //옮기는 지점.
                ACM[next]=max(ACM[next],ACM[node]+D[next]); //같은 단계 내에서 최고의 시간을 저장해야함.
                vertex[next]--; //node->next로 가기에, next를 감소시킴.
                if(vertex[next]==0){ //만약 간선이 하나도 없으면 큐에 넣어줌.
                    q.push(next);
                }
            }
        }
        cout<<ACM[answer]<<"\n";
    }
}