#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int n;
vector<int> drink;
vector<int> answer(2, 0);
int main()
{
    cin >> n;
    drink.resize(n, 0);
    for (int i = 0; i < n; i++)
    {
        cin >> drink[i];
    }
    sort(drink.begin(), drink.end()); // 오름차순 정렬
    int start = 0, end = n - 1;
    answer[0] = drink[start], answer[1] = drink[end];
    int sum = answer[0] + answer[1];
    while (1)
    { // 이분탐색 시작
        if (start >= end) //이분탐색이 종료되는 기준 1
        {
            break;
        }
        if (abs(drink[start] + drink[end]) < abs(sum)) //절대값이 더 작다는 뜻은 0에 가깝다는 뜻임. 그 때의 sum값을 기준으로 계속 0에 가까운지 비교를 해야함.
        { 
            sum = drink[start] + drink[end];
            answer[0] = drink[start];
            answer[1] = drink[end];
        }
        if (drink[start]+drink[end] > 0) //0보다 크다면 오른쪽 탐색범위를 한칸 감소
        {
            end--;
        }
        else if (drink[start]+drink[end] < 0) //0보다 작다면ㅁ 왼쪽 탐색 범위를 한칸 증가
        {
            start++;
        }
        else if (drink[start]+drink[end] == 0) //두 용액의 농도 합이 0이라면 그 즉시 중단.
        {
            answer[0] = drink[start];
            answer[1] = drink[end];
            break;
        }
    }
    cout << answer[0] << " " << answer[1] << endl;
}