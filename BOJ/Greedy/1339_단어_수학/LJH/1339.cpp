// 백준 1339 골드4 단어수학
#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <math.h>
#include <string>
using namespace std;
int n;
vector<string> voca;
vector<int> bulk;
vector<pair<int, char>> p;
map<char, int> m;
vector<string> math;
vector<string> ans_string;
bool comp(int a, int b)
{
    return a > b;
}
int main()
{
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        string s;
        cin >> s;
        voca.push_back(s);
        bulk.push_back(voca[i].size()); //입력받은 문쟈열의 길이를 배열에 저장함.
    }
    for (int i = 0; i < n; i++) //문자열의 크기순서 대로 배열을 정렬.이러면 배열의 첫 원소가 가장 긴 문자열일거임.
    {
        for (int j = i + 1; j < n; j++)
        {
            if (bulk[i] < bulk[j])
            {
                swap(bulk[i], bulk[j]);
                swap(voca[i], voca[j]);
            }
        }
    }
    for (int i = 1; i < n; i++)
    {
        string s = voca[i];
        voca[i].erase();
        int Size = bulk[0] - bulk[i]; //가장 긴 문자열을 기준으로 각 문자열과의 차이만큼 빈 공간을 만들어줌.
        // cout<<Size<<endl;
        int idx = 0;
        while (idx != Size)
        {
            // cout<<1<<endl;
            voca[i] += "a"; //아무문자나 넣은것임. 어차피 입력문자는 대문자라 소문자는 상관없음!
            idx++;
        }
        voca[i] += s; //공백을 포함한 문자열 완성
    }
    int Max_size = voca[0].size(); //가장 긴 문자열의 길이를 변수로 저장해둠. 계속 입력하기 귀찮음.
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < Max_size; j++)
        {
            p.push_back(make_pair(pow(10, Max_size - j - 1), voca[i][j]));
            //각 문자별로 위치한 자리를 10의 지수로 나타냄. 예를 들어 ABCDE면 A는 10의 4승, B는 10의 3승 이렇게..
        }
    }
    sort(p.begin(), p.end());
    // pair 의 first는 자리수, second는문자
    // map의 첫번쨰는 문자, second는 숫자
    for (int i = 0; i < p.size(); i++)
    {
        if (p[i].second == 'a') //내가 임시로 만들어준 공간이면 넘어감.
        {
            continue;
        }
        else
        {
            if (m.find(p[i].second) == m.end()) //처음 map에 넣는다면 그냥 그 문자가 가지는 숫자를 넣어줌.
            {
                m.insert(make_pair(p[i].second, p[i].first));
            }
            else
            { //찾앗다면? 원래 기존의 숫자와 더해줌. EX) ABBCE 면 B는 1000과 100을 나타냄. 따라서 map에는 1100이 저장되야함.
                int value = m.find(p[i].second)->second;
                m[p[i].second] = value + p[i].first;
            }
        }
    }
    vector<pair<int, char>> result;
    // result안에 각 문자와 그 문자가 표현하는 수를 넣어줌. EX) A 10000, B 1010
    for (auto iter = m.begin(); iter != m.end(); iter++)
    {
        result.push_back(make_pair(iter->second, iter->first));
    }
    for (int i = 0; i < result.size(); i++)
    {
        for (int j = i + 1; j < result.size(); j++)
        {
            if (result[i].first < result[j].first) //자리수가 큰 순서대로 정렬함.
            {
                swap(result[i], result[j]);
            }
        }
    }
    m.clear();
    //문자열을 이제 숫자로 매핑해야함.
    int answer = 9; //각 알파벳은 0~9까지의 숫자를 표현할 수 있음. 우선순위가 높은 순서대로 9,8,7 ... 이렇게 할당함.
    for (int i = 0; i < result.size(); i++)
    {
        if (m.find(result[i].second) == m.end()) //각 알파벳의 숫자를 넣어줌. 9~0
        {
            m.insert(make_pair(result[i].second, answer));
            answer--; //한번 넣어준 숫자는 못넣기 때문에 감소시켜줌.
        }
    }
    for (int i = 0; i < n; i++)
    {
        string s;
        for (int j = 0; j < Max_size; j++)
        {
            int num = m.find(voca[i][j])->second;
            s += to_string(num);
        }
        ans_string.push_back(s);
    }
    int sum = 0;
    for (auto a : ans_string)
    {
        int num = stoi(a);
        sum += num;
    }
    cout << sum;
}