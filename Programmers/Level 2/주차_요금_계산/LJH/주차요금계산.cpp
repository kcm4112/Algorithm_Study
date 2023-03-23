#include <iostream>
#include <string>
#include <vector>
#include <math.h>
#include <algorithm>
#include <map>
using namespace std;
map<string,string>m;
map<string,int>cc;
vector<pair<string,int> >ans;
int first_t=0,second_t=0;

string get_time(vector<string> records, int step){ //HH:MM인 형식을 HHMM으로 반환하는 함수.
    string temp="";
    for(int i=0; i<=4; i++){
        if(i==2)
            continue;
        temp+=records[step][i];
    }
    return temp;
}
string get_number(vector<string> records,int step){ //차 번호를 반환하는 함수.
    string temp="";
    for(int i=6; i<=9;i++){
        temp+=records[step][i];
    }
    return temp;
}
void funct_time(string first, string second) //출차시간-입차시간을 반환하는 함수.
{
    first_t=0,second_t=0;
    first_t=(first[0]-'0')*600+(first[1]-'0')*60+(first[2]-'0')*10+(first[3]-'0');
    second_t = (second[0]-'0')*600+(second[1]-'0')*60+(second[2]-'0')*10+(second[3]-'0');
    return;
}
vector<int> solution(vector<int> fees, vector<string> records) {
    vector<int> answer;
    for(int i=0; i<records.size();i++){
        string c="",t="";
        c=get_number(records,i);
        t=get_time(records,i);
        if(m.find(c)==m.end()){ //map없다면 처음 입차이기 때문에, 그때의 차 넘버와 들어온 시간을 map에 넣어줌.
            m.insert(make_pair(c,t));
        }
        else //이미 map에 들어와있는 차번호라면 출차이기 때문에
        {    
            string tt = m.find(c)->second; //입차한 시간
            funct_time(tt,t); //출차한 시간 - 입차한 시간을 분단위로 전환함.
            if(cc.find(c)==cc.end()){ //처음으로 출차한 경우는 주차시간을 더해줌.
                cc.insert(make_pair(c,second_t-first_t));
            }
            else{ //처음으로 출차한 경우가 아니라면 누적 주차시간을 넣어줌.
                int total = cc.find(c)->second+second_t-first_t;
                cc[c]=total; //누적 주차시간 업데이트
            }
            m.erase(c); //출차한 경우 맵에서 차 번호를 지워줌.
        }
    }
    for(auto iter=m.begin(); iter!=m.end();iter++){ //만약 입차를 했는데, 출차를 안한 경우를 전부다 처리해줌.
        funct_time(iter->second,"2359"); //입차하고 출차안한경우는 23:59출차로 생각하기 때문에
        if(cc.find(iter->first)!=cc.end())
            cc[iter->first]=cc.find(iter->first)->second+second_t-first_t;
        else
            cc.insert(make_pair(iter->first,second_t-first_t));
    }
    for(auto iter= cc.begin(); iter!=cc.end();iter++){
        if(iter->second<=fees[0]){ //기본시간이하로 주차햇다면 그냥 기본요금을 넣어줌.
            answer.push_back(fees[1]);
        }
        else{ //기본시간 초과라면 초과한 단위시간만큼 추가요금을 부과함.
            int cost=0;    
            cost = fees[1]+ceil(double(iter->second-fees[0])/fees[2])*fees[3]; //
            answer.push_back(cost);
        }
    }
    return answer;
}