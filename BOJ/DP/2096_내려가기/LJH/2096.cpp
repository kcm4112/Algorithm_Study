/*
내려가기 골드5
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <limits.h>
using namespace std;
int main()
{
    int n;
    cin >> n;
    int Max[3] = {0,};
    int Big[3] = {0,};
    int Min[3] = {0,};
    int Small[3] = {0,};
    int graph[n][3];
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            cin >> graph[i][j];
        }
    }
    for (int i = 0; i < 3; i++)
    {
        Max[i] = graph[0][i];
        Min[i] = graph[0][i];
    }
    for (int i = 1; i < n; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            int value = Max[j];
            int svalue = Min[j];
            Big[j] = Max[j] + graph[i][j];
            Small[j] = Min[j] + graph[i][j];
            if (i > 0)
            {
                if (j - 1 >= 0)
                {
                    Big[j] = max(Big[j], Max[j - 1] + graph[i][j]);
                    Small[j] = min(Small[j], Min[j - 1] + graph[i][j]);
                }
                if (j + 1 < 3)
                {
                    Big[j] = max(Big[j], Max[j + 1] + graph[i][j]);
                    Small[j] = min(Small[j], Min[j + 1] + graph[i][j]);
                }
            }            
        }
        for (int k = 0; k < 3; k++)
        {
            Max[k] = Big[k];
            Min[k] = Small[k];
        }
    }
    int big = 0, small = INT_MAX;
    for (int i = 0; i < 3; i++)
    {
        big = max(big, Max[i]);
        small = min(small, Min[i]);
    }
    cout << big << " " << small;
}