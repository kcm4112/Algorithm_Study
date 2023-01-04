/*
골드3 메시기모띠
*/
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int M;
vector<int> f1;
string result = "Messi Gimossi";
int search(int Step, int order);
int main()
{
    cin >> M;
    f1.push_back(5);  //Messi
    f1.push_back(13); //Messi Gimossi
    int a = 5;
    int b = 13;
    int mm = 0;
    while (mm < M)
    {
        mm = a + b + 1;     //(n-1)+1+(n-2)
        a = b;
        b = mm;
        f1.push_back(mm);
    }
    int size=f1.size();
    int answer = search(size, M);
    if (answer == -1 || answer == 6)
    {
        cout << "Messi Messi Gimossi" << endl;
    }
    else
        cout << result[answer - 1];    
}
int search(int Step, int order)
{
    // 이제 쪼개주기.    
    if (order <= f1[1])
        return order;
    if (order <= f1[Step - 1]) // N-1구간
        return search(Step - 1, order);
    else if (order == f1[Step - 1] + 1) // 공백
        return -1;
    else //N-2구간
        return search(Step - 2, order - f1[Step - 1] - 1);
}