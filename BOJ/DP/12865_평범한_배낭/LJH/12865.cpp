/*12865*/
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
vector<int> profit, weight, include;
int n, w, NowWeight, Maxprofit;
void knapsack(int i, int totweight, int totprofit);
vector<vector<int>>bag;
int main()
{
    cin >> n >> w;
    profit.resize(n + 1, 0), weight.resize(n + 1, 0), bag.resize(n + 1, vector<int>(w + 1, 0));
    for (int i = 1; i <= n; i++)
    {
        cin >> weight[i] >> profit[i];
    }
    //나는 단위무게별로 내림차순 정렬을 했는데,DP문제에서는 굳이 단위무게별로 정렬을 안해도 된다는 사실을 알았음.
    for (int i = 1; i <= n; i++)
    {
        for (int j = i + 1; j <= n; j++)
        {
            if ((double)profit[i] / weight[i] < (double)profit[j] / weight[j])
            {
                swap(profit[i], profit[j]);
                swap(weight[i], weight[j]);
            }
        }
    }
    knapsack(0, 0, 0);
}
void knapsack(int i, int totweight, int totprofit)
{
    /*DP로 계산해서 담았을떄와 안담았을때 그 때의 무게를 비교.
    만약 담지 않았을 경우 P[i-1][totweight]가 되겠죵
    만약 담았을 경우 P[i][W]= P[i-1]+[totweight-wegiht[i]]인가?
    */
    for (int i = 1; i <= n; i++) //n은 몇번째 가방
    {
        for (int j = 1; j <= w; j++) //j는 무게를 뜻한다. 즉 P[2][30]은 2번째 배낭까지 탐색하고 그때의 허락되는 무게는 30이라는 뜻.
        {
            totweight = weight[i], totprofit = profit[i];
            if (totweight > j) //허락되는 무게보다 totweight가 넘는다면? --> 즉 못담았을경우 이때까지 담았던 값을 저장시켜주면됨.
            {
                bag[i][j] = bag[i - 1][j];
            }
            else if (totweight <= j)//허락되는 무게보다 totweight가 작다면 -> 즉 담을 수 있다면?
            {
                //담은경우와 안담은경우를 비교해서 큰 것을 고른다. 
                //담았을 경우 내가 허락되는 무게에서 현재 무게를 빼고 그 무게에 대한 가치를 bag[i][j]에 넣어준다.
                bag[i][j] = max(bag[i - 1][j], bag[i - 1][j - totweight] + totprofit);
            }
        }
    }
    cout << bag[n][w]; // 최종 우리가 구하고자 하는 값이다.
}