/*
골드5 톱니바퀴
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <deque>
using namespace std;
deque<int> one;
deque<int> two;
deque<int> three;
deque<int> four;
int k, answer = 0;
vector<int> point(6, 0);
vector<int> direct(5, 0);
void make_point();
void run(int n, int d);
void circle_run(deque<int> &circle, int d);
void check();
vector<string> str;
int main()
{
    string s;
    for (int i = 0; i < 4; i++)
    {
        cin >> s;
        str.push_back(s);
    }
    for (int i = 0; i < 8; i++)
    {
        one.push_back(str[0][i] - 48);
        two.push_back(str[1][i] - 48);
        three.push_back(str[2][i] - 48);
        four.push_back(str[3][i] - 48);
    }
    cin >> k;
    int num, dir;
    for (int i = 0; i < k; i++)
    {
        cin >> num >> dir;
        run(num, dir);
    }
    check();
    cout << answer;
}
void make_point()
{
    point[0] = one[2];
    point[1] = two[6];
    point[2] = two[2];
    point[3] = three[6];
    point[4] = three[2];
    point[5] = four[6];
}
void run(int n, int d)
{
    make_point();
    for (int i = 1; i <= 4; i++)
    {
        direct[i] = 0;
    }
    direct[n] = d;
    if (n == 1) // 첫번째 톱니바퀴인 경우
    {
        if (point[0] != point[1]) // 다른경우는 회전함.
        {
            direct[n + 1] = d * -1;
            if (point[2] != point[3])
            {
                direct[n + 1] = d * -1;
                direct[n + 2] = d;
                if (point[4] != point[5])
                {
                    direct[n + 2] = d;
                    direct[n + 3] = d * -1;
                }
            }
        }
    }
    if (n == 2) // 두번째 톱니바퀴인 경우
    {
        if (point[0] != point[1])
        {
            direct[n - 1] = d * -1;
        }
        if (point[2] != point[3])
        {
            direct[n + 1] = d * -1;
            if (point[4] != point[5])
            {
                direct[n + 1] = d * -1;
                direct[n + 2] = d;
            }
        }
    }
    if (n == 3)
    {
        if (point[4] != point[5])
        {
            direct[n + 1] = d * -1;
        }
        if (point[2] != point[3])
        {
            direct[n - 1] = d * -1;
            if (point[0] != point[1])
            {
                direct[n - 1] = d * -1;
                direct[n - 2] = d;
            }
        }
    }
    if (n == 4)
    {
        if (point[4] != point[5]) // 다른경우는 회전함.
        {
            direct[n - 1] = d * -1;
            if (point[2] != point[3])
            {
                direct[n - 1] = d * -1;
                direct[n - 2] = d;
                if (point[0] != point[1])
                {
                    direct[n - 2] = d;
                    direct[n - 3] = d * -1;
                }
            }
        }
    }
    circle_run(one, direct[1]);
    circle_run(two, direct[2]);
    circle_run(three, direct[3]);
    circle_run(four, direct[4]);
}
void circle_run(deque<int> &circle, int d)
{
    if (d == 1)
    {
        int d = circle[circle.size() - 1];
        circle.pop_back();
        circle.push_front(d);
    }
    else if (d == -1)
    {
        int d = circle[0];
        circle.pop_front();
        circle.push_back(d);
    }
    else
        return;
}
void check()
{
    if (one[0] == 1)
        answer += 1;
    if (two[0] == 1)
        answer += 2;
    if (three[0] == 1)
        answer += 4;
    if (four[0] == 1)
        answer += 8;
}