/*
백준 실버3 절사평균
*/
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
vector<double> a;
int main()
{
    int n, k;
    double avg1 = 0, avg2 = 0; // avg1은 절사평균, avg2는 보정평균
    cin >> n >> k;
    a.resize(n, 0);
    for (int i = 0; i < n; i++)
    {
        cin >> a[i];
    }
    sort(a.begin(), a.end());
    for (int i = 0 + k; i < n - k; i++)
    {
        avg1 += a[i];
    }
    for (int i = 0; i < k; i++)
    {
        avg2 += a[k];
    }
    for (int i = k; i < n - k; i++)
    {
        avg2 += a[i];
    }
    for (int i = n - k; i < n; i++)
    {
        avg2 += a[n - k - 1];
    }
    printf("%.2f\n", avg1 / (n - 2 * k) + 0.00000001);
    printf("%.2f", avg2 / n + 0.00000001);
}