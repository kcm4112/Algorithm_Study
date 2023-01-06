import java.util.*;
import java.io.*;

public class P_17297 {
    static int M, cnt;
    static String messi = "";
    static String messi_N, messi_N_1, messi_N_2;
    static List<String> answer = new ArrayList<>();
    static List<Integer> len = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());
        //0번째에는 default로 ""를 넣어준다.
        answer.add("");
        len.add(0);
        len.add(5);
        len.add(13);
        answer.add("Messi");
        answer.add("Messi Gimossi");
        if(M<=13) {
            if(answer.get(2).charAt(M-1) == ' ') {
                System.out.println("Messi Messi Gimossi");
            }
            else {
                System.out.println(answer.get(2).charAt(M-1));
            }
        }
        else {
            cnt = 3;
            while(true) {
                len.add(len.get(cnt-1) + 1 + len.get(cnt-2));
                if(len.get(cnt) >= M) {
                    break;
                }
                else {
                    cnt++;
                }
            }
            //최종적으로 M 이상의 길이를 가진 Messi(N) 중에서 N이 가장 작은 Messi 문자열의 길이가 나왔을 것이고 그 때의 cnt 즉, N을 알 수 있을 것이다.
            solve(cnt, M);
        }
    }
    public static void solve(int N, int M) {
        if(N == 1 || N == 2) {
            if(N == 1) {
                System.out.println(answer.get(1).charAt(M-1));
                return;
            }
            else if(N == 2) {
                if(answer.get(2).charAt(M-1) == ' ') {
                    System.out.println("Messi Messi Gimossi");
                }
                else {
                    System.out.println(answer.get(2).charAt(M-1));
                }
                return;
            }
        }
        else if (M == len.get(N-1) + 1) { //공백일 경우
            System.out.println("Messi Messi Gimossi");
            return;
        }
        else if(M < len.get(N-1) + 1) { //messi(N) 의 공백 부분보다 작을 경우
            solve(N-1, M);
        }
        else if(M > len.get(N-1) + 1) { //messi(N) 의 공백 부분보다 클 경우
            solve(N-2, M - (len.get(N-1) + 1));
        }
    }
}
