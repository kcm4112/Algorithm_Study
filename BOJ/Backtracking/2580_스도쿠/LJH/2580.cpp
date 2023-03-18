#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
vector<vector<int> > graph;
vector<pair<int, int> > blank;
void funct(int idx);
bool check(int row, int col, int num);
bool square(int ropt, int copt, int num);
int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    graph.resize(9, vector<int>(9, 0));
    for (int i = 0; i < 9; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            cin >> graph[i][j];
            if (graph[i][j] == 0) //채워야할 칸들을 미리 저장.
            {
                blank.push_back(make_pair(i, j));
            }
        }
    }
    funct(0);
}
void funct(int idx)
{
    if (idx == blank.size()) //만약 모든 칸을 채웠다면 그때 출력하고 프로그램을 종료함.
    {
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                cout << graph[i][j] << " ";

            cout << "\n";
        }
        exit(0);
    }
    else
    {
        auto loc = blank[idx];
        for (int i = 1; i <= 9; i++)
        {
            if (check(loc.first, loc.second, i) == true) //x,y 좌표에 i가 적절한지 검사.
            {
                // 적절한 좌표.
                graph[loc.first][loc.second] = i; //만약 적절하다면 i를 입력하고 재귀함수 실행
                funct(idx + 1);
                graph[loc.first][loc.second] = 0; //재귀함수를 빠져나온다면 다시 초기화
            }
        }
    }
}
bool check(int row, int col, int num)
{
    for (int i = 0; i < 9; i++)
    {
        if (num == graph[row][i]) //가로줄 검사.
            return false;
        if (num == graph[i][col]) //세로줄 검사.
            return false;
    }
    int ropt = row / 3, copt = col / 3;  //사각형의 위치를 구함.
    if (!square(ropt, copt, num))
    {
        return false;
    }
    return true;
}
bool square(int ropt, int copt, int num)
{
    int startCol = copt * 3;
    int startRow = ropt * 3;
    for (int i = startRow; i < startRow + 3; i++) //3*3 크기의 사각형 내에서 num이 있는지 없는지를 검사함.
    {
        for (int j = startCol; j < startCol + 3; j++)
        {
            if (graph[i][j] == num) //만약 있다면 num은  x,y 좌표에 있지 못함.
                return false;
        }
    }
    return true;
}