/* 17140 골드4 이차원 배열과 연산


A[r][c]가 k가 되기 위한 시간 계산
1초마다 R or C 연산을함.

기존 행렬 벡터를 지워줘야함. 시발. 개새기.
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;
int r, c, k;
vector<vector<int>> a;
map<int, int> m;
void makeary(int row, int col);
bool flag(int a, int b);
vector<int> visit;
int Row = 0, Col = 0, Cnt = 0;
int main()
{
    cin >> r >> c >> k;
    a.resize(101, vector<int>(101, 0));
    makeary(3, 3);
    int row = 3, col = 3;
    visit.resize(101, 0);     // visit배열 101로 초기화
    if (a[r - 1][c - 1] == k) //만약 바로 값을 찾을 수 있다면 계산할 필요가 없음.
    {
        cout << 0;
    }
    else
    {
        while (1) // a[r][c]가 k가 되거나? Cnt가 100을 넘기면 안됨.
        {
            int rsize = row; // row는 행의 개수
            int csize = col; // col은 열의 개수
            if (Cnt > 100)   // 100초가 지나면 -1을 출력하고 반복문을 탈출시킴.
            {
                cout << -1;
                break;
            }
            if (flag(csize, rsize)) //열과 행을 비교함.  //csize는 열의 개수, rsize는 행의 개수
            {
                //값이 1이면 R연산
                for (int i = 0; i < csize; i++)
                {
                    vector<pair<int, int>> p;
                    for (int j = 0; j < rsize; j++)
                    {
                        if (a[i][j] == 0)
                            continue;
                        visit[a[i][j]]++; //

                        m[a[i][j]] = visit[a[i][j]];
                        a[i][j] = 0;
                    }
                    //수,등장횟수로 정렬 등장횟수가 다르면 수 내림차순, 등장횟수가 같다면 수 오름차순.
                    for (auto iter = m.begin(); iter != m.end(); iter++)
                    {
                        p.push_back(make_pair(iter->second, iter->first)); // pair를 sort하면 first값으로 정렬됨.
                    }
                    for (int i = 0; i < p.size(); i++)
                    {
                        for (int j = i + 1; j < p.size(); j++)
                        {
                            if (p[i].first > p[j].first) //빈도수가 작은 순서대로 정렬
                            {
                                swap(p[i], p[j]);
                            }
                            else if (p[i].first == p[j].first) //빈도수가 같다면 숫자를 오름차순으로 정렬.
                            {
                                if (p[i].second > p[j].second)
                                {
                                    swap(p[i], p[j]);
                                }
                            }
                        }
                    }
                    int idx = 0;
                    if (Row < p.size() * 2) //실제로 숫자가 들어가는 크기는 pair의 2배가 들어가야한다. 최대값을 계속 바꿔줌.
                    {
                        Row = p.size() * 2;
                    }
                    // cout << "ROW " << Row << endl;
                    if (Row > 100) //만약 크기가 100을 넘긴다면 잘라줌.
                        Row = 100;
                    row = Row;                         //함수에 들어갈 row값 변경
                    for (int k = 0; k < p.size(); k++) //숫자,빈도수 순서대로 배열에 넣어줌.
                    {
                        a[i][idx] = p[k].second, idx++;
                        a[i][idx] = p[k].first, idx++; // second가 숫자, first가 빈도수
                    }
                    m.clear();            // map비우기.
                    visit.assign(101, 0); // visit배열 비우기.
                }
                Cnt++;                    // 1초 경과
                if (a[r - 1][c - 1] == k) //만약 a[r][c]를 찾았다면 그때 시간초를 출력하고 반복문 탈출
                {
                    cout << Cnt;
                    break;
                }
            }
            else
            {
                for (int x = 0; x < rsize; x++) //행의 개수
                {
                    vector<pair<int, int>> P;
                    for (int y = 0; y < csize; y++) //열의 갯수
                    {
                        if (a[y][x] == 0)
                            continue;
                        visit[a[y][x]]++; //빈도수 ++시켜주기.
                        m[a[y][x]] = visit[a[y][x]];
                        a[y][x] = 0; //값을 넣고 실시간으로 0으로 초기화해줌.
                    }
                    for (auto iter = m.begin(); iter != m.end(); iter++)
                    {
                        P.push_back(make_pair(iter->second, iter->first));
                    }

                    for (int i = 0; i < P.size(); i++)
                    {
                        for (int j = i + 1; j < P.size(); j++)
                        {
                            if (P[i].first > P[j].first) //빈도수가 작은 순서대로 정렬
                            {
                                swap(P[i], P[j]);
                            }
                            else if (P[i].first == P[j].first) // 빈도수가 같을시 숫자를 비교해서 오름차순으로 정렬
                            {
                                if (P[i].second > P[j].second)
                                {
                                    swap(P[i], P[j]);
                                }
                            }
                        }
                    }
                    int idx = 0;
                    if (Col < P.size() * 2) //실제로 들어가는 크기는 pair의 크기 * 2다.
                    {
                        Col = P.size() * 2;
                    }
                    if (Col > 100) //만약 100을 넘긴다면 100이후로는 짤라줌.
                        Col = 100;
                    col = Col;
                    for (int k = 0; k < P.size(); k++) //숫자,빈도수 순서대로 배열에 넣어줌.
                    {
                        a[idx][x] = P[k].second;
                        idx++;
                        a[idx][x] = P[k].first;
                        idx++;
                    }
                    m.clear();            // map비우기.
                    visit.assign(101, 0); // visit배열 비우기.
                }
                Cnt++;
                if (a[r - 1][c - 1] == k) //만약 값을 찾았다면 초를 출력하고 반복문 탈출
                {
                    cout << Cnt;
                    break;
                }
            }
        }
    }
}
void makeary(int row, int col)
{
    int num;
    for (int i = 0; i < row; i++)
    {
        for (int j = 0; j < col; j++)
        {
            cin >> num;
            a[i][j] = num;
        }
    }
}
bool flag(int a, int b) // a가 행 , b가 열
{
    if (a >= b)
        return true;
    else
        return false;
}