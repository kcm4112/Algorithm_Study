/*
실버1 레벨 햄버거
*/
#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
long long n, x;
vector<long long>patty;
vector<long long >bread;
vector<long long>burger;
long long eat_patty(long long  level, long long number);
int main()
{
    cin >> n >> x;
    patty.resize(51,0);
    bread.resize(51,0);
    burger.resize(51,0);
    //레벨 원의 햄버거는 패티로만 이루어져 있음.
    patty[0]=1;
    bread[0]=0;
    burger[0]=patty[0]+bread[0]; 
    for(int i=1; i<=n;i++){
        patty[i]=patty[i-1]*2+1; //패티
        bread[i]=bread[i-1]*2+2; //번
        burger[i]=patty[i]+bread[i]; //햄버거 총 길이
    }
    cout<<eat_patty(n,x);
}
long long eat_patty(long long level, long long number){

    if(level==0){ //레벨이 0일 때는 패티로만 이루어져 있음.
        return 1;
    }
    if(number==1){ //level이 0 이 아니고, 먹은 장수가 1개라면 무조건 빵이다.
        return 0;
    }
    else if(number==burger[level-1]+1) //이전 burger + 빵 한개 와 일치할때는 -> 이전 burger에 patty를 가져오면 되겟죵?
    {
        return patty[level-1];
    }
    else if(number<burger[level-1]+1) //이전 burger + 빵 구간일때 이전 burger구간에서 patty를 찾아야함.
    {
        return eat_patty(level-1,number-1);
    }
    else if(number==burger[level-1]+2) //가운데 patty 위치일때.
    {
        return patty[level-1]+1;
    }
    else if(number<=burger[level-1]*2+2) //가운데 페티 ~ 마지막 빵 구간
    {
        return patty[level-1]+1+eat_patty(level-1,number-burger[level-1]-2); //x는 이전 레벨 burger와 빵하나 패티하나를 뺴준 값이 들어가야함.
    }
    else //x가 level 햄버거와 똑같을때
        return patty[level-1]*2+1;

}