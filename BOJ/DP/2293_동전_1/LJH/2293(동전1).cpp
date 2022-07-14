/*
2293번 동전1
*/
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int n, k;
vector<int> coin;
vector<int> DP;
int main()
{
    cin >> n >> k;
    coin.resize(n + 1, 0);
    DP.resize(k + 1, 0);
    for (int i = 1; i <= n; i++)
    {
        cin >> coin[i];
    }
    DP[0] = 1;  //0원을 만드는 경우의 수는 1개이다.
    //DP[a]는 a원을 만드는 경우의수.

    for (int i = 1; i <= n; i++)
    {
        for (int j = coin[i]; j <= k; j++) //동전이 늘어날때마다 DP[j]를 최신화해준다.
        //동전이 늘어나도 j의 범위가 coin[i]인 이유는 i가 증가할때마다 코인을 바꿔주는데 사용할려는 코인의 가치보다 낮은 가치를 만들 수 없기 때문에 최소 범위가 coin[i]이다.
        //DP[j]를 더해주는 이유는 그전까지 사용했던 동전으로 j원을 만든 경우의 수를 더해줘야 하기 때문이다.
        //DP[j-coin[i]]을 더해주는 이유는 DP[j] . 즉 j원을 만들때 j에서 동전의 합을 뺀 경우의수와 이전에 동전들로 j를 만든 경우를 합치면 
        //현재 내가 사용하고 있는동전 coin[i]에 대해서 j원을 만들 수 있는 경우의 수를 구할 수 있다.
        {
            DP[j] = DP[j - coin[i]] + DP[j];
        }
    }
    cout << DP[k];
}