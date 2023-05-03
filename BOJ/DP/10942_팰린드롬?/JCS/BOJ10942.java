package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10942 {
    static int N;
    static int [] nums ;
    static int M;
    static int S, E;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static  boolean[][] dp ;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        nums = new int[N];
        st = new StringTokenizer(bufferedReader.readLine());
        int cnt = 0;
        while (st.hasMoreTokens())
        {
            nums[cnt++] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(bufferedReader.readLine());

        dp = new boolean[N][N];
        sol();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i<M;i++)
        {
            st = new StringTokenizer(bufferedReader.readLine());
            S = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            if(dp[S-1][E-1]) stringBuilder.append("1\n");
            else stringBuilder.append("0\n");
        }
        System.out.print(stringBuilder);
        return;
    }

    private static void sol() {


        for(int i =0 ;i<N;i++)
        {
            dp[i][i] = true;
        }
        for(int i = 0 ; i<N-1;i++)
        {
            dp[i][i+1] = (nums[i] == nums[i+1]) ? true:false;
        }
        for(int i = 2 ; i< N ;i++ )
        {
            for (int j =0;j < N-i ; j++)
            {
                if(nums[j]==nums[j+i] && dp[j+1][j+i-1])
                {
                    dp[j][j+i] = true;
                }
                else dp[j][j+i]=false;
            }
        }


    return;
    }
}
