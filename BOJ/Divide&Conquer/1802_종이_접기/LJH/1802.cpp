/*
백준 실버2 종이접기
종이가 시계방향으로 꺾여있으면 out, 반시계방향으로 꺾여잇으면 IN
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <deque>
using namespace std;
int n;
vector<string> answer;
bool paper(string str, int s, int f);
int main()
{
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        string str;
        cin >> str;
        if (str.length() == 1) //1이면 무조건 접을 수 있음.
        {
            cout << "YES" << endl;
            continue;
        }
        //  cout<<str<<endl;
        if (paper(str, 0, str.length() - 1) == true)
        {
            cout << "YES" << endl;
        }
        else
        {
            cout << "NO" << endl;
        }
    }
}
bool paper(string str, int s, int f)
{
    if (s >= f)
    {
        return true;
    }
    int left = s, right = f;
    while (left < right)
    {
        if (str[left] != str[right])
        {
            left++, right--;
        }
        else
        {
            return false;
            // break;
        }
    }
    return paper(str, s, right - 1);
}
