/* DNA  1969 */
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int n, m, cnt;
vector<string> str;
vector<int>sum(4, 0); // 0 == A , 1 == C, 2==G, 3 ==T
vector<char> DNA;
string answer = "";
int main()
{
    cin >> n >> m;
    DNA.push_back('A'), DNA.push_back('C'), DNA.push_back('G'), DNA.push_back('T');
    for (int i = 0; i < n; i++)
    {
        string s;
        cin >> s;
        str.push_back(s);
    }
    for (int i = 0; i < m; i++)
    {
        int Max = 0, maxIdx = 0;
        for (int j = 0; j < n; j++) //각 DNA의 자리수별로 등장하는 염기서열의 빈도수를 sum에다 집어넣음.
        {
            if (str[j][i] == 'A')
            {
                sum[0]++;
            }
            else if (str[j][i] == 'C')
            {
                sum[1]++;
            }
            else if (str[j][i] == 'G')
            {
                sum[2]++;
            }
            else if (str[j][i] == 'T')
            {
                sum[3]++;
            }
        }
        // cout<<endl;
        for (int k = 0; k < 4; k++) //가장 많이 나온 문자열을 결정함.
        {
            if (Max < sum[k])
            {
                Max = sum[k];
                maxIdx = k;
            }
            else if (Max == sum[k])  //같을시 문자는 사전순으로 오름차순이니 maxidx를 건드리면 안됨. 왜냐하면 내가 sum에다 넣을때 이미 오름차순으로 넣음.
            {
                continue;
            }
        }
        answer += DNA[maxIdx]; //가장 많이 나온 DNA염기서열을 넣어줌.
        sum.assign(4, 0); //빈도수 초기화
    }
    cout << answer << endl;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (answer[j] != str[i][j]) //내가 찾은 염기서열과 다른 염기서열을 비교해서 다른 글자수가 있다면 ++해줌.
            {
                cnt++;
            }
        }
    }
    cout << cnt;
}