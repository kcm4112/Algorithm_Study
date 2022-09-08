/*
백준 골드4 데스노트
사용하지 않는 칸의 제곱의 합이 최소가 되어야함.
마지막 줄의 남는 칸은 계산하면 안됨.
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <math.h>
#include <climits>
using namespace std;
int n, m, now;
vector<int> name;
vector<int> DP;
// DP[i]는 i번째 이름을 처음으로 쓸 경우의 공백의 최솟값을 넣어줌. 다음 칸을 붙일지 안붙일지만 결정해주면 됨.
int paper;
int dp(int step);
int main()
{
    cin >> n >> m;
    name.resize(n + 1, 0), DP.resize(n + 1, INT_MAX);
    for (int i = 0; i < n; i++)
    {
        cin >> name[i];
    }
    DP[n - 1] = 0; //마지막 줄은 공백을 계산하지 않아도 되기 때문에 0이다.
    cout << dp(0) << endl;

    return 0;
}
int dp(int step) { //step은 현재 내가 몇번째 이름까지 검사햇는지 체크하는 것.
    //이름이 너비를 넘지 않으면 일단 재귀를 넘겨보자.
    //현재상태에서 잔여칸은  잔여-name[i]가 될거같음.
    if (DP[step] < INT_MAX)
    {
        return DP[step];
    }

    int Paper = m - name[step]; //잔여를 계속 step마다 초기화함.
    for (int i = step + 1; i <= n && Paper >= 0; i++) { //현재 step 다음 거붙어 이어붙이는 거죵
        if (i == n) { //끝까지 돌았다는 뜻.
            DP[step] = 0;
            break;
        }
        DP[step] = min(DP[step], Paper * Paper + dp(i));
        Paper -= name[i] + 1; //종이를 이어붙인 경우 ,, 항상 이름+공백 한칸이 들어가기 때문에 뺴준다.
        //현재 step의 dp값은 이어붙일때와 이어붙이지 않을 때 중 최소값이 들어가게 될것이다.
    }
    return DP[step];
}