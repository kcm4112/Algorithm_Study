/*
백준 17609 골드5 회문
*/
#include <iostream>
#include <vector>
#include <algorithm>
#include <string.h>
using namespace std;
int n;
vector<string> a;
vector<string> b;
void Copy(int start, int finish, int now, int step);
int main()
{
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        string str;
        cin >> str;
        int size = str.length();
        string cur = str;
        int check = 0;
        int leftpoint = 0;
        int rightpoint = 0;
        for (int j = 0; j < size / 2; j++)
        {
            if (str[j] != str[size - 1 - j])
            {
                check = 1;
                leftpoint = j, rightpoint = size - 1 - j; // 어긋난 경계점을 기록함.
                break;
            }
        }
        if (check != 0)
        { // check가 0이 아니라면 회문이 아니라는 뜻. 유사회문 or 아무것도 아님.
            string test = cur.erase(leftpoint, 1); //경계점중 왼쪽을 지운 배열
            string test1 = str.erase(rightpoint, 1); //경게점 중 오른쪽을 지운 배열
            int size = test.length();
            bool flag = true;
            bool flag1 = true;
            for (int k = 0; k < size / 2; k++)
            {
                if (test[k] != test[size - 1 - k])
                {
                    flag = false;
                }
                if (test1[k] != test1[size - 1 - k])
                {
                    flag1 = false;
                }
            }
            if (flag == false && flag1 == false) //만약 둘다 false라면 하나도 통과하지 못햇다는 뜻이므로 유사회문이 아님.
            {
                check = 2;
            }
        }
        cout << check << endl;
    }
}