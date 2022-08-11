/*
실버1 백준 1697 숨박꼭질
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;
void bfs();
long long int n, k;
queue<long long int> q;
vector<vector<int>> graph;
int step = 1;
vector<int> a, b, c;
vector<int> visit;
int main()
{
    cin >> n >> k;
    visit.resize(100000);
    if (n == k)
    {
        cout << 0;
        return 0;
    }
    bfs();
}
void bfs()
{
    //처음으로 queue에 k가 들어가는 시간을 찾아보자.
    q.push(n);
    int size = q.size();
    int Size = 0;
    while (!q.empty())
    {
        int answer = q.front();
        q.pop();
        Size++;
        if (answer - 1 >= 0 && visit[answer - 1] == 0)
        {
            q.push(answer - 1);
            visit[answer - 1] = 1;
        }
        if (answer + 1 <= 100000 && visit[answer + 1] == 0)
        {
            q.push(answer + 1);
            visit[answer + 1] = 1;
        }
        if (answer * 2 <= 100000 && visit[answer * 2] == 0)
        {
            q.push(answer * 2);
            visit[answer * 2] = 1;
        }
        if (answer - 1 == k || answer + 1 == k || answer * 2 == k)
        {
            cout << step;
            break;
        }
        if (size == Size)
        {
            step++;
            size = q.size();
            Size = 0;
        }
    }
}