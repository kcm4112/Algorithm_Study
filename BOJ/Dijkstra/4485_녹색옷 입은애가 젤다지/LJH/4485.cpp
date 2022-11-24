/*
골드4 4485 녹색 옷 입은애가 젤다지?
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <limits.h>
using namespace std;
int n, answer;
vector<vector<int>> graph;
vector<vector<int>> visit;
vector<int> result;
vector<int> x = { 1, -1, 0, 0 };
vector<int> y = { 0, 0, 1, -1 };
void print_result();
void bfs();
int main()
{
    while (1)
    {
        cin >> n;
        if (n == 0) //종료 조건
        {
            print_result(); //출력 형식 출력 
            break;
        }
        graph.assign(n, vector<int>(n, 0)); //입력 행렬 초기화
        visit.assign(n, vector<int>(n, INT_MAX)); //잃는 금액 배열 최신화
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                cin >> graph[i][j];
            }
        }
        visit[0][0] = graph[0][0]; //초기 출발 지점 금액 
        bfs();
    }
}
void print_result()
{
    for (int i = 0; i < result.size(); i++)
    {
        cout << "Problem " << i + 1 << ": " << result[i] << endl;
    }
}
void bfs()
{
    queue<pair<int, int>>pq;
    pq.push(make_pair(0, 0)); //초기 출발점 push
    while (!pq.empty())
    {
        int fx = pq.front().first;
        int fy = pq.front().second;
        // x,y 좌표 추출
        pq.pop();
        for (int i = 0; i < 4; i++)
        {
            int tempx = fx + x[i];
            int tempy = fy + y[i];
            if (tempx >= n || tempy >= n || tempx < 0 || tempy < 0)
            {
                continue;
            }
            if (visit[tempx][tempy] > visit[fx][fy] + graph[tempx][tempy]) //계속해서 갱신해주기.
            {
                pq.push(make_pair(tempx, tempy));
                visit[tempx][tempy] = visit[fx][fy] + graph[tempx][tempy];
            }
        }
    }
    result.push_back(visit[n - 1][n - 1]);
}