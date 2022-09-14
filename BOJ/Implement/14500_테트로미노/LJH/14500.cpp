/*
백준 골드4 테트로미노
사각형의 형태는 5개고 회전,대칭 가능

*/

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int n, m, sum = 0, big = 0;
vector<vector<int>>graph;
int main() {
    cin >> n >> m;
    graph.resize(n, vector<int>(m, 0));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> graph[i][j];
        }
    }
    //모든 칸에 대해서 만들 수 있는 모든 사각형을 넣어보기?
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            //항상 graph[i][j]가 있어야함.
            if (i + 3 < n) { //세로로 4개 이어붙인거
                sum = graph[i][j] + graph[i + 1][j] + graph[i + 2][j] + graph[i + 3][j];
                if (sum > big) {
                    big = sum;
                }
            }
            if (j + 3 < m) { //가로로 4개 이어붙인거
                sum = graph[i][j] + graph[i][j + 1] + graph[i][j + 2] + graph[i][j + 3];
                if (sum > big) {
                    big = sum;
                }
            }
            if (i + 1 < n && j + 1 < m) { //2*2 정사각형 
                sum = graph[i][j] + graph[i + 1][j] + graph[i][j + 1] + graph[i + 1][j + 1];
                if (sum > big) {
                    big = sum;
                }
            }
            if (i + 2 < n && j + 1 < m) { //세로로  3개 이어 붙이고 가로로  오른쪽 하나씩 이어붙일 경우
                for (int k = 0; k < 3; k++) {
                    if (sum < graph[i][j] + graph[i + 1][j] + graph[i + 2][j] + graph[i + k][j + 1])
                    {
                        sum = graph[i][j] + graph[i + 1][j] + graph[i + 2][j] + graph[i + k][j + 1];
                    }
                }
                if (sum > big) {
                    big = sum;
                }

            }
            if (i + 2 < n && j - 1 >= 0) { //세로로 3개 이어붙이고 가로 왼쪽에 하나 붙이기.
                for (int k = 0; k < 3; k++) {
                    if (sum < graph[i][j] + graph[i + 1][j] + graph[i + 2][j] + graph[i + k][j - 1])
                    {
                        sum = graph[i][j] + graph[i + 1][j] + graph[i + 2][j] + graph[i + k][j - 1];
                    }
                }
                if (sum > big) {
                    big = sum;
                }
            }
            if (j + 2 < m && i + 1 < n) { //가로로 3개 이어붙이고 아래로 세로 1개 붙이는 경우.
                for (int k = 0; k < 3; k++) {
                    if (sum < graph[i][j] + graph[i][j + 1] + graph[i][j + 2] + graph[i + 1][j + k])
                    {
                        sum = graph[i][j] + graph[i][j + 1] + graph[i][j + 2] + graph[i + 1][j + k];
                    }
                }
                if (sum > big) {
                    big = sum;
                }
            }
            if (j + 2 < m && i - 1 >= 0) { //가로로 3개 이어붙이고 아래로 세로 1개 붙이는 경우.
                for (int k = 0; k < 3; k++) {
                    if (sum < graph[i][j] + graph[i][j + 1] + graph[i][j + 2] + graph[i - 1][j + k])
                    {
                        sum = graph[i][j] + graph[i][j + 1] + graph[i][j + 2] + graph[i - 1][j + k];
                    }
                }
                if (sum > big) {
                    big = sum;
                }
            }
            //엇갈려서 2개 붙이는 경우. 총 4가지의 모형이 발견됨.
            if (i + 2 < n && j + 1 < m)
            {
                sum = graph[i][j] + graph[i + 1][j] + graph[i + 1][j + 1] + graph[i + 2][j + 1];
                if (sum > big) {
                    big = sum;
                }
            }
            if (i + 1 < n && j - 1 >= 0 && j + 1 < m)
            {
                sum = graph[i][j] + graph[i][j + 1] + graph[i + 1][j - 1] + graph[i + 1][j];
                if (sum > big) {
                    big = sum;
                }
            }
            if (j - 1 >= 0 && i + 1 < n && j + 1 < m)
            {
                sum = graph[i][j] + graph[i][j - 1] + graph[i + 1][j] + graph[i + 1][j + 1];
                if (sum > big) {
                    big = sum;
                }
            }
            if (i - 1 >= 0 && j + 1 < m && i + 1 < n) {
                sum = graph[i][j] + graph[i + 1][j] + graph[i][j + 1] + graph[i - 1][j + 1];
                if (sum > big) {
                    big = sum;
                }
            }
        }
    }
    cout << big; //최대값 출력.
}