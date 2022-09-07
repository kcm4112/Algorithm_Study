/*
백준  골드5 5904 Moo게임
*/
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int n;
vector<string> str;
void moo(string str, int i);
int main()
{
    string str = "moo";
    cin >> n;
    moo(str, 0);

    /*  for(int i=0; i<str.size();i++){
         cout<<str[i]<<endl;
     } */
}
void moo(string str, int i)
{
    int step = i + 1;
    string m = "m";
    int size = n - str.length();
    if (size <= step * 2)
    {
        if (size == 1) {
            cout << "m";
            return;
        }
        else {
            cout << "o";
            return;
        }
    }
    else if (size >= step * 2) {
        cout << str[size - step * 2];
        return;
    }
    else
    {

        for (int j = step; j <= 2 + step; j++)
        {
            m += "o";
        }
    }
    moo(str + m + str, step);
}
/*
void moo(int i){
    int step=i+1;
    if(str[i].length()>=n){
        //cout<<n<<"번째 문자열은 "<<str[i][n-1]<<endl;
        cout<<str[i][n-1];
        return ;
    }
    else{
        string m="m";
        for(int j=step; j<=step+2;j++){
            m+="o";
        }
        str.push_back(str[i]+m+str[i]);
    }
    moo(step);
}
*/