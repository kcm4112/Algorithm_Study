/*
백준 7569 골드5
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;
int n, m, h, answer = -1, day = 0; // N = 상자 세로 칸의 수 M = 상자 가로 칸의 수 H = 상자의수
vector<vector<vector<int>>> tomato;
vector<vector<vector<int>>> date;
vector<vector<vector<int>>> visit;
queue<pair<int, int>> Tomato;
vector<int> x = { 0, 1, -1, 0, 0, 0 }, y = { 1, 0, 0, -1, 0, 0 }, z = { 0, 0, 0, 0, 1, -1 };
void tom();
int main()
{
    cin >> m >> n >> h;
    tomato.resize(n, vector<vector<int>>(m, vector<int>(h, 0)));
    visit.resize(n, vector<vector<int>>(m, vector<int>(h, 0)));
    date.resize(n, vector<vector<int>>(m, vector<int>(h, 0)));
    for (int k = 0; k < h; k++)
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                int num = 0;
                cin >> num;
                tomato[i][j][k] = num;
                if (num == 1)
                {
                    Tomato.push(make_pair(i * m + j, k)); //해당 행렬에서 좌표를 특정위치로 삼아서 넣고, k를 넣어줌.
                }
            }
        }
    }
    tom();
    //토마토가 익은 부분을 queue에 넣어줌.
}
void tom()
{
    while (!Tomato.empty())
    { // tomato가 완전히 빌떄까지 반복한다.
        //첫번째를 선택함.
        int xx = Tomato.front().first;  // x,y좌표 합쳐놓은거 i*m+j
        int zz = Tomato.front().second; // z좌표
        int col = xx % m, row = xx / m; //실제 좌표를 구했음.
        // cout << "현재 익은 토마토위치는 " << row << " " << col << " " << zz << endl;
        Tomato.pop(); //선정하고 뺀다.
        //여기서 갈 수 있는 방향과 그 때 갔을 때 토마토의 값이 0이면 1로 바꿔줘야함.
        for (int i = 0; i < 6; i++)
        {
            int dx = row + x[i], dy = col + y[i], dz = zz + z[i]; //움직이는 좌표가 잘못된듯.. 바로 위랑 아래만 가능하네.
            if (dx < 0 || dx >= n || dy < 0 || dy >= m || dz < 0 || dz >= h)
            { //해당 방향으로 못가요 ~
              // cout << dx << " " << dy << " " << dz << " 해당 방향은 index가 터지기 때문에 못갑니다" << endl;
                continue;
            }
            //만약 갈 수 있다면?
            if (tomato[dx][dy][dz] == 0 && visit[dx][dy][dz] == 0)
            { //해당 자리에 토마토가 있다면?
                tomato[dx][dy][dz] = 1;
                visit[dx][dy][dz] = 1;
                date[dx][dy][dz] = date[row][col][zz] + 1;
                // cout << dx << " " << dy << " " << dz << " 토마토 찾앗다! " << date[dx][dy][dz] << endl;
                if (day < date[dx][dy][dz])
                {
                    day = date[dx][dy][dz];
                }
                Tomato.push(make_pair(dx * m + dy, dz));
            }
        }
    }
    for (int k = 0; k < h; k++)
    {
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (tomato[i][j][k] == 0) //만약 안익은 토마토가 있다면? -1로 해줘야함.
                {
                    // cout << "안익은 토마토가 있습니다" << endl;
                    day = -1;
                    break;
                }
            }
        }
    }
    cout << day << endl;
}