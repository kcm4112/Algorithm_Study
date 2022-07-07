import java.util.*;
import java.io.*;


//1. 123부터 987까지는 영수가 생각할 수 있는 수의 후보이다.
//2. 단, 0이 있거나, 같은 숫자일 경우는 예외처리 해준다.
//3. 예외처리를 한 후, 그 숫자에 대해 민혁이가 던진 숫자를 반복문을 통해 던진다.
//4. 민혁이가 질문한 각 숫자에 대해 나오는 strike수와 ball수가 N번 모두 같으면 그 숫자는 후보가 된다.
//5. 후보가 되는 수의 개수를 출력하면 끝!!
public class P_2503 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] num = new int [N];
        int [] strike = new int [N];
        int [] ball = new int [N];
        int find_strike = 0;
        int find_ball = 0;
        int cnt = 0;
        int total=0;
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            num[i] = Integer.parseInt(st.nextToken());
            strike[i] = Integer.parseInt(st.nextToken());
            ball[i] = Integer.parseInt(st.nextToken());
        }

        for(int n = 123 ; n <= 987 ; n++){
            //n의 백,십,일의자리수를 구한다.
            int f = n/100;
            int s = (n%100)/10;
            int t = n%10;
            if(f ==0 || s == 0 || t == 0){
                continue;
            }
            if(f==s || s==t || f==t){
                continue;
            }
            for(int i=0;i<N;i++){
                //민혁이가 제시한 숫자들의 백,십,일의자리수를 먼저 구해놓은다.
                int first = num[i]/100;
                int sec = (num[i]%100)/10;
                int third = num[i]%10;

                //스트라이크 개수 구하기
                if(first == f){
                    find_strike++;
                }
                if(sec == s){
                    find_strike++;
                }
                if(third == t){
                    find_strike++;
                }
                //볼 개수 구하기
                if(first != f){
                    if(first == s || first == t){
                        find_ball++;
                    }
                }
                if(sec != s ){
                    if((sec == f || sec == t)){
                        find_ball++;
                    }
                }
                if(third != t ){
                    if(third == f || third == s){
                        find_ball++;
                    }
                }
                if(strike[i] == find_strike && ball[i] == find_ball){
                    cnt++;
                }
                find_strike=0;
                find_ball=0;
            }
            if(cnt==N){
                total++;
            }
            cnt =0;
        }
        System.out.println(total);
    }
}
