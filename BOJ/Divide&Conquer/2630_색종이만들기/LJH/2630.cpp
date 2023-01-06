/*
백준 2630 색종이 만들기
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int n, blue = 0, white = 0, color;
vector<vector<int>>graph;
void divide(vector<vector<int>>& paper, int size);
bool check(vector<vector<int>>& paper);
void print_array(vector<vector<int>>& paper);
int main() {
    cin >> n;
    graph.resize(n, vector<int>(n, 0));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> graph[i][j];
        }
    }
    divide(graph, n);
    cout << white << endl;
    cout << blue;
}
void divide(vector<vector<int>>& paper, int size) {
    int s = size / 2; //중간으로 쪼갠다.
    //print_array(paper);
    if (check(paper) == true) {
      //  cout << "전체 사각형의 색깔이" << paper[0][0] << "으로 같습니다" << endl;
        if (color == 1)
            blue++;
        else
            white++;
        return;
    }
    else { // 전체가 한가지 색이 아닐때 4등분 해야함.
        vector<vector<int>>Q1;
        vector<vector<int>>Q2;
        vector<vector<int>>Q3;
        vector<vector<int>>Q4;
        Q1.resize(s, vector<int>(s));
        Q2.resize(s, vector<int>(s));
        Q3.resize(s, vector<int>(s));
        Q4.resize(s, vector<int>(s));
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                Q1[i][j] = paper[i][j];
                Q2[i][j] = paper[i][j + s];
                Q3[i][j] = paper[i + s][j];
                Q4[i][j] = paper[i + s][j + s];
            }
        }
        //1사분면
        divide(Q1, s);
        //2사분면
        divide(Q2, s);
        //3사분면
        divide(Q3, s);
        //4사분면
        divide(Q4, s);
    }

}
bool check(vector<vector<int>>& paper) { //파란색 true 1, 흰색 white 0
    color = paper[0][0];
    for (int i = 0; i < paper.size(); i++) {
        for (int j = 0; j < paper.size(); j++) {
            if (color != paper[i][j]) {
                return false;
            }
        }
    }
    return true;
}
void print_array(vector<vector<int>>& paper) {
    for (int i = 0; i < paper.size(); i++) {
        for (int j = 0; j < paper.size(); j++) {
            cout << paper[i][j] << " ";
        }
        cout << endl;
    }
}
