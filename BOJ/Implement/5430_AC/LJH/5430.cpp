/*
백준 골드5 AC
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <deque>
using namespace std;
deque<int> dq;
int T;
bool flag, err;
void ac(string str, vector<string>& n);
int main()
{
    ios_base::sync_with_stdio(false), cin.tie(0), cout.tie(0);
    cin >> T;
    for (int i = 0; i < T; i++)
    {
        flag = true, err = 0; // true가 정방향, false가 역방향
        string str, arr, now;
        int Size;
        vector<string> answer;
        cin >> str >> Size >> arr;
        for (int j = 0; j < arr.size(); j++)
        {
            if (isdigit(arr[j]) != 0) //숫자라는소리임
            {
                now += arr[j];
            }
            else
            {
                if (!now.empty())
                {
                    dq.push_back(stoi(now));
                    now = "";
                }
            }
        }
        for (int k = 0; k < str.size(); k++)
        {
            if (str[k] == 'R')
            {
                if (flag == false)
                {
                    flag = true;
                }
                else
                    flag = false;
            }
            else
            { // str[k]==D
                if (dq.size() == 0)
                {
                    cout << "error" << endl;
                    err = 1;
                    break;
                }
                else
                {
                    if (flag == 1)
                    {
                        dq.pop_front();
                    }
                    else
                        dq.pop_back();
                }
            }
        }
        if (err != 1)
        {
            if (flag == 1)
            { //정방향
                cout << '[';
                while (dq.size() != 0)
                {
                    if (dq.size() != 1)
                        cout << dq.front() << ',';
                    else
                    {
                        cout << dq.front();
                    }
                    dq.pop_front();
                }
                cout << ']';
            }
            else
            {   //역방향
                cout << '[';
                while (dq.size() != 0)
                {
                    if (dq.size() != 1)
                    {
                        cout << dq.back() << ',';
                    }
                    else
                    {
                        cout << dq.back();
                    }
                    dq.pop_back();
                }
                cout << ']';
            }
            cout << endl;
        }
    }
}