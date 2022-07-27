//백준 11047 동전0 실버4
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int n, k;
vector<int> answer;
bool comp(int a, int b)
{
    return a > b;
}
int main()
{
    cin >> n >> k;
    vector<int> coin(n, 0);
    for (int i = 0; i < n; i++)
    {
        cin >> coin[i];
    }
    sort(coin.begin(), coin.end(), comp);
    for (int i = 0; i < n; i++)
    {
        if (coin[i] > k)
        {
            continue;
        }
        else
        {
            answer.push_back(k / coin[i]);
            k = k - answer.back() * coin[i];
        }
    }
    int sum = 0;
    for (auto a : answer)
    {
        sum += a;
    }
    cout << sum;
}