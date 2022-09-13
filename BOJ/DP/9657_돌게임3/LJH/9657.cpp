/*
백준 실버3 돌 게임3
*/
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int n;
vector<bool> DP;
int main()
{
    cin >> n;
    DP.resize(n + 1, 0);
    DP[1] = DP[3] = DP[4] = 1; //1은 선공 승, 0은 후공 승이라는 뜻.
    DP[2] = 0;
    for (int i = 5; i <= n; i++) {
        //각 단계에서 하위 가지는 i-1,i-3,i-4가 될 것이다.
        DP[i] = min(min(DP[i - 1], DP[i - 3]), DP[i - 4]);
        if (DP[i] == 1) {
            DP[i] = false;
        }
        else {
            DP[i] = true;
        }
        //한번 뽑으면 찬영이 차례임.
        //나는 상근이가 이기는 경우의 수를 골라야하니 각각 이전 단계에서는 min을 뽑아서 상근이가 이기는 경우인 0이 하나라도 있으면 DP[i]값을 1로 바꿨다.
        //하나라도 DP[i-1], DP[i-3], DP[i-4]가 하나라도 1이 된다면 상근이가 완벽하게 이길 수 있음.
    }
    if (DP[n] == true) {
        cout << "SK" << endl;
    }
    else {
        cout << "CY" << endl;
    }
}