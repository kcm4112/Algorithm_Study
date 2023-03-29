#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
string str;
vector<int>dp;
int lim = 1000000;
void funct();
int main()
{
    cin >> str;
    if (str[0] == '0') //첫글자가 0이라면 암호가 될 수 없음.
    {
        cout << 0 << endl;
        return 0;
    }
    dp.resize(str.size()+1,0);
    dp[1]=1,dp[0]=1;
    funct();
    cout<<dp[str.size()]%lim<<endl;
}

void funct()
{
    for (int i = 2; i <= str.size(); i++)
    {
        int num1 = str[i - 2] - '0',num2 = str[i - 1] - '0';
        if(num1 ==0 && num2 ==0){ // 00이 된다면, 암호가 될 수 없음.
            dp[str.size()]=0;
            return;
        }
        int temp = num1 * 10 + num2; 
        if(num2>0){ //이전 상태를 그대로 들고옴.
            dp[i]=dp[i-1];
        }
        if(temp>= 10 && temp<=26){ //두 글자를 만들수 있는 범위일때
            dp[i]=dp[i-2]+dp[i];
        }
        dp[i]%=lim;
    }
}