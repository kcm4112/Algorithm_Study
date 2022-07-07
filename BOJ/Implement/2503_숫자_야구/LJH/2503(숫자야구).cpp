/*2503 숫자야구
숫자는 1~9 , 서로 다른 3글자
내가 부른 숫자와 자리가 일치하다면 스트라이크
숫자는 일치한데 자리가 다르면 볼
*/
#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <map>
using namespace std;
bool same(int num);
int baseball(int num, int cnum);
int c, s, b, n, Count = 0;
int main()
{
    cin >> n;
    vector<int> T(988, -1);
    for (int i = 0; i < n; i++)
    {
        cin >> c >> s >> b;
        for (int j = 123; j <= 987; j++)
        {                //서로 다른 숫자여야함.
            if (same(j)) // 1이 출력되면 같은 숫자니까 넘김.
                continue;
            else
            {
                if (baseball(c, j))
                {                   //내가 생각한 s,b가 일치하다면?
                    if (T[j] == -1) //한번이라도 걸러지면 안됨.
                        T[j] = 1;
                }
                else          //다르다면?
                    T[j] = 0; //한번이라도 제외되면 컷. 해야함.
            }
        }
    }
    for (int i = 123; i <= 987; i++)
    {
        if (T[i] == 1)
            Count++;
    }
    cout << Count;
    // count 넣고 strike ball 넣엇음.
}
bool same(int num)
{
    int A, B, C;
    A = num / 100; //백의자리
    num = num % 100;
    B = num / 10; // 10의 자리?
    C = num % 10;
    if (A == 0 || B == 0 || C == 0)
        return true;
    else if (A - B == 0 || A - C == 0 || B - C == 0)
        return true;
    else
        return false;
}
int baseball(int num, int cnum)
{
    int strike = 0, ball = 0;
    vector<int> old(3, 0), New(3, 0);
    old[0] = num / 100; //백의자리
    num = num % 100;
    old[1] = num / 10;   // 10의 자리?
    old[2] = num % 10;   // 1의 자리
    New[0] = cnum / 100; //백의자리
    cnum = cnum % 100;
    New[1] = cnum / 10; // 10의 자리
    New[2] = cnum % 10; // 1의 자리
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            if (old[i] == New[j])
            { //숫자가 같을 때
                if (i == j)
                { // 자리수가 같다면?
                    strike++;
                }
                else
                    ball++;
            }
        }
    }
    if (s == strike && b == ball)
        return 1;
    else
        return 0;
}