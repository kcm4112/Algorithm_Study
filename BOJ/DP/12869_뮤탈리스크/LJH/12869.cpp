/*
뮤탈리스크 골드4
*/
#include <iostream>
#include <algorithm>
#include <vector>
#include <limits.h>
using namespace std;
int n;
vector<vector<vector<int>>> graph;
void attack(int one, int two, int three, int depth);
int ans = INT_MAX;
int main()
{
    cin >> n;
    int scv[n]={0,0,0};
    graph.resize(61, vector<vector<int>>(61, vector<int>(61, 0)));
    for (int i = 0; i < n; i++)
    {
        cin >> scv[i];
    }    
    attack(scv[0],scv[1],scv[2],0);
    cout<<ans;
}
void attack(int one, int two, int three, int depth) // 9-> 3- > 1 차례대로 때려야함.
{        
    if (one <= 0 && two <= 0 && three<=0)
    {
        if (ans > depth) //최소값을 계속해서 넣어줌.
        {
            ans = depth;
            return;
        }
    }
    if(one <0) one =0;
    if(two<0 ) two=0;
    if(three<0) three=0;
    //여기 종료조건.
    if (graph[one][two][three] <= depth && graph[one][two][three] != 0) return;
    //이미 한번 방문했는데 그 때의 저장된 깊이보다 큰 깊이로 연산이 들어온다면 그때는 무시해도 됨.    

    graph[one][two][three]=depth; //그 때 방문했던 경우의 수를 현재의 깊이로 넣어줌.

    if (n == 1) //scv가 하나일때
    {
        attack(one - 9, two, three, depth + 1); // scv가 하나라면 하나의 공격만.
    }
    if (n == 2) //scv가 두개일때
    {
        attack(one - 9, two - 3, three, depth + 1);
        attack(one - 3, two - 9, three, depth + 1);
    }
    if (n == 3) //scv가 세개일때
    {
        attack(one - 9, two - 3, three - 1, depth + 1);
        attack(one - 9, two - 1, three - 3, depth + 1);
        attack(one - 3, two - 9, three - 1, depth + 1);
        attack(one - 3, two - 1, three - 9, depth + 1);
        attack(one - 1, two - 9, three - 3, depth + 1);
        attack(one - 1, two - 3, three - 9, depth + 1);
    }
}