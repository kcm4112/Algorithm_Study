/*
실버2 특정거리의 도시 찾기
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>
#include <queue>
using namespace std;
int n, m, k, x;
queue<int> q;
vector<int> graph[300001];
vector<int> length;
vector<int> visit;
#define INF INT_MAX
priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq; // pair의 첫번째 순으로 정렬됨.
void dijkstra();
int main()
{
    bool check = false;
    cin >> n >> m >> k >> x;
    visit.resize(300001, 0);
    length.resize(300001, INF);
    // n : 정점의 개수 m : 간선의 개수 k : 정답인 거리 , x : 출발
    for (int i = 0; i < m; i++)
    {
        int start, finish;
        cin >> start >> finish;
        graph[start].push_back(finish);
    }
    pq.push(make_pair(0, x)); // first에는 거리. second에는 출발지역이 들어가용~
    length[x] = 0; //출발점 거리는 0
    dijkstra();
    for (int i = 1; i <= n; i++)
    {
        if (length[i] == k) //k길이를 만족한다면 그때의 vertex를 출력
        {
            check = true;
            cout << i << endl;
        }
    }

    if (check == false) //만약 k길이를 만족하는 경로가 없다면 -1출력 
    {
        cout << -1 << endl;
    }
}
void dijkstra()
{
    //거리 테이블 최신화 끝
    while (!pq.empty())
    {
        auto temp = pq.top();
        int distance = temp.first; //거리 추출.
        int startVertex = temp.second; //출발지 추출.
        visit[startVertex] = 1;
        pq.pop();
        for (int i = 0; i < graph[startVertex].size(); i++)
        {
            int v = graph[startVertex][i];
            if (visit[v] == 0) //방문을 하지 않았다면.
            {
                if (length[v] > distance + 1) //기존 pq에 푸쉬한 length보다 더 작은 길이라면 
                {
                    length[v] = distance + 1; //length[v]를 업데이트해줌.
                    pq.push(make_pair(length[v], v)); //pq에 넣어줌.
                }
                else if (length[v] <= distance + 1) //만약 기존에 길이보다 크다면 굳이 pq에 넣어줄 필요가 없음.
                {
                    continue;
                }
            }
        }
    }
}