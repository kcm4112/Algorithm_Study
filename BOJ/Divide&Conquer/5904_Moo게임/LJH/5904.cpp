/*
백준  골드5 5904 Moo게임
s(0)= moo  3  [0]
s(1)= moo /mooo /moo 10 [0,3]
s(2)= moo mooo moo / moooo /moo mooo moo 25  [0,3,7,10]
s(3)= moo mooo moo moooo moo mooo moo / mooooo / moo mooo moo moooo moo mooo moo 56
[0,3,7,10,15,18,22]
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;
int n;
string str = "moo";
void moo(int i, int step, int init);
int main()
{
    cin >> n;
    moo(n, 0, str.length());
    // S(k)= s(k-1)+k*2+1+s(k-1)
    //글자를 받아서 규칙을 이해하고 그 문자를 추출해야할듯
}
void moo(int i, int step, int init)
{
    int idx = step + 1;
    if (i <= 3)
    { //임계점. 더이상 분할이 불가능함. 초기 문자열은 "moo"이기에
        cout << str[i - 1];
        return;
    }
    if (init * 2 + idx + 3 < i) //만약 내가 찾고싶어하는 번째의 글자가 해당 step에서 만들지 못한다면 문자열을 늘려줌.
    {
        moo(i, idx, init * 2 + idx + 3);
    }
    else //만약 내가 찾고싶어하는 번째의 글자가 해당 step에서 찾을 수 있다면?
    {
        if (i > init)
        {
            //규칙에 의하면 s(k)=s(k-1)+규칙+s(k-1)이다.
            if (i - init <= idx + 3)  //이 조건에 걸린다는 뜻은 규칙안에 있다는 뜻이다.
            {
                if (i - init == 1)//규칙에 의하면 m ooo...이기에 첫글자를 제외한 나머지 글자는 무조건 o를 출력한다.
                {
                    cout << "m";
                    return;
                }
                else
                {
                    cout << "o";
                    return;
                }
            }
            else //규칙 뒤쪽이라는 뜻은 내가 찾고자 하는 글자가 s(k-1)안에 있다는 뜻이다.
            {
                moo(i - (init + idx + 3), 0, str.length());
                //뒤쪽에 해당하는 글자수인 i-(init+idx+3)을 인자로 넣어주고, 다시 처음부터 탐색을 시작함.
            }
        }
    }
}