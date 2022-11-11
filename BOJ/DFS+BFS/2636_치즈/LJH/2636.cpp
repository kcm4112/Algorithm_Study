/*
2306 골드4 치즈
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;
int n, m;
vector<vector<int>> graph;
vector<vector<int>> visit;
queue<pair<int, int>> q;
queue<pair<int, int>> chese;
vector<pair<int, int>> air;
vector<int> x = { 0, 0, 1, -1 };
vector<int> y = { 1, -1, 0, 0 };
int t = 0, piece = 0, number = 0, result;
void outside_air();
void melting();
bool check();
int main()
{
    cin >> n >> m;
    graph.resize(n, vector<int>(m, 0));
    visit.resize(n, vector<int>(m, 2));
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> graph[i][j];
            if (graph[i][j] == 1)
            { //치즈 위치를 queue에 넣음.
                q.push(make_pair(i, j));
                piece++;
                visit[i][j] = 0; //치즈는 0으로 초기화.
            }
        }
    }
    // graph랑 visit 배열 설정 위에서햇음.
    while (1)
    {
        if (check() == true)
        { //전부다 공기라면 반복문을 멈춘다.
            break;
        }
        result = piece;
        piece = 0;
        outside_air(); //외부공기를 visit값을 1로 초기화
        melting();     //외부공기와 맞닿은 치즈를 녹여줌. 해당 좌표를 graph 0값으로 돌림.
        t++;           //녹였으면 t++해줌.
        //녹이고 치즈 개수 세야함.
        while (!q.empty())
        { // queue 초기화.
            q.pop();
        }
        visit.assign(n, vector<int>(m, 2));
        for (int i = 0; i < n; i++)
        { // visit 배열에서 치즈 위치를 queue에 넣고, 치즈 위치를 visit에서 0으로 초기화
            for (int j = 0; j < m; j++)
            {

                if (graph[i][j] == 1)
                {
                    piece++; //치즈 기록.
                    q.push(make_pair(i, j));
                    visit[i][j] = 0;
                }
            }
        }
    }
    cout << t << endl; //다 녹인 시점 전에 시간을 출력
    cout << result;   //그 시간대에 치즈 조각들을 출력
}
bool check()  //배열에서 치즈가 다 녹았는지 안녹았는지 체크를 해줌.
{
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (graph[i][j] == 1)
            { //치즈가 있다면
                return false;
            }
        }
    }
    return true;
}
void outside_air() //외부 공기를 솎아 내는 작업
{
    queue<pair<int, int>> out;
    out.push(make_pair(0, 0)); //초기 좌표 넣어줌.
    while (!out.empty())
    {
        int tx = out.front().first;
        int ty = out.front().second;
        // cout<<"현재 좌표 : "<<tx<<" "<<ty<<endl;
        out.pop();
        for (int i = 0; i < 4; i++)
        {
            int row = tx + x[i];
            int col = ty + y[i];
            //  cout<<"move :"<<row<<" "<<col<<endl;
            if (row < 0 || col < 0 || row >= n || col >= m)
            {
                continue;
            }
            if (graph[row][col] == 0 && visit[row][col] == 2)
            { //갈려는 곳이 치즈가 아니고, 아직 방문 안했던 곳이라면
                out.push(make_pair(row, col));
                visit[row][col] = 1; //외부 공기는 1로 초기화.
            }
        }
    }
}
void melting() //외부공기랑 맞닿은 지역을 녹이기.
{
    queue<pair<int, int>> melt = q; // melt에는 치즈가 들어있음.
    while (!melt.empty())
    {
        int mx = melt.front().first;
        int my = melt.front().second;
        melt.pop();
        // chese 초기 위치 뽑아줌.
        for (int i = 0; i < 4; i++)
        { //좌표 이동
            int tempx = mx + x[i];
            int tempy = my + y[i];
            if (tempx < 0 || tempy < 0 || tempx >= n || tempy >= m)
            {
                continue;
            }
            if (visit[tempx][tempy] == 1)
            {                      //외부 공기와 맞닿아있다면
                graph[mx][my] = 0; // graph상에서 치즈를 공기로 바꿔줍니다.
            }
        }
    }
}