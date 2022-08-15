/*
백준 골드5 백도어
시간초과 나서 인접행렬 말고 인접리스트로 풀어야할듯.
우선순위큐를 이용해서 거리가 작은 순으로 queue에 넣기.
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;
#define INF 30000000001
int n, m;
vector<long long> ward;
vector<long long> d;
int answer = 0;
void back(int start);
int smallIdx(int start);
bool flag = 0;
int main()
{
    cin >> n >> m;
    vector<pair<int, long long>> p[n];
    ward.resize(n, 0), d.resize(n, INF);
    for (int i = 0; i < n; i++)
    {
        cin >> ward[i];
    }
    ward[n - 1] = 0; //마지막 도착지는 와드에 어쩔수 없이 보이기 때문에 0으로 바꿔줘야함.
    for (int i = 0; i < m; i++)
    {
        int s, f, v;
        cin >> s >> f >> v;
        p[s].push_back(make_pair(f, v));
        p[f].push_back(make_pair(s, v));
        //양방향이기에 둘 다 넣어줌.
    }
    priority_queue<pair<long long, int>, vector<pair<long long, int>>, greater<pair<long long, int>>> PQ;
    //PQ에는 (value,도착지)가 들어감.
    PQ.push(make_pair(0, 0));
    while (0 != PQ.size())
    {
        pair<long long, int> pa = PQ.top(); // p에다가 PQ에 top을 저장함. (value,도착지)
        PQ.pop();
        //나는 pq에 도착지와 거리를 넣고싶음.
        if (pa.first > d[pa.second]) //도착지까지의 거리인 pa.first 와 거리테이블을 d[pa.second]를 비교함.
        {
            //만약 거리테이블보다 크다면 탐색할필요가 없음.
            continue;
        }
        for (auto a : p[pa.second]) //현재 갈 수 있는 모든지역을 탐색함.
        {    //p에는 (도착지,value)가 저장됨.
            if (ward[a.first] == 1) //도착지가 와드시야에 보인다면 넘겨준다.
            {
                continue;
            }
            pair<long long, int> next = { a.second + pa.first, a.first }; //next에는 (도착지까지의 거리,도착지)가 저장됨.
            if (next.first < d[a.first]) //현재 입력된 거리보다 거리테이블의 거리가 크다면 최신화해줌.
            {
                d[next.second] = next.first; //거리테이블을 최신화해줌.
                PQ.push(next); //PQ에 넣어줌.
            }
        }
    }
    if (d[n - 1] == INF) //만약 넥서스까지의 거리가 INF면 도달 못한다는 뜻.
    {
        cout << -1;
    }
    else
        cout << d[n - 1];
}