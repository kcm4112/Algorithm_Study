#include <iostream>
#include <vector>
#include <algorithm>
#include <limits.h>
#include <string.h>
using namespace std;
int n;
vector<char> str;
long long small = LONG_MAX, big = 0;
vector<int> v;
void funct(int step, string s);
string ss = "";
string banswer = "", sanswer = "";
int main()
{
    cin >> n;
    str.resize(n, 0);
    v.resize(10, 0);
    for (int i = 1; i <= n; i++)
    { // 부등호 방향 넣기.
        cin >> str[i];
    }
    funct(0, ss);
    cout << banswer << "\n"
         << sanswer << endl;
}
void funct(int step, string s) // 백트래킹 함수.
{
    bool opt = true;
    if (str[step] == '>') // 왼쪽 방향은 true
        opt = true;
    else // 오른쪽 방향은 false
        opt = false;
    if (step == n + 1) // 만약 숫자가 n+1개 만들어졋다면
    {
        long num = stol(s);
        // 최대값 최소값 갱신해주기.
        if (num > big)
        {
            big = num;
            banswer = s;
        }
        if (num < small)
        {
            small = num;
            sanswer = s;
        }
        return;
    }
    else
    {
        for (int i = 0; i <= 9; i++)
        { // 0~9까지 반복
            string c = to_string(i);
            if (s.length() == 0)
            {
                v[i] = 1;
                funct(step + 1, s + c);
                v[i] = 0;
            }
            else
            {
                if (v[i] == 1) // 이미 사용한 숫자라면 continue
                    continue;
                if (opt) // 왼쪽 방향
                {
                    int num = (s[step - 1]) - '0';
                    if (num < i) // 부등호 방향에 맞게 숫자를 걸러줘야 함.
                        continue;
                    v[i] = 1;
                    funct(step + 1, s + to_string(i));
                    v[i] = 0;
                }
                else
                { // 오른쪽
                    int num = (s[step - 1]) - '0';
                    if (num > i) // num은 추출한 숫자고, i는 넣을려눈 숫자. 부등오 방향에 맞게 해줘야함.
                        continue;
                    v[i] = 1;
                    funct(step + 1, s + to_string(i));
                    v[i] = 0;
                }
            }
        }
    }
}