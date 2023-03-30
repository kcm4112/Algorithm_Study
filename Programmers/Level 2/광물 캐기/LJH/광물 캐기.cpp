#include <iostream>
#include <string>
#include <vector>
#include <limits.h>
using namespace std;
int total=INT_MAX;
vector<int> result;
void funct(int idx, vector<int> picks,int tot,vector<string> minerals){
    int temp = 0;
    if((picks[0]==0 && picks[1]==0 && picks[2] ==0)||idx *5>=minerals.size()){ 
        //종료 조건. 곡괭이가 모두 0개이거나, 광물을 모두 다 캔 경우에는 그때의 피로도를 비교해서 최솟값을 저장.
        total=min(tot,total);
        return;
    }
    else{
        for(int i=0; i<3; i++){
            if(picks[i]!=0){ //곡괭이의 개수가 있다면
                picks[i]-=1;
                for(int j=idx*5; j<(idx+1)*5;j++){ //0~4 5    
                    if(j>=minerals.size()){ //만약 광물을 다 캤다면 종료
                        break;
                    }
                    if(i==0){ //다이아 곡괭이
                        temp+=1; //다이아 곡괭이는 어떤 광물을 캐더라도 모두 피로도가 1임.
                    }
                    else if(i==1){ //철 곡괭이
                        if(result[j]==25) //철 곡괭이는 다이아 굉물 빼고는 피로도가 1임.
                            temp+=5;
                        else
                            temp+=1;
                    }
                    else{ //돌 곡괭이는 피로도가 그대로 소요됨.
                        temp+=result[j];
                    }
                }       
                funct(idx+1,picks,tot+temp,minerals);
                temp=0; //재귀를 탈출했다면, 곡괭이를 원상복귀 해주고, temp값을 0으로 초기화
                picks[i]+=1;
            }
        }
    }
}
int solution(vector<int> picks, vector<string> minerals) {
    int answer = 0;
    for(int i=0; i<minerals.size();i++){     
        //광물의 첫글자만 봐도 비교가 되기에, 각 광물에 따라 값을 넣어줌. 비교하기 쉽게 25, 5, 1로 넣어줌. 
        switch(minerals[i][0]){
            case 'd':{
                result.push_back(25);
                break;
            }
            case 'i':{
                result.push_back(5);
                break;
            }
            case 's':{
                result.push_back(1);
                break;
            }
        }
    }
    funct(0,picks,0,minerals);
    answer=total;
    return answer;
}