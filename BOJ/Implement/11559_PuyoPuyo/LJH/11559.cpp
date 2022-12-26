/*
백준 골드4 뿌요뿌요
*/

/*
같은 색 뿌요가 상하좌우로 연결되어 있으면 한꺼번에 없어짐. -> 1연쇄
뿌요들은 계속 아래로 떨어짐.
동 시간 대에 터지면 1연쇄라고 처리하는 거 같음.
RGBPY 6개의 색깔 .은 빈공간
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;
int result = 0;
int step;
char alph;
bool flag = false;
queue<pair<int, int>> q;
vector<vector<char>> graph;
vector<vector<int>> visit;
vector<int> x = {1, -1, 0, 0};
vector<int> y = {0, 0, 1, -1}; // 상하 좌우 방향 벡터
vector<int> color = {0, 0, 0, 0, 0};
vector<pair<int, int>> p;
void PuPu();
int select_color(int x, int y);
void print_ary();
int main()
{
    graph.resize(12, vector<char>(6, 0));
    visit.resize(12, vector<int>(6, 0));
    for (int i = 0; i < 12; i++)
    {
        for (int j = 0; j < 6; j++)
        {
            cin >> graph[i][j];
        }
    }
    while (1)
    {
        flag = false;
        for (int i = 0; i < 12; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                if (graph[i][j] != '.' && visit[i][j] == 0) //빈공간이 아니고, 아직 방문 안한 좌표
                {
                    q.push(make_pair(i, j));
                    PuPu();
                }
            }
        }
        visit.assign(12, vector<int>(6, 0));  //행렬을 한번 쫙 검사하면 방문처리를 다시 초기화
        for (int k = 0; k < p.size(); k++) //p에는 4개이상의 같은 문자열된 블록의 좌표가 들어있음.
        {
            int ax = p[k].first;
            int ay = p[k].second;
            graph[ax][ay] = '.'; //뿌요를 터트렸다면 해당 좌표를 빈공간으로 만들어줌.
        }
        p.clear(); //p 초기화
        for (int i = 0; i < 6; i++) { //중력적용하기, 내릴 수 있는 끝까지 블록을 내리기.
            queue<char> queue;
            for (int j = 11; j >= 0; j--)
                if (graph[j][i] != '.') queue.push(graph[j][i]);
            for (int j = 11; j >= 0; j--) {
                if (queue.empty()) {
                    graph[j][i] = '.';
                    continue;
                }
                graph[j][i] = queue.front();
                queue.pop();
            }
        }
        if (flag == true) //만약 한번이라도 연쇄가 일어났다면 연쇄 횟수를 늘려야한다.
        {
            result++;
        }
        if (flag == false) //연쇄가 없었다면, 더이상 연쇄할수 없다는 뜻이기에 종료
            break;
    }
    cout << result;
}
int select_color(int x, int y) //색깔에 대한 주소를 지정해줌.
{
    switch (graph[x][y])
    {
    case 'R':
        return 0;
    case 'G':
        return 1;
        break;
    case 'B':
        return 2;
    case 'P':
        return 3;
    case 'Y':
        return 4;
    }
}
void PuPu()
{
    while (!q.empty())
    {
        int mx = q.front().first;
        int my = q.front().second;
        alph = graph[mx][my];
        step = select_color(mx, my);
        q.pop();
        if (visit[mx][my] == 1)
        {
            continue;
        }
        color[step]++; //해당 컬러에 대한 블록 수를 증가시켜줌.
        p.push_back(make_pair(mx, my)); //p에 좌표를 저장해줌. -> 나중에 빈공간으로 교체해주기 위해서
        visit[mx][my] = 1; //방문처리 
        for (int i = 0; i < 4; i++)
        {
            int fx = mx + x[i];
            int fy = my + y[i];
            if (fx < 0 || fx >= 12 || fy < 0 || fy >= 12 || graph[fx][fy] != alph || visit[fx][fy] == 1) // 부적절한 좌표
            {
                continue;
            }
            q.push(make_pair(fx, fy));
        }
    }
    if (color[step] < 4) //만약 4개 이상의 블록을 연결못했다면 실패한것임.
    {
        for (int i = 0; i < color[step]; i++) //해당 블록 수만큼 다시 좌표를 뺴줌.
        {
            p.pop_back();
        }
        color[step] = 0; //0으로 초기화
    }
    if (color[step] >= 4) //만약 4개 이상의 블록을 연결했다면 성공
    {
        flag = true;
        color[step] = 0;
    }
}
void print_ary()
{
    for (int i = 0; i < 12; i++)
    {
        for (int j = 0; j < 6; j++)
        {
            cout << graph[i][j] << " ";
        }
        cout << endl;
    }
}