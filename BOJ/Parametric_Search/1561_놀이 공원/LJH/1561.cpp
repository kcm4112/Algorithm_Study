#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
long long n, m;
vector<int> play;
int main()
{
    cin >> n >> m;
    play.resize(m, 0);
    for (int i = 0; i < m; i++)
    {
        cin >> play[i]; // 운행시간을 뜻합니다.
    }
    if (n <=m)
    {
        cout << n << endl;
    }
    else
    {
        long long start = 0,mid=0 ,end = n*30; //최대 운행시간은 n명이고 1개의 놀이기구가 있을때 그 놀이기구 30분 일경우
        long long tt,time=n*30;
        while (1)
        {
            if(start>=end){
                break;
            }
            mid=(start+end)/2; //여기서 mid, start, end는 모두 시간을 의미함.
            tt = 0; //mid 시간 내에 놀이기구에 탑승할 수 있는 학생
            for(int i=0; i<m; i++){
                tt+=(mid/play[i])+1; 
                //+1을 해주는 이유는 예를 들어 5초시간대에는 운행시간이 3초인 놀이기구에는 2명이 탔는데 몫은 1이기에 1을 더해줌,
            }
            if(tt<n){ //전체 인원수보다 작다면.. 탐색하는 시간대를 늘려줘야함.
                start=mid+1;
            }
            else{ //이 경우는 모든 꼬맹이들을 다 태운 경우임.                
                time=min(time,mid); //다 태웠다면 더 낮은 시간대를 갱신함.
                end=mid;
            }
        }
        //time이 모든 아이들이 놀이기구를 탄 최소의 시간
        long long child=0;
        for(int i=0; i<m;i++){ //time-1까지 진행해서 탈 수 있는 아이들의 수를 계산함.
            child+=((time-1)/play[i])+1;
        }
        for(int i=0; i<m;i++){
            if(time%play[i]==0){ //time시간대에 탈 수 있는 놀이기구가 있다면 아이들을 탑승시킴.
                child++;
            }
            if(child==n){ //만약 아이들의 수가 n이라면 그때 마지막 탑승이기에 놀이기구의 번호를 출력함.
                cout<<i+1<<endl;
                return 0;
            }
        }
    }
}