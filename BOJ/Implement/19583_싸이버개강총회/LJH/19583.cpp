/*
백준 19583 실버2 싸이버개강총회
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <sstream>
using namespace std;
string S, E, Q;
pair<string, int> p;
map<string, int> m;
int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(0), cout.tie(0);
    cin >> S >> E >> Q;
    int s = S[0] * 600 + S[1] * 60 + S[3] * 10 + S[4];
    int e = E[0] * 600 + E[1] * 60 + E[3] * 10 + E[4];
    int q = Q[0] * 600 + Q[1] * 60 + Q[3] * 10 + Q[4];
    int cnt = 0;
    while (cin.eof() == false)
    {
        string Time, name;
        cin >> Time >> name;

        if (Time == "" && name == "")
            break;
        int t;
        t = Time[0] * 600 + Time[1] * 60 + Time[3] * 10 + Time[4];
        if (t > q)
        {
            continue;
        }
        if (t <= s) //제시간에 입장.
        {
            m.insert(make_pair(name, 1));
        }
        //여기서 개강총회가 시작하고~ 스트리밍이 끝나기 전까지의 채팅 기록이 있어야함.
        else if (t >= e && t <= q)
        {
            // map에 들어있고, 채팅이 있어야함.
            if (m.find(name) != m.end()) // map에 이름이 있고, 시간이 개강총회시작시간 ~ 개강총회스트리밍끝나는 시간 안에 채팅을 쳐야함.
            {
                cnt++;
                //증가시켜줫으니 map에서 지워야함.
                m.erase(m.find(name));
            }
        }
    }
    cout << cnt;
}