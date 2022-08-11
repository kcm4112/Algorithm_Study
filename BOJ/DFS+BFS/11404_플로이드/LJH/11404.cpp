/*
골드4 백준 11404 플로이드
*/
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
#define INF 10000001
int n, m;
vector<vector<int>> graph;
vector<vector<int>> Path;
int main()
{
    cin >> n >> m;
    graph.resize(n + 1, vector<int>(n + 1, INF));
    Path.resize(n + 1, vector<int>(n + 1, INF));
    for (int i = 0; i < m; i++)
    {
        int s, f, value;
        cin >> s >> f >> value;
        if (graph[s][f] != -1) //경로가 여러개일수도있지만 그 중에서 최소의 비용을 가진 경로를 넣어줘야함.
        {
            graph[s][f] = min(graph[s][f], value);
        }
        else
            graph[s][f] = value; //만약 입력이 안된 도시-도시라면 그냥 value를 넣어줌.
    }
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            if (i == j) // i와 j가 같은 경우는 0으로 초기화.
            {
                graph[i][j] = 0;
            }
            Path[i][j] = graph[i][j];
        }
    }
    for (int k = 1; k <= n; k++) // i에서 j로 가는데 k를 거친다.
    {
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (Path[i][j] > Path[i][k] + Path[k][j]) //직접적으로 가는것보다 거쳐서 가는 것이 더 빠를경우
                {
                    Path[i][j] = Path[i][k] + Path[k][j];
                }
            }
        }
    }
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            if (Path[i][j] == INF) // INF이면 경로가 없다는 뜻이므로 문제 출제의도에 따라 0을 넣어줌.
            {
                Path[i][j] = 0;
            }
            cout << Path[i][j] << " ";
        }
        cout << endl;
    }
}