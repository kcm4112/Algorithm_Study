/*
백준 11000 골드5 강의실 배정
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;
int n;
priority_queue<int, vector<int>, greater<int>>pq; //강의실에서 수업이 끝나는 시간을 오름차순으로 저장.
vector<pair<int, int>>Time; //시작 시간과 끝나는 시간을 저장하는 배열
int main()
{
    cin >> n; // n개의 수업
    for (int i = 0; i < n; i++)
    {
        int start, finish;
        cin >> start >> finish;
        Time.push_back(make_pair(start, finish));
    }
    sort(Time.begin(), Time.end()); //시작시간과 끝나는 시간중에서 시작시간을 기준으로 오름차순 정렬
    pq.push(Time[0].second); //가장 먼저 시작되는 수업을 일단 강의실에 배정함.
    
    for (int i = 1; i < Time.size(); i++) {
        bool flag = true;
        if (pq.top() <= Time[i].first) { //제일 빨리 끝나는 강의실이 해당 수업보다 일찍 끝난다면 바로 강의실 배정해줌.
            //기존 강의실을 배정핻줌.
            pq.pop();
            pq.push(Time[i].second);
            flag = false;
        }
        if (flag == true) { //만약 제일 빨리 끝나는 강의실이 
            pq.push(Time[i].second);
        }
    }
    cout << pq.size();
}