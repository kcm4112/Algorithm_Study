#include <iostream>
#include <vector>
#include <algorithm>
#include <limits.h>

using namespace std;
int n;
vector<int> fir;
vector<int> sec;
vector<int> temp;
int answer = INT_MAX, ans = 0;
bool flag = true;
void light(int index);
bool funct();
int main()
{
    cin >> n;
    fir.resize(n, 0);
    temp.resize(n, 0);
    sec.resize(n, 0);
    for (int i = 0; i < n; i++)
    {
        scanf("%1d", &fir[i]);
        temp[i] = fir[i];
    }
    for (int i = 0; i < n; i++)
        scanf("%1d", &sec[i]);

    light(0);    // 처음 스위치를 누른다.
    ans++;       // 스위치를 눌렀으므로, 누른 횟수를 +1 해줌.
    if (funct()) // 리턴값이 트루면 성공이라는 뜻이므로, 최소값을 갱신해줌.
        answer = min(answer, ans);
    else // 만들수 없을 떄
        flag = false;
    ans = 0;
    fir = temp;  // 기존 배열 다시 들고옴.
    if (funct()) // 처음 스위치를 누르지 않는 경우
    {
        answer = min(answer, ans);
        flag = true;
    }
    if (flag == false) // 만약 두 경우의 수를 통해서 만들수 없는 상태라면 -1을 출력, 아니면 최소횟수를 출력
        cout << -1 << endl;
    else
        cout << answer << endl;
}
void light(int index) // 전구에 영향을 주는 함수. 첫,끝 스위치가 아니라면 index-1, index, index+1 모두 영향을 줌.
{
    if (index > 0)
    { // 첫 전구가 아니라면.
        if (fir[index - 1])
            fir[index - 1] = 0;
        else
            fir[index - 1] = 1;
    }
    if (index < n - 1)
    { // 끝전구가 아니라면
        if (fir[index + 1])
            fir[index + 1] = 0;
        else
            fir[index + 1] = 1;
    }
    if (fir[index])
        fir[index] = 0;
    else
        fir[index] = 1;
}
bool funct()
{
    for (int i = 1; i < n; i++)
    {
        if (fir[i - 1] != sec[i - 1])
        { // 이전 상태가 결과물이랑 다르다면
            ans++;
            light(i);
        }
    }
    for (int i = 0; i < n; i++)
    {
        if (fir[i] != sec[i]) // 다를 시.
            return false;
    }
    return true;
}