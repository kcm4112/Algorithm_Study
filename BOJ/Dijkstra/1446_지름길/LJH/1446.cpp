/*
백준 실버1 지름길
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;
int n, d;
vector<int> dist;
int main()
{
    cin >> n >> d;
    dist.resize(d + 1, 0);
    vector<pair<int, int>> p[d + 1]; // p[d+1]의 vector를 생성 p(d+1)은 vector의 size만 조절 pair의 사이즈는 조절하지못한것.
    for (int i = 0; i < n; i++)
    {
        int s, f, v;
        cin >> s >> f >> v;
        if (f > d || f - s < v) //지름길이 부적절한 경우->
        {                       //만약 지름길의 도착점이 우리가 가고자 하는 지점보다 멀다면 굳이 필요가 없음.
                                // 지름길의 길이가 출발지점과 도착지점 사이의 실제거리보다 크다면 의미가 없음.
            continue;
        }
        p[f].push_back(make_pair(s, v));
    }

    // DP사용 DP[i]는 i까지의 최소거리를 저장하고싶음.
    // i까지의 지름길이 있는지 체크함.
    for (int i = 1; i <= d; i++)
    {
        if (p[i].size() == 0) // pair[i]의 size가 0이라면 i까지 가는데 지름길이 없다는 뜻이다.
        {
            // cout<<i<<endl;
            dist[i] = dist[i - 1] + 1;
        }
        else
        {
            for (auto a : p[i])
            {
                if (dist[i] == 0)
                {
                    dist[i] = min(dist[i - 1] + 1, dist[a.first] + a.second);
                }
                else
                    dist[i] = min(dist[i], min(dist[i - 1] + 1, dist[a.first] + a.second));
            }
        }
    }
    cout << dist[d] << endl;
}