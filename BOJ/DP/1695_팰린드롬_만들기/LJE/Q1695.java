package DP;

import java.util.Arrays;
import java.util.Scanner;

public class Q1695 {
    static int N;
    static int[] numbers;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }

        dp = new int[N][N];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }

        int L = 0;
        int R = N - 1;
        System.out.println(recur(L, R));
    }

    static int recur(int L, int R) {
        if (L > R) {
            return 0;
        }

        if (dp[L][R] != -1) {
            return dp[L][R];
        }

        if (numbers[L] == numbers[R]) {
            dp[L][R] = recur(L + 1, R - 1);
        } else {
            dp[L][R] = Math.min(recur(L + 1, R) + 1, recur(L, R - 1) + 1);
        }

        return dp[L][R];
    }
}
