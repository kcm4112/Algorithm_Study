/*
백준 2469 사다리타기 골드5
*/
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int k, n;
string str;
vector<int> start;
vector<int> finish;
vector<vector<char>> graph;
vector<char> result;
int fault = 0;
int main()
{
    cin >> k >> n;
    start.resize(k, 0);
    finish.resize(k, 0);
    graph.resize(n, vector<char>(k, 0));
    cin >> str;
    for (int i = 0; i < k; i++) // 알파벳을 int형으로 바꾸기
    {
        start[i] = 'A' + i;
        finish[i] = str[i];
    }
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < k - 1; j++)
        {
            cin >> graph[i][j];
            if (graph[i][j] == '?') // 가려진 열 찾기
            {
                fault = i;
            }
        }
    }
    for (int i = 0; i < fault; i++) // start 배열을 ?가 만나기전 까지 진행
    {
        for (int j = 0; j < k - 1; j++)
        {
            if (graph[i][j] == '-')
            {
                swap(start[j], start[j + 1]);
            }
        }
    }
    for (int i = n - 1; i >= fault + 1; i--) // finish 배열을 ?가 만나기전 까지 진행
    {
        for (int j = 0; j < k - 1; j++)
        {
            if (graph[i][j] == '-')
            {
                swap(finish[j], finish[j + 1]);
            }
        }
    }
    for (int i = 0; i < k - 1; i++)
    {
        if (start[i] == finish[i]) // 두 배열이 같다면 그대로 진행
        {
            result.push_back('*');
        }
        else if (start[i] == finish[i + 1] && start[i + 1] == finish[i]) // 두 배열이 다르지만 교차하는 상태라면 -을 기록, swap해준다.
        {
            swap(start[i], start[i + 1]);
            result.push_back('-');
        }
        else // 만약 위 경우의 수에 걸리지 않는다면 결과물을 도출할 수 없기에 x를 k-1개 기록한다.
        {
            result.assign(0, 0);
            for (int j = 0; j < k - 1; j++)
            {
                result.push_back('x');
            }
        }
    }
    for (int i = 0; i < k - 1; i++) // 결과 출력
    {
        cout << result[i];
    }
}