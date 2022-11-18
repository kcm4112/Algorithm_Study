/*
15900 실버1 나무탈출
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <stack>
using namespace std;
int n;
vector<int> graph[500001];
vector<int> visit;
void dfs(int x, int detph);
int cnt = 0;
int height = 0;
int main()
{
    int start, finish;
    cin >> n;
    visit.resize(n + 1);
    for (int i = 0; i < n - 1; i++)
    {
        cin >> start >> finish;
        graph[start].push_back(finish);
        graph[finish].push_back(start);
    }
    dfs(1, 0);
    if (height % 2 == 1)
    {
        cout << "Yes" << endl;
    }
    else
    {
        cout << "No" << endl;
    }
}
void dfs(int x, int depth)
{
    visit[x] = 1;
    if (x != 1 && graph[x].size() == 1) //현재 들어온 지점이 리프노드일때임.
    // 즉 출발점이 아니고 간선이 하나일때.
    {
        cnt++; //리프 노드 갯수 +
        height += depth; //그때의 말 이동횟수를 저장.
        return;
    }
    for (int i = 0; i < graph[x].size(); i++)
    {
        if (visit[graph[x][i]] != 0) //이미 방문한 좌표라면 넘어감!
        {
            continue;
        }
        visit[graph[x][i]] = 1; //방문처리
        dfs(graph[x][i], depth + 1);
    }
}