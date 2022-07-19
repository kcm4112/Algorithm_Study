​
## **문제해석** 
​
**index가 1부터 시작하지만 나는 0번지부터 넣어줬다. 따라서 a \[r\]\[c\]가 아닌 a \[r-1\]\[c-1\]을 찾아줘야 한다.**
​
-   while문을 사용하는데 만약 100초가 지났다면 -1을 출력함.
-   Cnt값으로 구현.
​
**어떤 연산을 하는지 결정하는 것이 중요하다. (R연산 or C연산) -> 행과 열의 개수를 비교함.**
​
-   **bool flag(함수)**
-   **R연산일 때는 true를, C연산일 때는 false를 반환함.**
​
![img](https://user-images.githubusercontent.com/99114456/179795347-442c7776-cf8e-478b-b9af-460be5b84f44.png)

​
R연산
​
-   **행을 고정시키고, 열을 증가시키면서 그 값과 빈도수를 기록해야 함.**
-   **나는 map을 이용해서 key값을 값으로 하고, value값을 빈도수로 지정함.**
-   **map의 형태는 map <int, int> m이다.**
​
![img](https://user-images.githubusercontent.com/99114456/179795460-e74da694-6e9a-4525-8ebb-5a88a348d6e0.png)
​
**숫자마다 빈도수를 저장하기 위해서 map을 썼지만, 정렬하기 위해서 pair를 사용**.
​
-   **pair의 first에는 빈도수를, second에는 값을 저장함.**
-   **pair의 first를 비교해서 오름차순으로 정렬.**
    -   **만약 first값\[빈도수\]이 같다면 second값\[숫자\]을 오름차순으로 정렬함.**
​
![img](https://user-images.githubusercontent.com/99114456/179795515-0e8a7486-c081-42a2-9a72-7d671bd3be5d.png)
​
**a행렬 입력하기**
​
-   **실제로 숫자를 채우는 크기는 pair의 크기 \*2가 될 것이다. (숫자, 빈도수가  들어가야하기때문)**
-   **그리고 각 열의 개수는 최댓값으로 최신화해야 하기 때문에 계속 비교해준다.**
-   **만약 개수가 100개를 초과한다면 100으로 배열의 크기를 잘라준다.**
-   **a배열에 숫자, 빈도수 순서로 채워준다.**
-   **m과 빈도수를 저장하기 위해 쓴 visit를 초기화시켜줌.**
​
![img](https://user-images.githubusercontent.com/99114456/179795543-b1535f19-95be-4d14-9369-7f40a6f0bbfb.png)
​
**연산이 끝난 후에 Cnt(시간 초)를 ++해주고, 옮긴 행렬에서 만약 a \[r\]\[c\]를 찾을 수 있다면 그때의 Cnt를 출력해줌.**
​
![img](https://user-images.githubusercontent.com/99114456/179795579-67747705-64e1-4bf8-a272-082a9b50b095.png)
​
> **C연산은 행과 열 계산을 R연산과 바꿔주면 된다.**
​
---
​
## **👀코드**
​
```
/* 17140 골드4 이차원 배열과 연산
​
​
A[r][c]가 k가 되기 위한 시간 계산
1초마다 R or C 연산을함.
​
기존 행렬 벡터를 지워줘야함. 시발. 개새기.
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;
int r, c, k;
vector<vector<int>> a;
map<int, int> m;
void makeary(int row, int col);
bool flag(int a, int b);
vector<int> visit;
int Row = 0, Col = 0, Cnt = 0;
int main()
{
    cin >> r >> c >> k;
    a.resize(101, vector<int>(101, 0));
    makeary(3, 3);
    int row = 3, col = 3;
    visit.resize(101, 0);     // visit배열 101로 초기화
    if (a[r - 1][c - 1] == k) //만약 바로 값을 찾을 수 있다면 계산할 필요가 없음.
    {
        cout << 0;
    }
    else
    {
        while (1) // a[r][c]가 k가 되거나? Cnt가 100을 넘기면 안됨.
        {
            int rsize = row; // row는 행의 개수
            int csize = col; // col은 열의 개수
            if (Cnt > 100)   // 100초가 지나면 -1을 출력하고 반복문을 탈출시킴.
            {
                cout << -1;
                break;
            }
            if (flag(csize, rsize)) //열과 행을 비교함.  //csize는 열의 개수, rsize는 행의 개수
            {
                //값이 1이면 R연산
                for (int i = 0; i < csize; i++)
                {
                    vector<pair<int, int>> p;
                    for (int j = 0; j < rsize; j++)
                    {
                        if (a[i][j] == 0)
                            continue;
                        visit[a[i][j]]++; //
​
                        m[a[i][j]] = visit[a[i][j]];
                        a[i][j] = 0;
                    }
                    //수,등장횟수로 정렬 등장횟수가 다르면 수 내림차순, 등장횟수가 같다면 수 오름차순.
                    for (auto iter = m.begin(); iter != m.end(); iter++)
                    {
                        p.push_back(make_pair(iter->second, iter->first)); // pair를 sort하면 first값으로 정렬됨.
                    }
                    for (int i = 0; i < p.size(); i++)
                    {
                        for (int j = i + 1; j < p.size(); j++)
                        {
                            if (p[i].first > p[j].first) //빈도수가 작은 순서대로 정렬
                            {
                                swap(p[i], p[j]);
                            }
                            else if (p[i].first == p[j].first) //빈도수가 같다면 숫자를 오름차순으로 정렬.
                            {
                                if (p[i].second > p[j].second)
                                {
                                    swap(p[i], p[j]);
                                }
                            }
                        }
                    }
                    int idx = 0;
                    if (Row < p.size() * 2) //실제로 숫자가 들어가는 크기는 pair의 2배가 들어가야한다. 최대값을 계속 바꿔줌.
                    {
                        Row = p.size() * 2;
                    }
                    // cout << "ROW " << Row << endl;
                    if (Row > 100) //만약 크기가 100을 넘긴다면 잘라줌.
                        Row = 100;
                    row = Row;                         //함수에 들어갈 row값 변경
                    for (int k = 0; k < p.size(); k++) //숫자,빈도수 순서대로 배열에 넣어줌.
                    {
                        a[i][idx] = p[k].second, idx++;
                        a[i][idx] = p[k].first, idx++; // second가 숫자, first가 빈도수
                    }
                    m.clear();            // map비우기.
                    visit.assign(101, 0); // visit배열 비우기.
                }
                Cnt++;                    // 1초 경과
                if (a[r - 1][c - 1] == k) //만약 a[r][c]를 찾았다면 그때 시간초를 출력하고 반복문 탈출
                {
                    cout << Cnt;
                    break;
                }
            }
            else
            {
                for (int x = 0; x < rsize; x++) //행의 개수
                {
                    vector<pair<int, int>> P;
                    for (int y = 0; y < csize; y++) //열의 갯수
                    {
                        if (a[y][x] == 0)
                            continue;
                        visit[a[y][x]]++; //빈도수 ++시켜주기.
                        m[a[y][x]] = visit[a[y][x]];
                        a[y][x] = 0; //값을 넣고 실시간으로 0으로 초기화해줌.
                    }
                    for (auto iter = m.begin(); iter != m.end(); iter++)
                    {
                        P.push_back(make_pair(iter->second, iter->first));
                    }
​
                    for (int i = 0; i < P.size(); i++)
                    {
                        for (int j = i + 1; j < P.size(); j++)
                        {
                            if (P[i].first > P[j].first) //빈도수가 작은 순서대로 정렬
                            {
                                swap(P[i], P[j]);
                            }
                            else if (P[i].first == P[j].first) // 빈도수가 같을시 숫자를 비교해서 오름차순으로 정렬
                            {
                                if (P[i].second > P[j].second)
                                {
                                    swap(P[i], P[j]);
                                }
                            }
                        }
                    }
                    int idx = 0;
                    if (Col < P.size() * 2) //실제로 들어가는 크기는 pair의 크기 * 2다.
                    {
                        Col = P.size() * 2;
                    }
                    if (Col > 100) //만약 100을 넘긴다면 100이후로는 짤라줌.
                        Col = 100;
                    col = Col;
                    for (int k = 0; k < P.size(); k++) //숫자,빈도수 순서대로 배열에 넣어줌.
                    {
                        a[idx][x] = P[k].second;
                        idx++;
                        a[idx][x] = P[k].first;
                        idx++;
                    }
                    m.clear();            // map비우기.
                    visit.assign(101, 0); // visit배열 비우기.
                }
                Cnt++;
                if (a[r - 1][c - 1] == k) //만약 값을 찾았다면 초를 출력하고 반복문 탈출
                {
                    cout << Cnt;
                    break;
                }
            }
        }
    }
}
void makeary(int row, int col)
{
    int num;
    for (int i = 0; i < row; i++)
    {
        for (int j = 0; j < col; j++)
        {
            cin >> num;
            a[i][j] = num;
        }
    }
}
bool flag(int a, int b) //
{
    if (a >= b)
        return true;
    else
        return false;
}
```
​
---
​
## **✔느낀 점**
​
-   **초기에는 배열의 값을 101로 지정해둔 것이 아닌 3x3으로 고정해놓고 증가한 행과 열의 개수로 배열의 크기를 조정했었다.**
    -   **하지만 백준의 더러운 테스트 케이스에서 runtime에러가 났다.**
    -   **초기 입력값을 a \[3\]\[3\]를 찾도록 준 것이다. 당연히 나는 배열을 3x3으로 초기화했고 그러한 index에 가지 못했다.**
    -   **따라서 배열의 크기를 101로 고정해줬다. 최댓값이 100이기 때문이다.**
    -   **확실히 배열의 크기를 101로 고정시키니 신경 써줘야 할게 없어지긴 했다.**
-   **하지만 행과 열의 개수를 계산해서 R연산과 C연산의 여부를 결정시켜 주기 위해서는 입력된 행의 개수와 열의 개수를 계속 지속적으로 최신화해서 비교시켜줘야 했다.**
    -   **그 구현은 기존에서 했던 것에서 조금만 바꿔줬다.**
-   **조금 헷갈렸던 것은 행열의 개념이 아직 덜 잡혀있어서 그런지 계속 for문 상에서는 i, j값을 계속 반대로 써서 오류가 났었다.**
    -   **행의 개수는 row이고, 열의 개수는 col인데 이게 계속 헷갈리네..**
-   **그리고 map과 pair를 썼는데 둘 중 하나만 써서 풀 수 있었을 거 같은데 일단 두 개 모두 사용했다.**
-   **굉장히 오랜 시간이 걸렸다. 이상한 데서 막혀서,,** 
    -   **예를 들어 배열을 고정시키지 않아서 생긴 작은 오류들**
    -   **행과 열의 최댓값을 갱신할 때 변수를 하나 사용해서 최신화를 잘못한 점**
-   **너무 오래 걸린 문제였다.**
​