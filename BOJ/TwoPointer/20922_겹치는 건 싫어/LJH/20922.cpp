#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int n,k;
vector<int>ary;
vector<int>v;
int main(){
    cin>>n>>k;
    ary.resize(n,0);
    v.resize(100001,0);
    for(int i=0; i<n;i++){
        cin>>ary[i];
    }
    int start=0, end=0,result=0;
    while(end<n){
        if(v[ary[end]]==k){ //방문한 원소가 k개 초과일경우
            v[ary[start]]--;
            start++;
        }
        else{
            v[ary[end]]++;
            end++;
        }
        result=max(result,end-start);
    }
    cout<<result<<endl;
}