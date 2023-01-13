package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2096 {
    public static int[][] graph;
    public static int[][] dp;
    public static final int MAX=1, MIN=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 줄의 수
        graph = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][3];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }

        // Max 값 계산
        findMaxMin(N, MAX);
        Arrays.sort(dp[N-1]); // dp 배열의 맨 마지막 줄을 정렬했을 때, 제일 끝 갑이 최댓값
        System.out.print(dp[N-1][2]+" "); // Max값 출력

        // 다시 dp 배열 초기화
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }

        // Min 값 계산
        findMaxMin(N, MIN);
        Arrays.sort(dp[N-1]);
        System.out.println(dp[N-1][0]);
    }

    public static void findMaxMin(int n, int flag){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                if(i==0){
                    dp[i][j] = graph[i][j];
                }
                else {
                    if(flag==1){
                        dp[i][j] = Math.max(dp[i-1][j] + graph[i][j], dp[i][j]);
                    }
                    else{
                        if(dp[i][j]==-1){ // 아직 값이 입력되지 않은 경우
                            dp[i][j] = dp[i-1][j] + graph[i][j];
                        }
                        else
                            dp[i][j] = Math.min(dp[i-1][j] + graph[i][j], dp[i][j]);
                    }

                    // 왼쪽으로 가는 경우
                    if(j-1>=0){
                        if(flag==1) // Max값을 구하는 경우
                            dp[i][j] = Math.max(dp[i-1][j-1] + graph[i][j], dp[i][j]);
                        else{
                            if(dp[i][j]==-1){ // 아직 값이 입력되지 않은 경우
                                dp[i][j] = dp[i-1][j-1] + graph[i][j];
                            }
                            else
                                dp[i][j] = Math.min(dp[i-1][j-1] + graph[i][j], dp[i][j]);
                        }
                    }

                    // 오른쪽으로 가는 경우
                    if(j+1<3){
                        if(flag==1)
                            dp[i][j] = Math.max(dp[i-1][j+1] + graph[i][j], dp[i][j]);
                        else{
                            if(dp[i][j]==-1){ // 아직 값이 입력되지 않은 경우
                                dp[i][j] = dp[i-1][j+1] + graph[i][j];
                            }
                            else
                                dp[i][j] = Math.min(dp[i-1][j+1] + graph[i][j], dp[i][j]);
                        }
                    }
                }
            }
        }
    }
}
