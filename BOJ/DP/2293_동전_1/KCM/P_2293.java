import java.nio.Buffer;
import java.util.*;
import java.io.*;

public class P_2293 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int [] dp = new int[k+1];


        for(int i=1; i<=n; i++){
            int value = Integer.parseInt(br.readLine());
            for(int j=value; j<=k;j++){
                //자신의 숫자 1개만써서 k를 맞추는 경우.
                if(j-value==0){
                    dp[j] = dp[j] + 1;
                }
                //예를들어 2를 사용해서 3을 맞출 때, 2를 필요한 경우는 1이 있을 경우이므로 dp[1]을 더한다.
                else if (j-value>0){
                    dp[j] = dp[j] + dp[j-value];
                }
            }

        }

        System.out.println(dp[k]);
    }
}
