#include <iostream>
#include <vector>
using namespace std;
int n;
vector<vector<int> > ary;
int dir[4][2] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
void find_blank(int idx, vector<int> p[]);
int main()
{
    cin >> n;
    ary.resize(n, vector<int>(n, 0));
    int score[5];
    score[0] = 0, score[1] = 1, score[2] = 10, score[3] = 100, score[4] = 1000;
    vector<int> p[n * n + 1];
    int idx, a;
    for (int i = 0; i < n * n; i++)
    {
        cin >> idx;
        for (int j = 0; j < 4; j++)
        {
            cin >> a;
            p[idx].push_back(a);
        }
        find_blank(idx, p);
    }
    int answer = 0,value=0,cnt=0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            value = ary[i][j];
            cnt = 0;  
            for (int k = 0; k < 4; k++) //인접칸 수 탐색
            {
                int nx = i + dir[k][0], ny = j + dir[k][1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;
                for (int z = 0; z < 4; z++) //인접칸 좋아하는 사람 탐색.
                {
                    if (ary[nx][ny] == p[value][z])
                        cnt++;
                }
            }
            answer += score[cnt];
        }
    }
    cout << answer << endl;

}
void find_blank(int idx, vector<int> p[])
{
    int v[n][n];
    int f[n][n];
    int max = -1, mrow = 0, mcol = 0, fcnt = 0, cnt = 0; //max값을 왜 -1로?
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (ary[i][j] != 0) //이미 들어있는 칸은 탐색할 필요가 없다.
                continue;
            cnt = 0;
            fcnt = 0;
            for (int d = 0; d < 4; d++)
            {
                int nx = i + dir[d][0], ny = j + dir[d][1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                    continue;
                if (ary[nx][ny] == 0)
                    cnt++;
                // cout<<"좋아하는 사람 찾기 "<<endl;
                for (int k = 0; k < 4; k++){
                    if (ary[nx][ny] == p[idx][k])// 좋아하는 놈일때.
                        fcnt++;
                }
            }
            v[i][j] = cnt;  // 인접칸수
            f[i][j] = fcnt; // 좋아하는 사람 칸수
            if (max < fcnt)
                max = fcnt, mrow = i, mcol = j;
            else if (max == fcnt)
            { // 만약 좋아하는 사람 칸수가 동일하다면
                if (v[i][j] > v[mrow][mcol])// 인접칸수중 비어있는 칸수를 비교함.
                    mrow = i, mcol = j;
                else if (v[i][j] == v[mrow][mcol])
                { // 인접칸수도 같다면, i가 작은거를 정함. j가 작은거를 정함.
                    if (i < mrow) // i가 작다면 바꿔줌.
                        mrow = i, mcol = j;
                    else if (i == mrow){ // i가 같을경우 j가 작은걸로 바꿔줌.
                        if (j < mcol)
                            mrow = i, mcol = j;
                    }
                }
            }
        }
    }
    ary[mrow][mcol] = idx;
}