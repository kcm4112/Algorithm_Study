#include <iostream>
#include <vector>
using namespace std;
int n,m;
vector<int>ary;
vector<vector<int> >dp;
bool funct(int start,int end);
int main(){
    cin.tie(NULL);
    ios_base :: sync_with_stdio(false);
    cin>>n;
    ary.resize(n+1,0);
    dp.resize(n+1,vector<int>(n+1,-1));
    for(int i=1; i<=n; i++){
        cin>>ary[i];
    }
    cin>>m;
    int s,e;
    for(int i=0; i<m;i++){
        cin>>s>>e;
        cout<<funct(s,e)<<"\n";
    }
}
bool funct(int start,int end){
    if(dp[start][end]!=-1){ //-1이 아니면 한번 계산을 마친 결과니까 그거 그대로 반환 -> 메모이제이션
        return dp[start][end];
    }
    if(ary[start]!=ary[end]){ //다르면 그 때의 start~end는 펠린드롬이 아님.
        dp[start][end]=0;
        return dp[start][end];
    }
    else{ //같을 경우
        dp[start][end]=1;  //start와 end가 숫자가 같으므로, 1로 선언해주고, 계속해서 탐색한 값을 업데이트 해준다.
        if(start+1<=end-1) //start가 end와 교차되서는 안된다.
        {
            dp[start][end]=dp[start][end]&&funct(start+1,end-1); //깎아서 검사.
        }
        return dp[start][end];
    }
}