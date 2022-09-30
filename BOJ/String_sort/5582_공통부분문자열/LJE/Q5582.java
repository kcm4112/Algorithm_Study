package String_Sort;

import java.io.*;
import java.util.*;

public class Q5582 {

    static int [][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String A = br.readLine();
        String B = br.readLine();
        int A_length = A.length();
        int B_length = B.length();

        int result = 0;
        int[][] dp = new int[A_length + 1][B_length + 1];

        for (int i = 1; i <= A_length; i++) {
            for (int j = 1; j <= B_length; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    result = Math.max(result, dp[i][j]);
                }
            }
        }

        System.out.println(result);
    }
}