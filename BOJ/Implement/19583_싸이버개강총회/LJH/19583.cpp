/*
백준 실버1 2002 추월
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;
map<string, int> m;
vector<int> f;
int n;
int cnt = 0;
int main()
{
    ios_base::sync_with_stdio(false), cin.tie(0), cout.tie(0);
    cin >> n;
    f.resize(n, 0);
    for (int i = 0; i < n; i++)
    {
        string name;
        cin >> name;
        m[name] = i;
    }
    for (int i = 0; i < n; i++)
    {
        string name;
        cin >> name;
        f[i] = m[name]; // f[나간순서]에 들어온순서를 저장함.
    }
    for (int i = 0; i < n - 1; i++)
    {
        for (int j = i + 1; j < n; j++)
        {
            if (f[i] > f[j])
            {
                cnt++;
                break;
            }
        }
    }
    cout << cnt;
}