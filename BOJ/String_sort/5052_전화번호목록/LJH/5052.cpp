/*
백준 골드5 배
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;
int n, m;
int t = 0;
vector<int> crain;
vector<int> box;
bool comp(int a, int b)
{
    return a > b;
}
int main()
{
    cin >> n;
    crain.resize(n, 0);
    for (int i = 0; i < n; i++)
    {
        cin >> crain[i]; //내림차순으로 들어감.
    }
    sort(crain.begin(), crain.end(), comp);
    cin >> m;
    box.resize(m, 0);
    for (int i = 0; i < m; i++)
    {
        cin >> box[i];
    }
    sort(box.begin(), box.end(), comp);
    if (box[0] > crain[0])
    {
        cout << -1;
    }
    else
    {
        while (box.size() != 0)
        {
            t++;
            for (int i = 0; i < n; i++)
            { //포크레인으로 돌리고
                for (int j = 0; j < box.size(); j++)
                {
                    if (crain[i] >= box[j])
                    {
                        box.erase(box.begin() + j);
                        break;
                    }
                }
            }
        }
        cout << t;
    }
}