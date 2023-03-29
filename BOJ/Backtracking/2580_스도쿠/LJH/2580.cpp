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
            if (graph[i][j] == 0)
            {
                blank.push_back(make_pair(i, j));
            }
        }
    }
    funct(0);
}
void funct(int idx)
{
    if (idx == blank.size()) //만약 모든 빈칸을 채웠다면 그때 출력하고 종료.
    {
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                cout << graph[i][j] << " ";
            }
            cout << "\n";
        }
        exit(0);
    }
    else
    {
        auto loc = blank[idx];
        for (int i = 1; i <= 9; i++)
        {
            if (check(loc.first, loc.second, i) == true)
            {
                // 적절한 좌표.
                graph[loc.first][loc.second] = i;
                funct(idx + 1); // 다음거.
                graph[loc.first][loc.second] = 0;
            }
        }
    }
}
bool check(int row, int col, int num)
{
    for (int i = 0; i < 9; i++)
    {
        if (num == graph[row][i])
            return false;
        if (num == graph[i][col])
            return false;
    }
    // 3*3사각형
    int ropt = row / 3, copt = col / 3;
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
    for (int i = startRow; i < startRow + 3; i++) //3*3 사각형 검사
    {
        for (int j = startCol; j < startCol + 3; j++)
        {
            if (graph[i][j] == num)
                return false;
        }
    }
    return true;
}