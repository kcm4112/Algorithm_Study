/*
백준 실버2 마인크래프트
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>
using namespace std;
int n, m, b, height, bigT = INT_MAX;
vector<vector<int>> graph;
int main()
{
    cin >> n >> m >> b;
    graph.resize(n, vector<int>(m, 0));
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> graph[i][j];
        }
    }
    for (int k = 0; k < 257; k++) //모든 높이에 대해서 검사함.
    {
        int addblock = 0; //높이가 모자랄때 쌓는 블록의 개수를 저장함. 
        int subblock = 0; //높이가 남을때 제거하는 블록의 개수를 저장함.
        int t = 0; //현재 높이에 대해서 검사할 때 시간을 저장함.
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                int num = graph[i][j] - k; //해당 지점에서 높이와의 차이를 저장. -> 필요하거나 남는 블록 수

                if (num < 0) { //차이가 음수라면 그 차이만큼 땅의 높이를 올리고 addblock을 증가시킴. 시간 : 1초
                    addblock += num; //올릴때 쓰이는 블록 수
                }
                else if (num > 0) { //치이가 양수라면 그 차이만큼 땅의 높이를 내리고 subblock을 증가시킴. 시간 : 2초
                    subblock += num;  //내릴떄 쓰이는 블록 수
                }
            }
        }
        if (addblock * -1 > b + subblock) { //내가 더해주고 빼준 블록의 수의 합이 내가 들고있는 블록의 수보다 많다면 이것은 잘못된 것.
            continue;
        }
        else { //subblock-addblock>=b
            t = addblock * -1 + subblock * 2;
            if (t <= bigT) {
                bigT = t;
                height = k;
            }
        }
        //높이 k에 대해서 모든 addblock과 subblock을 수집햇고.
                //만약 높이가 갚을 경우는 ? 시간이 영초겟죠
                /*
                int num = graph[i][j];
                while (num != k)
                {
                    if (num > k) //높을때는 높이 -1 현재블록수 + 1 --> 시간 2초걸림
                    {
                        num -= 1;
                        nowblock += 1;
                        t += 2;
                    }
                    else //낮을때는 높이 +1 블록 -1 --> 시간 1초걸림
                    {
                        num += 1;
                        nowblock -= 1;
                        t += 1;
                    }
                    if (nowblock < 0)
                    { //블록의 개수가 0보다 작아진다면 해당 높이를 맞출수는없음.
                        flag=false;
                    }
                    if(flag==false){
                        break;
                    }
                }
                --> 시간 초과 코드 */
    }
    cout << bigT << " " << height << endl;
}