/*
백준 골드4 데스노트
사용하지 않는 칸의 제곱의 합이 최소가 되어야함.
마지막 줄의 남는 칸은 계산하면 안됨.
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <math.h>
using namespace std;
int n, m, now;
vector<int> name;
vector<int> DP;
int paper;
int dp(int step);
int main()
{
    cin >> n >> m;
    //int INF = pow(m, 2);
    name.resize(n, 0), DP.resize(n, INF);
    for (int i = 0; i < n; i++)
    {
        cin >> name[i];
    }
    dp(0);
    cout << DP[n - 1];
}
int dp(int step) { //step은 현재 내가 몇번째 이름까지 검사햇는지 체크하는 것.
    //이름이 너비를 넘지 않으면 일단 재귀를 넘겨보자.
    //현재상태에서 잔여칸은  잔여-name[i]가 될거같음.
    if (step > n) //탐색할 개수는 n보다 크면 안된다.
    {
        return;
    }
    int Paper = m - name[step]; //잔여를 계속 step마다 초기화함.
    for (int i = step + 1; i < n; i++) { //현재 step 다음 거붙어 이어붙이는 거죵
        if (i == n - 1) { //끝까지 돌았넹
            DP[step] = 0;
            break;
        }
        DP[step] = min(DP[step], Paper * Paper + dp(i));
        paper = paper - name[i] - 1; //종이를 이어붙인 경우 ,, 항상 이름+공백 한칸이 들어가기 때문에 뺴준다.
        //현재 step의 dp값은 이어붙일때와 이어붙이지 않을 때 중 최소값이 들어가게 될것이다.
    }
}