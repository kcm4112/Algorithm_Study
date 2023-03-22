#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;
priority_queue<int, vector<int>, greater<int>> pq;
int main()
{
    int n, sum = 0, num = 0;
    cin >> n;
    for (int t = 0; t < n; t++)
    {
        cin >> num;
        pq.push(num); // pq에는 자동 정렬
    }
    while (pq.size() != 1)
    { // 카드가 한묶음이 될 때까지 반복
        int first = pq.top();
        pq.pop();
        int second = pq.top();
        pq.pop();
        int next = first + second;
        pq.push(next);
        sum += next;
    }
    cout << sum << endl;
}