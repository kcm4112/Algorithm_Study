package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17485 {

    static int [][] map ;
    static int [][][] dp ;
    static int N;
    static int M;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M][3];
        for(int i =0;i<N;i++)
        {
            st = new StringTokenizer(bufferedReader.readLine());
            for(int j =0;j<M;j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(i==0)
                {
                    dp[i][j][0] = map[i][j];
                    dp[i][j][1] = map[i][j];
                    dp[i][j][2] = map[i][j];
                }
                else if(j==M-1)
                {
                    dp[i][j][0] = Integer.MAX_VALUE;
                }
                else if(j==0)
                {
                    dp[i][j][2] = Integer.MAX_VALUE;
                }
            }
        }
        solution();
    }

    private static void solution() {

        for(int i=1;i<N;i++)
        {
            for(int j=0;j<M;j++)
            {
                if( 0<=j+1 && j+1<M)
                dp[i][j][0] = Integer.min(dp[i-1][j+1][1],dp[i-1][j+1][2])+map[i][j];
                if(0<=j && j<M)
                dp[i][j][1] = Integer.min(dp[i-1][j][0],dp[i-1][j][2]) + map[i][j];
                if(0<=j-1 && j-1<M)
                dp[i][j][2] = Integer.min(dp[i-1][j-1][0],dp[i-1][j-1][1])+map[i][j];
            }
        }
        int minValue = Integer.MAX_VALUE;

        for(int i =0;i<M;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(dp[N-1][i][j]<minValue)
                {
                    minValue = dp[N-1][i][j];
                }
            }
        }
        System.out.println(minValue);
    }

}
