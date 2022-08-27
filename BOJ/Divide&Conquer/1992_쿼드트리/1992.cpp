/*
백준 실버1 쿼드트리
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <deque>
using namespace std;
int n;
vector<vector<int>> graph;
deque<string> dq;
void makeTree(int row, int col, int Size);
bool checkTree(int row, int col, int Size);
void decideTree(int row, int col);
int main()
{
    cin >> n;
    graph.resize(n, vector<int>(n, 0));
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            scanf("%1d", &graph[i][j]); //공백없이 한글자씩 입력받음.
        }
    }
    makeTree(0, 0, n);
    while (!dq.empty())
    {
        auto a = dq.front();
        dq.pop_front();
        cout << a;
    }
}
void makeTree(int row, int col, int Size)
{

    int tsize = Size / 2; //4사분면은 가로와 세로를 2등분하면 된다.
    if (tsize < 1) //더 이상 분할하지 못할 경우에는 재귀를 멈춘다.
    {
        return;
    }
    else
    {
        if (checkTree(row, col, Size) == true) //현재 기준점으로 Size만큼 배열을 탐색해서 적절한지 검사함.
        { //리턴값이 true면 모든 배열의 구성요소가 같다는 뜻.
            decideTree(row, col);
            return;
        }
        else
        { //쪼개야한다면
            dq.push_back("(");
            int x[2] = { row,row + tsize };
            int y[2] = { col,col + tsize };
            //4분면을 탐색함.
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    if (checkTree(x[i], y[j], tsize) == false) {
                        makeTree(x[i], y[j], tsize);
                    }
                    else {
                        decideTree(x[i], y[j]);
                    }
                }
            }
            dq.push_back(")");
        }
    }
}
bool checkTree(int row, int col, int Size) //배열이 모두 같은 원소로 구성되어있는지를 검사함.
{
    bool flag = true;
    int num = graph[row][col];
    for (int i = row; i < row + Size; i++)
    {
        if (flag == false)
        {
            break;
        }
        for (int j = col; j < col + Size; j++)
        {
            if (num != graph[i][j])
            { //다르다면
                flag = false;
                break;
            }
        }
    }
    return flag; //true면 모두 같은 원소, false면 하나라도 다른 원소가 있다는 뜻
}
void decideTree(int row, int col) //원소의 종류에 따라 dq에 넣어줌.
{
    int num = graph[row][col];
    switch (num)
    {
    case 0:
        dq.push_back("0");
        // dq.push_back(")");
        break;
    case 1:
        dq.push_back("1");
        // dq.push_back(")");
        break;
    }
}