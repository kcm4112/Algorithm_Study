// 11052
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
bool big(int a, int b);
int main()
{
    int n;
    vector<int> card;
    cin >> n;
    // vector<int>DP(n+1,0);
    vector<int> DP(n + 1);
    card.resize(n + 1, 0);
    bool flag;
    for (int i = 1; i <= n; i++)
    {
        cin >> card[i];
    }
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= i; j++)
        {
            DP[i] = max(DP[i], DP[i - j] + card[j]);
        }
    }
    cout << DP[n];

}