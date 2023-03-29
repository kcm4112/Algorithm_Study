#include <iostream>
#include <vector>
#include <limits.h>
using namespace std;
int n,m;
vector<int>a;
vector<int>c;
int dp[101][10001]={0,};
void sort_app();
void funct();
int main(){
    cin>>n>>m;
    a.resize(n+1,0);
    c.resize(n+1,0);
    for(int i=1; i<=n;i++){
        cin>>a[i];
    }
    for(int i=1; i<=n;i++){
        cin>>c[i];
    }
    sort_app(); //비용이 작고 단위메모리가 큰 순서대로 정렬함.
    funct();
    for(int i=0; i<=10000;i++){
        if(m<=dp[n][i]){ //메모리가 충족일때 가장 작은 cost를 반환해야함. 따라서 가장 처음 만나게 되는 i값이 그때의 최소비용일 것이다.
            cout<<i<<endl;
            break;
        }
    }
}
void sort_app(){ //비용이적고 앱크기가 큰 순서대로 정렬한다.
    for(int i=1; i<=n; i++){
        for(int j=i+1; j<=n; j++){
            if(c[i]==0){
                continue;
            }
            if(c[j]==0){ //추가 비용이 0인 앱은 가장 앞으로
                swap(a[i],a[j]);
                swap(c[i],c[j]);
                continue;
            }
            if(a[j]/c[j] > a[i]/c[i]){ //단위메모리가 큰 앱이 앞으로
                swap(a[j],a[i]);
                swap(c[j],c[i]);
            }
            /*
            else if(a[j]/c[j]==a[i]/c[i]){ //만약 같다면. 메모리가 작은게 앞으로.
                if(a[j]<a[i]){ 
                    swap(a[j],a[i]);
                    swap(c[j],c[i]);
                }
            }
            */
        }
    }
}
void funct(){
    for(int i=1; i<=n; i++){
        for(int j=0; j<=10000;j++){
            int memory = a[i], cost = c[i];
            if(j<cost) //현재 단계에서 못담는다.
                dp[i][j]=dp[i-1][j];
            else if(j>=cost)
                dp[i][j]= max(dp[i-1][j],dp[i-1][j-cost]+memory); //무조건 메모리 값이 큰 걸 넣어줘야함. 그리고 cost가 작아야함
        }
    }
}  
