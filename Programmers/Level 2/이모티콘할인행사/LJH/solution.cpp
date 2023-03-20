#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;
vector<pair<int,int>>user;
vector<int>emot;
vector<int>sale;
priority_queue<pair<int,int>,vector<pair<int,int>>,less<pair<int,int>>>pq; //내림차순 pq
queue<pair<int,int>>q;
int maxPlus =0;
int maxCost=0;
int main(){
    
    int n;
    cin>>n;
    emot.resize(n,0);
    for(int i=0; i<n;i++){
        int a, b; //a는 할인률, b는 max cost
        cin>>a>>b;
        user.push_back(make_pair(a,b));        
    }
    for(int i=0; i<n; i++){
        cin>>emot[i]; //이모티콘 가격
    }
}
void sales(){

    if(sale.size()==emot.size()){
        funct();

        return;
    }
    for(int i=10; i<=40; i+=10){ //비율은 10%씩 증가함.
        sale.push_back(i);
        funct(); //10, 20 , 30 , 40으로 돌리낟.
        sale.pop_back();
    }
}
void funct(){
    
    int emotPlus= 0;
    int total =0;
    for(int i=0; i<user.size();i++){
        int s=0;    
        for(int j=0; j<sale.size(); j++){

            if(user[i].first>sale[j]){ //유저 할인률보다 낮으면 사질 못함
                continue;
            }
            s+= emot[j] *( 100-sale[j]) / 100 ;  //이모티콘 가격의 할인비율을 적용해서 더해줌.        
        }
        if(s>user[i].second){ //상한 금액을 넘는다면
            emotPlus++;
        }
        else{ //상한 금액을 안넘는다면
            total+=s; //금액을 더해줌.
        }
    }
    if(maxPlus>emotPlus){ //만약 가입자수가 적다면 되돌아감
        return ;
    }
    else if(maxPlus ==emotPlus && maxCost > total){ //이모티콘 플러스 가입자수가 같은데 코스트가 더 낮다면 되돌아감.
        return;
    }
    //위 조건에 해당하지 않는다면 값을 최신화해줌.
    maxCost=total;
    maxPlus = emotPlus;        
}