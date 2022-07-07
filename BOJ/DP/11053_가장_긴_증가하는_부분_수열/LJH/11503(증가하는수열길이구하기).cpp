// 11053
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int n;
vector<int> ary, cnt, include;
vector<vector<int>>DP;
#define MAX 1001
int main()
{
    cin >> n;
    ary.resize(n + 1, 0);
    vector<int>dp(n, 0);
    for (int i = 0; i < n; i++)
    {
        cin >> ary[i];
    }
    //포함하고 포함안하고를 정하자.
    // DP[i][j]에서 i는 현재 탐색한 원소의 수로 하고, j를 현재 내가 담은 원소중에서 가장 큰수로 할까?
    // 그럼 DP[i][j]의 값은 길이로 하면 되겟당.!
    int nowV = 0, Cnt = 0, oldV = 0, CurV = 0;
    for (int i = 0; i < n; i++) //i는 기준이 되는 원소
    {
        for (int j = 0; j < i; j++) //j는 0~기준 전까지 찾는것.
        {
            if (ary[i] > ary[j])
            {
                dp[i] = max(dp[i], dp[j] + 1);
            }
        }
    }
    int max = 0;
    for (auto a : dp)
    {
        if (max < a)
        {
            max = a;
        }
    }
    cout << max + 1;
}