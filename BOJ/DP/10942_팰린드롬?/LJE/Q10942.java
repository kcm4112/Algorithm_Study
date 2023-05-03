package DP;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q10942 {
    static int N, M;
    static int[] numbers;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());


        numbers = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }


        dp = new int[N + 1][N + 1];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            bw.write(check(S, E) + "\n");
        }

        bw.flush();
        bw.close();
    }

    static int check(int L, int R) {
        if (dp[L][R] != -1) {
            return dp[L][R];
        }

        if (L >= R) {
            dp[L][R] = 1;
            return dp[L][R];
        }

        if (numbers[L] == numbers[R]) {
            dp[L][R] = check(L + 1, R - 1);
        } else {
            dp[L][R] = 0;
        }

        return dp[L][R];
    }
}
