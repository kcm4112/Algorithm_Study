#include <iostream>
#include <vector>
#include <string>
using namespace std;

string s;
unsigned long long answer = 0;
long long dp[10];
int mod1[19],mod2[19];
int sum_date(int Y, int M, int D);
bool compare_year(string y, int Y);
bool compare_month(string m, int M);
bool compare_day(string d, int D);
int go_date(int m, int y);
void desni_dio();
void spoji();
int main()
{
    cin >> s;
    // x마다 모든 경우의 수를 탐색
    // YYYY가 100의 배수가 아니고 ,4의 배수 or  // 400의 배수.
    // 년도를 먼저 채워놓는게 편할거 같긴 하네! 4~7
    desni_dio();
    string year = s.substr(4, 4);
    string month = s.substr(2, 2);
    string day = s.substr(0, 2);
    // 내가 보기엔 DDMMYY만 돌리면 됨.
    for (int Y = 1; Y <= 9999; Y++)
    {
        if(!compare_year(year,Y)){
            continue;
        }
        for (int M = 1; M <= 12; M++)
        {
            if(!compare_month(month,M)){
                continue;
            }
            int to = go_date(M,Y);
            for (int D = 1; D <= to; D++)
            {
                if(!compare_day(day,D)){
                    continue;
                }
               ++mod1[ ( 6*(Y/1000) + 5*(Y/100%10) + 4*(Y/10%10) + 3*(Y%10) +
                      8*(M/10) + 7*(M%10) + 10*(D/10) + 9*(D%10) ) % 19 ];
            }
        }
    }
    spoji();
    if(s[18]=='X'){
        cout<<answer<<endl;
    }
    else
        cout<<dp[s[18]-'0']<<endl;
}
void spoji()
{
   for ( int l=0; l<19; ++l )
      for ( int d=0; d<19; ++d ) {
         dp[ (l+d)%19<10? (l+d)%19 : 19-(l+d)%19 ] += (long long)mod1[l] * mod2[d];
         answer += (long long)mod1[l] * mod2[d];
      }
}
void desni_dio()
{
   int k = 2;
   static int c[11][19];

   c[0][0] = 1;
   for ( int i=0; i<10; ++i ) {
      for ( int z=0; z<10; ++z ) if ( s[8+i] == 'X' || s[8+i] == '0'+z )
         for ( int m=0; m<19; ++m )
            c[i+1][(m+k*z)%19] += c[i][m];
      if ( --k < 2 ) k = 10;
   }

   for ( int m=0; m<19; ++m )
      mod2[m] = c[10][m];
}
int go_date(int m, int y)
{
    int date[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    if (m== 2 && (y % 400 == 0 || y % 4 == 0 && y % 100 != 0))
        return 29;
    return date[m - 1];
}
bool compare_day(string d, int D){
    if(d[1]!='X'&&d[1]!=char((D%10)+48)){
        return false;
    }
    if(d[0]!='X'&&d[0]!=char((D/10)+48)){
        return false;
    }
    return true;
}
bool compare_month(string m, int M){
    if(m[1]!='X'&&m[1]!=char((M%10)+48)){
        return false;
    }
    if(m[0]!='X'&&m[0]!=char((M/10)+48)){
        return false;
    }
    return true;
}
bool compare_year(string y, int Y){
    //20x2 vs Y
    if(y[3]!='X'&& y[3]!=char((Y%10)+48)){
        return false;
    }
    Y/=10;
    if(y[2]!='X'&&y[2]!=char((Y%10)+48)){
        return false;
    }
    Y/=10;
    if(y[1]!='X'&&y[1]!=char((Y%10)+48)){
        return false;
    }
    Y/=10;
    if(y[0]!='X'&&y[0]!=char((Y%10)+48)){
        return false;
    }
    return true;
}