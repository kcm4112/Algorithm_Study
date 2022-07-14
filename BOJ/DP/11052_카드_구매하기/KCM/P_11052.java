import java.util.*;
import java.io.*;
public class P_11052 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int [] value = new int[N+1];
        int [] dp = new int[N+1];
        //카드팩에 대하여 값 넣기.
        for(int i=1;i<=N;i++){
            value[i] = Integer.parseInt(st.nextToken());
        }

        //1. 처음에 N개카드가 들어있는 카드팩을 골랐다고 default로 설정하고 그 값을 넣고
        //2. 그 다음에는 N개를 만들 수 있는 경우의 수와 현재 dp값을 비교하여 최대값 저장
        //3. 예를들어 4개를 만드는 경우의 수는 (dp[3] + dp[1]),  (dp[2] + dp[2])
        //  (dp[1] + dp[3]), (dp[0] + dp[4]) 이렇게 4번 비교하면 된다.
        //4. 1+1+1+1의 경우도 위의 경우의 수 안에 포함되어있다! 그래서 이 경우는 따로 생각하지 않아도 됨
        //5. 그 이유는 만약 1+1이 최대값이었다면 -> dp[2], 1+1+1 -> dp[2] + dp[1]로 들어가기 때문!

        for(int i=1;i<=N;i++){
            dp[i] = value[i];
            for(int j=1;j<=i;j++){
                dp[i] = Math.max(dp[i], dp[i-j] + dp[j]);
            }
        }
        System.out.println(dp[N]);

    }
}
