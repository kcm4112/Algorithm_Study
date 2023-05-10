#include <iostream>
#include <vector>
#include <algorithm>
#include <string.h>
#include <queue>

using namespace std;
string html;
int main()
{

    // 주요 태그는 main태그와 div태그, p태그가 있다. 어느게 닫는 태그인지 정확하게 알아야 할듯.
    getline(cin, html);
    cout << html << endl;
    for (int i = 0; i < html.size(); i++)
        {

            switch (html[i])
            {
            case '<': //시작부분
            {
                break;
            }
            case '>' : //완료부분
            {

            }



            default:
                break;
            }
        }
}