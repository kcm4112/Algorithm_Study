/*
백준 실버1 포도주 시식
*/
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int n;
vector<int> glass;
vector<int> DP;
int main()
{
    cin >> n;
    glass.resize(n, 0);
    DP.resize(n, 0);
    int Max = 0;
    for (int i = 0; i < n; i++)
    {
        cin >> glass[i];
    }
    DP[0] = glass[0];
    DP[1] = glass[1] + glass[0]; //01 
    DP[2] = max(max(DP[1], DP[0] + glass[2]), glass[1] + glass[2]); //01 02
    for (int i = 3; i < n; i++) {
        DP[i] = max(max(DP[i - 2] + glass[i], DP[i - 3] + glass[i - 1] + glass[i]), DP[i - 1]);
    }
    cout << DP[n - 1];
}