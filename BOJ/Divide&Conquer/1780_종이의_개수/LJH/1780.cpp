/*
백준 실버2 종이의 개수
*/
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int n;
vector<vector<int>> graph;
vector<int> sum(3);
void makepaper(int row, int col, int size);
bool checkpaper(int row, int col, int size);
void sumpaper(int row, int col);
int main()
{
    cin >> n;
    graph.resize(n, vector<int>(n, 0));
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> graph[i][j];
        }
    }
    makepaper(0, 0, n);
    for (int i = 0; i < sum.size(); i++)
    {
        cout << sum[i] << endl;
    }
}
void makepaper(int row, int col, int size)
{
    int Size = size / 3;
    if (Size < 1) //최소 크기일때 병합해야할까?
    {
        //cout << "종이의 분할가능 크기는 최소 1입니다" << endl;
        return; //
    }
    else
    {
        if (checkpaper(row, col, size) == true)
        {
            sumpaper(0, 0);
            return;
        }
        if (checkpaper(row, col, Size) == false)
        {
            // cout << "graph1 is not same" << endl;
            makepaper(row, col, Size);
        }
        else
        { // true면 모두 같은 종이의 구성요소가 있다는 뜻이다.
           // cout << "graph1 is same." << endl;
            sumpaper(row, col);
        }
        if (checkpaper(row, col + Size, Size) == false)
        {
            // cout << "graph2에 종이의 요소는 모두 같지 않습니다" << endl;
            makepaper(row, col + Size, Size);
        }
        else
        {
            // cout << "graph2 is  same" << endl;
            sumpaper(row, col + Size);
        }
        if (checkpaper(row, col + 2 * Size, Size) == false)
        {
            // cout << "graph3 is not same" << endl;
            makepaper(row, col + 2 * Size, Size);
        }
        else
        {
            //cout << "graph3 is same" << endl;
            sumpaper(row, col + 2 * Size);
        }
        if (checkpaper(row + Size, col, Size) == false)
        {
            //cout << "graph4 is not same" << endl;
            makepaper(row + Size, col, Size);
        }
        else
        {
            // cout << "graph4 is same" << endl;
            sumpaper(row + Size, col);
        }
        if (checkpaper(row + Size, col + Size, Size) == false)
        {
            // cout << "graph5 is not same" << endl;
            makepaper(row + Size, col + Size, Size);
        }
        else
        {
            // cout << "graph5 is same" << endl;
            sumpaper(row + Size, col + Size);
        }
        if (checkpaper(row + Size, col + 2 * Size, Size) == false)
        {
            // cout << "graph6 is not same" << endl;
            makepaper(row + Size, col + 2 * Size, Size);
        }
        else
        {
            //cout << "graph6 is same" << endl;
            sumpaper(row + Size, col + 2 * Size);
        }
        if (checkpaper(row + 2 * Size, col, Size) == false)
        {
            // cout << "graph7 is not same" << endl;
            makepaper(row + 2 * Size, col, Size);
        }
        else
        {
            // cout << "graph7 is same" << endl;
            sumpaper(row + 2 * Size, col);
        }
        if (checkpaper(row + 2 * Size, col + Size, Size) == false)
        {
            // cout << "graph8 is not same" << endl;
            makepaper(row + 2 * Size, col + Size, Size);
        }
        else
        {
            // cout << "graph8 is same" << endl;
            sumpaper(row + 2 * Size, col + Size);
        }
        if (checkpaper(row + 2 * Size, col + 2 * Size, Size) == false)
        {
            // cout << "graph9 is not same" << endl;
            makepaper(row + 2 * Size, col + 2 * Size, Size);
        }
        else
        {
            // cout << "graph9 is same" << endl;
            sumpaper(row + 2 * Size, col + 2 * Size);
        }
    }
}
void sumpaper(int row, int col)
{
    int num = graph[row][col];
    switch (num)
    {
    case -1:
        sum[0] += 1;
        break;
    case 0:
        sum[1] += 1;
        break;
    case 1:
        sum[2] += 1;
        break;
    }
}
bool checkpaper(int row, int col, int s)
{
    int check = graph[row][col]; //-1,0,1 아닌 숫자면 가능
    bool cp = true;
    for (int i = row; i < row + s; i++)
    {
        if (cp == false)
        {
            break;
        }
        for (int j = col; j < col + s; j++)
        {
            if (check != graph[i][j]) {
                cp = false;
                break;
            }
        }
    }
    return cp;
}