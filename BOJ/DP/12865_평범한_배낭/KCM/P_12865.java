import java.util.*;
import java.io.*;

public class P_12865 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //무게와 가치를 차례로 저장.
        //물건을 1번 2번으로 인덱싱하기위해 행의 수를 +1해줌.
        int [][] list = new int [N+1][2];

        //dp는 1부터 최대무게 K까지 들어갈 수 있는 가치의 최대값을 저장.
        //dp 배열을 작성할 떄는 물건을 하나하나 넣을 떄마다. 각 무게별 최대가치가 얼마인지를 고려하여 for문돌리기
        int [][] dp = new int [N+1][K+1];

        //무게와 가치를 list에 입력순서대로 저장.
        for(int i=1;i<=N;i++){
            StringTokenizer s = new StringTokenizer(br.readLine());
            list[i][0] = Integer.parseInt(s.nextToken());
            list[i][1] = Integer.parseInt(s.nextToken());
        }

        //dp배열 작성!
        for (int i=1;i<=N;i++){
            for(int j=1;j<=K;j++){
                if(j-list[i][0]>=0){
                    dp[i][j] = Math.max(dp[i-1][j], list[i][1] + dp[i-1][j-list[i][0]]);
                }
                else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
