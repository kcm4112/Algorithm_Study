/*
백준 골드5 백도어
시간초과 나서 인접행렬 말고 인접리스트로 풀어야할듯.
*/
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
#define INF 1316134911
int n, m;
vector<vector<int>> graph;
vector<int> visit;
vector<int> ward;
vector<int> location;
vector<int> d;
int answer = 0;
void back(int start);
int smallIdx(int start);
bool flag = 0;
int main()
{
    cin >> n >> m;
    visit.resize(n, 0), ward.resize(n, 0), d.resize(n, INF), graph.resize(n, vector<int>(n, INF));
    for (int i = 0; i < n; i++)
    {
        int a;
        cin >> a;
        ward[i] = a; // n-1번째 분기점은  지나가도 됨. 따라서 ward값이 1이면 못지나가는데 n-1은 무조건 1이라서 상관없음
    }
    ward[n - 1] = 0;
    for (int i = 0; i < m; i++)
    {
        int s, f, v;
        cin >> s >> f >> v;
        graph[s][f] = v;
        graph[f][s] = v;
    }
    for (int i = 0; i < n; i++)
    {
        graph[i][i] = 0; //자기 자신을 향한 경로는 0으로 초기화
        location[i] = 0; //모든 점을 0에 대한 것으로 일단 연결
    }
    d[0] = 0;
    back(0);
    if (flag == 1)
    { //목적지까지 잘갔다.
        cout << answer;
    }
    else
        cout << -1;
    //무조건 출발은 0번인듯. 넥서스는 n-1이고. 따라서 0에서 n-1까지 가는 최소의 경로를 찾아야함.
}
int smallIdx(int start)
{
    int sidx = 0, Max = INF;
    for (int i = 0; i < n; i++)
    {
        if (i == start || visit[i] == true)
        {
            // cout<<"TEEST "<<i<<endl;
            continue;
        }
        if (d[i] < Max)
        {
            // cout<<"TEST"<<i<<endl;
            Max = d[i];
            sidx = i;
        }
    }
    return sidx;
}
void back(int start)
{
    // cout << start << "방문!" << endl;
    if (visit[start] == 1)
    {
        // cout << "이미 방문했습니다" << endl;
        return;
    }
    visit[start] = 1; //방문처리
    //
    for (int i = 0; i < n; i++)
    { //차례대로 방문을 해서 거리테이블을 최신화함.
        if (visit[i] == 1 || ward[i] == 1)
        { //방문했거나,시야가 있다면 해당 지점은 못감.
            // cout << i << "는 와드가 있거나 방문했습니다" << endl;
            continue;
        }
        if (d[i] > graph[start][i])
        {
            // cout << start << "에서 " << i << "는 최신화해줍니다" << endl;
            d[i] = min(d[i], min(graph[start][i], graph[i][start]));
            location[i] = start;
        }
    }
    /*  //거리테이블 최산화 끝
     for (auto a : d)
     {
         cout << a << " ";
     }
     cout << endl;
     for (auto b : location)
     {
         cout << b << " ";
     }
     cout << endl; */
    if (start == n - 1)
    {
        flag = 1;
        for (auto a : visit)
        {
            if (a == 1)
            {
                answer += a;
            }
        }
        return;
    }
    int ridx = smallIdx(start);
    // cout << start << "에서 최소 여행지는" << ridx << "입니다" << endl;
    back(ridx);
    visit[ridx] = 0;
}