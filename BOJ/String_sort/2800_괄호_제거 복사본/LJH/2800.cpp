// 2800 괄호 제거
#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;
string str;
vector<int> symbol;
vector<pair<int, int>> p;
vector<int> visit;
void check(int i, int Count);
map<string, int>m;
int main()
{
    cin >> str;
    for (int i = 0; i < str.length(); i++)
    {
        if (str[i] == '(')
        {
            symbol.push_back(i); //왼쪽 괄호의 index를 넣음.
        }
        else if (str[i] == ')')
        {
            //가장 최근에 symbol에 넣어준 왼쪽 괄호의 index값과 가장 먼저 등장한 오른쪽 괄호가 서로 대응되기 때문에 
            //그때의 index를 pair에 넣어줌.
            int num = symbol[symbol.size() - 1];
            symbol.pop_back();
            p.push_back(make_pair(num, i));
        }
    }
    visit.resize(str.length(), 0); //visit의 size를 str과 동일하게 초기화해줌.
    check(0, 0);
    for (auto iter = m.begin(); iter != m.end(); iter++) //map에는 자동으로 오름차순 정렬되고, first를 전부 출력함.
    {
        cout << iter->first << endl;
    }
}
void check(int i, int Count)
{
    if (Count > 0)
    {
        string s = "";
        for (int j = 0; j < visit.size(); j++)
        {
            if (visit[j] == true)
                continue; // true라면 문자열에 포함x
            else
            {
                s += str[j]; //내가 포함하고싶지 않은 문자열을 뺴고 넣어줌.
            }
        }
        m.insert(make_pair(s, 1)); //괄호를 제거하더라도 중복된 문자열이 있을수 있으므로, map을 사용함.
    }
    for (int k = i; k < p.size(); k++)
    {
        if (visit[p[k].first] == true && visit[p[k].second] == true) //만약 이미 했던 경우의 수라면 넘어감.
        { //이미 사용한 괄호라면
            continue;
        }
        //괄호를 저장하지 못하게 초기화
        visit[p[k].first] = true;
        visit[p[k].second] = true;
        check(k, Count + 1);
        //괄호를 다시 포함하게 초기화
        visit[p[k].first] = false;
        visit[p[k].second] = false;
    }
}