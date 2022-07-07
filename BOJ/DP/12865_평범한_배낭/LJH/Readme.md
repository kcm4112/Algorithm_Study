## **📕문제**

이 문제는 아주 평범한 배낭에 관한 문제이다.

한 달 후면 국가의 부름을 받게 되는 준서는 여행을 가려고 한다. 세상과의 단절을 슬퍼하며 최대한 즐기기 위한 여행이기 때문에, 가지고 다닐 배낭 또한 최대한 가치 있게 싸려고 한다.

준서가 여행에 필요하다고 생각하는 N개의 물건이 있다. 각 물건은 무게 W와 가치 V를 가지는데, 해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수 있다. 아직 행군을 해본 적이 없는 준서는 최대 K만큼의 무게만을 넣을 수 있는 배낭만 들고 다닐 수 있다. 준서가 최대한 즐거운 여행을 하기 위해 배낭에 넣을 수 있는 물건들의 가치의 최댓값을 알려주자.

---

## **📕****입력**

첫 줄에 물품의 수 N(1 ≤ N ≤ 100)과 준서가 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)가 주어진다. 두 번째 줄부터 N개의 줄에 거쳐 각 물건의 무게 W(1 ≤ W ≤ 100,000)와 해당 물건의 가치 V(0 ≤ V ≤ 1,000)가 주어진다.

입력으로 주어지는 모든 수는 정수이다.

---

## **📕****출력**

한 줄에 배낭에 넣을 수 있는 물건들의 가치 합의 최댓값을 출력한다.

---

## **💡문제 해석**

-   배낭의 넣는 물건들은 그대로의 무게를 넣어준다.(쪼갤수없다는 뜻임 == 0-1 knapsack)
-   0-1 knapsack 문제를 푸는 방법은 여러가지지만 이번 문제는 DP로 해결했다.
-   나는 단위무게당 이익이 높은 순으로 정렬을 했다.(이익, 무게 모두)
-   DP \[i\]\[j\]란?
    -   i는 현재 내가 탐색한 가방의 수
    -   j는 내가 담을 수 있게 허락된 무게.
-   경우의 수는 2가지이다.
    -   담았을 경우  
        -   DP \[i\]\[j\]=DP \[i-1\]\[j-weight \[i\]\]+profit \[i\]
            -   그전에 해당되는 경우에서 담았으니까 weight를 뺴주고, profit을 더해준다.
    -   담지 못한 경우
        -   DP \[i\]\[j\]=DP \[i-1\]\[j\]
            -   그전에 해당되는 profit을 그대로 들고 오면 된다.
    -   담았을 경우와 안담은 경우 중에 큰 값이 DP \[i\]\[j\]이다.
    -   만약 현재 내가 담으려는 무게가 j(허락된 무게) 보다 무겁다면 담지를 못하므로 DP \[i-1\]\[j\]로 초기화시켜주면 된다.
-   **최종적으로 구해야 하는 값은 DP \[n\]\[w\]이다.**

## **📃코드**

```
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
    profit.resize(n+1, 0), weight.resize(n+1, 0),bag.resize(n+1,vector<int>(w+1,0));
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
    for(int i=1; i<=n;i++) //n은 몇번째 가방
    {
        for(int j=1; j<=w;j++) //j는 무게를 뜻한다. 즉 P[2][30]은 2번째 배낭까지 탐색하고 그때의 허락되는 무게는 30이라는 뜻.
        {
            totweight=weight[i],totprofit=profit[i];
            if(totweight>j) //허락되는 무게보다 totweight가 넘는다면? --> 즉 못담았을경우 이때까지 담았던 값을 저장시켜주면됨.
            {
                bag[i][j]=bag[i-1][j];
            }
            else if(totweight<=j)//허락되는 무게보다 totweight가 작다면 -> 즉 담을 수 있다면?
            {
                //담은경우와 안담은경우를 비교해서 큰 것을 고른다. 
                //담았을 경우 내가 허락되는 무게에서 현재 무게를 빼고 그 무게에 대한 가치를 bag[i][j]에 넣어준다.
                bag[i][j]=max(bag[i-1][j],bag[i-1][j-totweight]+totprofit);
            }
        }
    }
    cout<<bag[n][w]; // 최종 우리가 구하고자 하는 값이다.
}
```

---

## **느낀 점**

-   0-1 knapsack 문제는 단위 무게당 이익으로 내림차순 정렬할 필요가 없다.
-   DP의 개념은 진짜 어렵다. 따라서 반복 숙달이 필요할 것 같다.