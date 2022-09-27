#include <iostream>
#include <vector>
#include <algorithm>
#include <string.h>

using namespace std;
string str1, str2;
int answer = 0;
vector<vector<int>> dp;
int main()
{
  //문자열 2개 선언
    cin >> str1 >> str2; //문자열 2개 입력받기
    int size = str1.length(), Size = str2.length();
    dp.resize(size + 1, vector<int>(Size + 1, 0));  // 2차원 배열 초기화.
    for (int i = 1; i <= size; i++)
    {
        for (int j = 1; j <= Size; j++)
        {
            if (str1[i - 1] == str2[j - 1]) //문자열이 같아요
            {
                dp[i][j] = dp[i - 1][j - 1] + 1;                
            }       
            answer = max(answer, dp[i][j]);

        }
    }
    
    cout << answer;
 
}