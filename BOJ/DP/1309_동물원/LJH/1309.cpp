/*
백준 실버1 동물원
세로의 칸 수가 늘어나도 그 이전의 칸 수의 경우를 계쏙 포함해야함.
*/
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
vector<int>DP;
int n;
int main() {
    cin >> n;
    DP.resize(n + 1, 0);
    DP[1] = 3;
    DP[2] = 7;
    for (int i = 3; i <= n; i++) {
        DP[i] = (DP[i - 1] * 2 + DP[i - 2]) % 9901;
    }
    cout << DP[n];
}
