#include <iostream>
#include <vector>

using namespace std;
int n;
vector<int> ary;
vector<vector<int> > dp;
int funct(int start, int end);
int main()
{
    cin.tie(NULL);
    
    ios_base ::sync_with_stdio(false);
    cin >> n;
    ary.resize(n + 1, 0);
    dp.resize(n + 1, vector<int>(n + 1, -1));
    for (int i = 1; i <= n; i++)
    {
        cin >> ary[i];
    }
    cout << funct(1, n) << "\n";
}
int funct(int start, int end)
{
    if (start > end){ //범위 초과
        return 0;
    }
    if (dp[start][end] != -1){ //한번 구한 dp 배열은 다시 구할 필요가 없슴
        return dp[start][end];
    }
    if (ary[start] == ary[end]){ // 같으면 해줄 필요가 없는데.
        dp[start][end] = funct(start + 1, end - 1);
        return dp[start][end];
    }
    else
    { // 다를 경우. 끼워넣는 방법은 몇가지일까. 왼쪽을 오른쪽끝에 넣기 vs 오른쪽을 왼쪽 끝에 넣기?
        int right = 1 + funct(start, end - 1); //오른쪽 숫자를 왼쪽에 끼워넣기.
        int left = 1 + funct(start + 1, end); //왼쪽 숫자를 오른쪽에 끼워넣기.
        if (left < right) //최소로 끼워야 함.
            dp[start][end] = left;
        else
            dp[start][end] = right;
    }
    return dp[start][end];
}