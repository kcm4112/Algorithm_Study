/* 9935 골드4 문자열 폭발
내가 처음에 하는 방식은 문자열 처음 부터 차근차근 비교해서 하는것 --> 시간초과가 남.
왜 시간초과가 나지? O(n[문자열길이]) *O(폭탄길이) 라서?
어떻게 해야할까?
---내잘못 ..,
나는 그 폭발문자열에 포함되어있는 문자열만 만나면 지워줘야된다고 생각했지만 그 폭발문자열 전체를 지우는것이었다.
그래서 틀렸다.. 하나하나 비교해서 지웟으니까 틀리지,.,
예를 들어 c4가 있다면 ca4가 있으면 이건 못지우는 건데 내 코드로는 지운것이다. 그래서 틀린것이었고 내가 의도한 접근대로 하니 당연히 시간초과가 나는것이고,,
문제를 잘읽자.
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <queue>
#include <stack>
using namespace std;
string str, bomb;
vector<char> s;
string answer;
int idx = 0;
int main()
{
    cin >> str >> bomb;
    for (int i = 0; i < str.length(); i++)
    { //문자열을 더하다가 폭탄 만나면 터트려줌.
        s.push_back(str[i]);
        //꼭 입력받은 문자열의 크기가 폭탄의 길이보다 작다면? 폭발시킬 필요가 없다.
        if (s.size() >= bomb.size())
        {
            bool flag = true;
            if (s[s.size() - 1] == bomb[bomb.size() - 1])
            { //끝에 폭탄이 걸렷다면 그 이전 폭탄의 길이 까지 문자열을 검사해줌.
                for (int j = 0; j < bomb.size(); j++)
                {
                    if (s[s.size() - bomb.size() + j] != bomb[j])
                    {                 //문자열의 끝에서 폭탄의 길이만큼 뺴준뒤 차근차근 더해서 폭탄이 있는지 없는지 체크함.
                        flag = false; //폭발못할경우 false 폭발할경우 true
                        break;
                    }
                }
                if (flag == true)
                { //폭탄을 만났으면 해당하는 인덱스까지 빼준다.
                    for (int k = 0; k < bomb.size(); k++)
                    {
                        s.pop_back();
                    }
                }
            }
        }
    }
    if (s.size() == 0)
    { //만약 size가 0이라면 모든 문자열이 폭발된것.
        cout << "FRULA";
    }
    else
    {
        for (auto v : s)
        {
            cout << v;
        }
    }
}