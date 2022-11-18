/*
골드5 2688 숫자 고르기
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <stack>
#include <queue>
#include <map>
using namespace std;
int n, C = 0, result = 0;
vector<int> graph;
vector<int> visit;
map<int, int> m;
bool check;
void dfs(int x, int fx);
int main()
{
    cin >> n;
    graph.resize(n + 1, 0);
    visit.resize(n + 1, 0);
    for (int i = 1; i <= n; i++)
    {
        cin >> graph[i];
    }
    for (int i = 1; i <= n; i++)
    {
        visit[i] = 1; //현재 탐색 좌표 방문 처리
        dfs(i, graph[i]);
        visit.assign(n + 1, 0); //방문 초기화
        check = false; // 초기화
    }
    cout << m.size() << endl;
    for (auto a : m)
    {
        cout << a.first << endl;
    }
}
void dfs(int x, int fx)
{
    if (visit[fx])
    { //이미 방문했다면
        if (x == fx) //다시 되돌아갔다면 
        {
            check = true; //check를 true로 바꿔줌
            m.insert(make_pair(fx, 0)); //그때의 값을 map에다 넣어줌.
        }
        return; //다시 돌아가지 않았따면
    }
    //방문안햇다면
    visit[fx] = 1;
    dfs(x, graph[fx]);
    if (check) //만약 dfs를 탈출했을때 check가 true라면,  두 좌표는 조합이 일치하기 때문에 넣어줌.
    {
        m.insert(make_pair(fx, 0));
        m.insert(make_pair(graph[fx], 0));
    }
}