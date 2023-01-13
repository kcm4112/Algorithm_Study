package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1495 {
    public static int N, S, M;
    public static boolean[][] dp;
    public static int[] song;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 곡의 개수
        S = Integer.parseInt(st.nextToken()); // 시작 볼륨
        M = Integer.parseInt(st.nextToken()); // 최대값

        song = new int[N]; // 곡 리스트
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            song[i] = Integer.parseInt(st.nextToken());
        }
        // dp[a][b] = a 번째 노래를 b의 볼륨으로 부를 수 있다면 ture, 그렇지 못하면 false
        dp = new boolean[N][M+1];
        for (boolean[] ints : dp) {
            Arrays.fill(ints, false);
        }

        makeVolume();
        for (int i = M; i >=0 ; i--) {
            if(dp[N-1][i]) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);

    }


    public static void makeVolume(){
        if(S-song[0]>=0)
            dp[0][S-song[0]] = true;
        if(S+song[0]<=M)
            dp[0][S+song[0]] = true;

        for(int i=1;i<N;++i){
            for (int j = 0; j < M+1; j++) {
                // 이전 곡이 j의 볼륨으로 부를 수 없는 경우 넘어가기
                if(!dp[i-1][j])
                    continue;

                if(j-song[i]>=0)
                    dp[i][j-song[i]] = true;
                if(j+song[i]<=M)
                    dp[i][j+song[i]] = true;
            }
        }
    }

}
