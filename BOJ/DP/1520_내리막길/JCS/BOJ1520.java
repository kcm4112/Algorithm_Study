package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1520 {
    static int M, N;
    static int [][] map;
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    static StringTokenizer stringTokenizer ;
    static int[] x_dir = {0,0,-1,1};
    static int[] y_dir = {1,-1,0,0};
    static int[][] dp;
    static int count =0;
    public static void main(String[] args) throws IOException {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        M = Integer.parseInt(stringTokenizer.nextToken());
        N = Integer.parseInt(stringTokenizer.nextToken());
        map = new int[M][N];
        dp = new int[M][N];
        for(int i =0;i<M;i++)
        {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j=0;j<N;j++)
            {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                dp[i][j] = -1;
            }
        }
        count  = sol(0,0);
        System.out.println(count);
    }

    private static int sol(int x, int y) {
        if (x == M-1 && y == N-1) {
             return 1;
        }
        if(dp[x][y]!=-1)
        {
            return dp[x][y];
        }
        dp[x][y]=0;
        for(int i =0 ; i<4; i++)
        {
            if(x+x_dir[i]<0 || x+x_dir[i]>=M || y+y_dir[i]<0 || y+y_dir[i]>=N || map[x+x_dir[i]][y+y_dir[i]] >= map[x][y] )
                continue;
            dp[x][y] += sol(x+x_dir[i],y+y_dir[i]);
        }
        return dp[x][y];
    }

}
