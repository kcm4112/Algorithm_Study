#include <iostream>
#include <vector>
#include <algorithm>
#include <limits.h>

using namespace std;
int n;
vector<int> fir;
vector<int> sec;
vector<int> temp;
int answer = INT_MAX,ans=0;
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
    
    light(0); // 0번 누른다.
    ans++; //0번 눌렀으므로, 
    if (funct()) // 만들 수 있는 상태.
        answer = min(answer, ans);
    else // 만들수 없을 떄
        flag = false;
    ans = 0;
    fir = temp; //기존 배열 다시 들고옴.
    if (funct())
    {
        answer = min(answer, ans);
        flag = true;
    }
    if (flag == false)
        cout << -1 << endl;
    else
        cout << answer << endl;
}
void light(int index)
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
{ // num은 몇번째 스위치를 처음으로 누를거냐.
    for (int i = 1; i < n; i++)
    {
        if (fir[i - 1] != sec[i - 1])
        { // 결과가 다르다면.
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