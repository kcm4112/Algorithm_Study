/*
백준 실버1 단어맞추기
해당 단어가 있으면 그 단어 다음에 바로 오는 단어를 출력
해당 단어가 마지막이면 그 단어를 출력

*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <limits.h>
using namespace std;
int n;
vector<int> eng;
vector<string> result;
int check(string str);
int main()
{
    cin >> n;
    eng.resize(26, 0);
    for (int i = 0; i < n; i++)
    {
        string str;
        string answer;
        cin >> str;
        int idx = check(str);
        if (idx == -1)
        {
            result.push_back(str);
            continue;
        }
        for (int x = 0; x < idx; x++)
        {
            answer += str[x];
        }
        for (int j = idx; j < str.length(); j++)
        {
            eng[str[j] - 65]++;
        }
        int cur = 9999;
        //해당 idx의 알파벳보다 제일 가까운 큰수를 idx자리에 넣고 그 자리 뒤부터는 작은순서대로 쫘좌작 넣기.
        for (int k = idx; k < str.length(); k++)
        {
            if (eng[str[k] - 65] == 0) //해당 알파벳이 한번이라도 나와야하고.
                continue;
            if (str[k] > str[idx])
            {
                if (cur > str[k])
                {
                    cur = str[k];
                }
            }
        }
        answer += cur;
        eng[cur - 65]--;
        for (int y = 0; y < 26; y++)
        {
            if (eng[y] == 0)
            {
                continue;
            }
            while (eng[y] != 0)
            {
                answer += y + 65;
                eng[y]--;
            }
        }
        result.push_back(answer);
    }
    for (auto a : result) {
        cout << a << endl;
    }
}
int check(string str) //갈수록 아스키코드가 커져야함. 작아지는 구간이 있으면 거기서부터 바꿔줄수있음.
{
    int num = 0;
    int res = 0;
    for (int i = str.length() - 1; i >= 0; i--)
    {
        if (num <= str[i])
        {
            num = str[i];
        }
        else
        {
            return i;
        }
    }
    return -1;
}