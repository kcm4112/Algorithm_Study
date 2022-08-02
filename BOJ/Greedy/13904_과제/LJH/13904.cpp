//백준 골드3 13904 과제
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int n, day = 1, sum = 0;
vector<int> finish, score;
vector<pair<int, int>> p, np;
vector<int> visit(1001, 0);
bool cmp(pair<int, int>& a, pair<int, int>& b) //pair를 sort하기 위한 함수.
{
    //우선 이득이 큰 순서대로 정렬을 한다. 만약 이득이 같다면 과제 마감일이 큰 순서대로 정렬
    if (a.second == b.second)
    {
        return a.first > b.first;
    }
    else
    {
        return a.second > b.second;
    }
}
int main()
{
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        int deadline, profit;
        cin >> deadline >> profit;
        p.push_back(make_pair(deadline, profit));
    }
    sort(p.begin(), p.end(), cmp);
    int end_day = 0;
    for (int i = 0; i < n; i++)
    {
        end_day = p[i].first; //과제의 마감일을 end_day로 정한다.
        int score = p[i].second; //과제의 점수를 scroe로 지정.
        for (int j = end_day; j >= 1; j--) //마감일부터 하루씩 깎아서 과제를 넣는다.
        {
            if (visit[j] == true) //만약 j가 day라고 생각하면 편함. 해당 날에 이미 과제가 배정되었다면 넘어감.
            {
                continue;
            }
            else //만약 해당 날에 과제가 배정되지 않았다면 
            {
                sum += score; //과제의 점수를 더하고
                visit[j] = true; //해당날에는 풀어야할 과제가 선택되었다는것을 알린다.
                break;
            }
        }
    }
    cout << sum;
}