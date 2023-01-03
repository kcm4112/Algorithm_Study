import java.io.*;
import java.util.*;

//햄버거 재료의 개수 = N-1 햄버거 *2 + 3
//패티의 개수 = N-1 햄버거 패티의 수*2 +1
//Level 0 햄버거의 재료 개수 = 패티개수 = 1
//햄버거를 딱 반까지 먹을 경우, 반보다 적게 먹을 경우, 반이상 먹을 경우, 다먹을 경우


public class P_16974 {
    static int N;
    static long X;
    static long [] total;
    static long [] patty;
    static long answer = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Long.parseLong(st.nextToken());
        total = new long[N+1];
        patty = new long[N+1];
        //레벨 0짜리 재료랑 패티개수 저장
        total[0] = 1;
        patty[0] = 1;

        for(int i=1;i<=N;i++) {
            total[i] = total[i-1]*2 + 3;
            patty[i] = patty[i-1]*2 + 1;
        }
//        for(int i=0;i<=N;i++) {
//            System.out.printf("total : %d patty : %d\n", total[i], patty[i]);
//        }

        answer = solve(N,X);
        System.out.println(answer);


    }

    public static long solve(int N, long X) {
        if(N == 0)  {
            if(X == 0) {
                return 0;
            }
            else if(X == 1) {
                return 1;
            }
        }
        else if(X == 0) {
            return 0;
        }
        else if(X == 1 + total[N-1] + 1) { //가운데까지 먹은경우
            return 1 + patty[N-1];
        }
        else if ( X < 1+total[N-1] + 1) { // 가운데보다 적게 먹은 경우
            return solve(N-1, X-1);
        }
        else if( X > 1 + total[N-1] + 1 && X != total[N]) { //가운데보다 많이 먹은 경우
            return patty[N - 1] + 1 + solve(N-1, X-(total[N]/2 + 1));
        }
        else if ( X == total[N]) { //전체를 다 먹은 경우
            return patty[N];
        }
        return 0;
    }
}
