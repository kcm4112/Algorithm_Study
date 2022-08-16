/*
백준 골드4 최단경로
*/
#include <bits/stdc++.h>
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;
#define INF 3000000
int n, m, s;
vector<int> d;
int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    cin >> n >> m >> s;
    vector<pair<int, int>> p[n + 1];
    d.resize(n + 1, INT_MAX);
    d[s] = 0;
    for (int i = 0; i < m; i++)
    {
        int start, finish, value;
        cin >> start >> finish >> value;
        p[start].push_back(make_pair(finish, value)); //이러면 같은 지역에 대한 다른 경로를 구분지어줘야함.
    }
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> PQ;
    PQ.push(make_pair(0, s));
    while (0 != PQ.size())
    {
        auto now = PQ.top();
        PQ.pop();
        for (auto i : p[now.second])
        {
            if (i.second + now.first < d[i.first])
            {
                d[i.first] = i.second + now.first;
                PQ.push(make_pair(d[i.first], i.first));
            }
        }
    }
    for (int i = 1; i <= n; i++) //모든 거리 출력
    {
        if (d[i] == INT_MAX)
        {
            cout << "INF" << endl;
        }
        else
            cout << d[i] << endl;
    }
    return 0;
}