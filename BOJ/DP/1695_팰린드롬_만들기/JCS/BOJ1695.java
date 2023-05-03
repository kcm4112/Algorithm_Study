package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1695 {

    static int N ;
    static int [] nums ;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static int[][] dp;
    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(bufferedReader.readLine());
        stringTokenizer =new StringTokenizer(bufferedReader.readLine());
        nums = new int[N];
        for(int i =0; i <N;i++)
        {
            nums[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        dp = new int[N+1][N+1];
        sol();
        return;
    }

    private static void sol() {

        for(int i =1; i<N+1 ; i++)
        {
            dp[i][i] = 0;
        }
        for(int i=1; i<N;i++)
        {
            if(nums[i-1] == nums[i])
            {
                dp[i][i+1] = 0;
            }
            else dp[i][i+1] = 1;
        }
        for(int i = 2 ; i <N+1;i++)
        {
            for(int j =1; j<=N-i ; j++)
            {
                if(nums[j-1]!=nums[j+i-1])
                {
                        dp[j][j+i] = Math.min(dp[j][j+i-1],dp[j+1][j+i]) + 1;
                }
                else
                {
                        dp[j][j+i] = dp[j+1][j+i-1];
                }
            }
        }
        System.out.println(dp[1][N]);
    }

}
