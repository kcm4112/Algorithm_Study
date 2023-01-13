package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q12869 {
    public static int[] scv;
    public static int[][][] dp;
    // 중복을 피하기 위해 이미 처리한 값에 대한 처리
    public static boolean[][][] visited = new boolean[61][61][61];
    public static int result=(int)1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // SCV 수
        st = new StringTokenizer(br.readLine());

        scv = new int[3];
        for (int i = 0; i < N; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }
        // scv 개수가 3보다 작으면 나머지는 0으로 채워주기
        for (int i = N; i < 3; i++) {
            scv[i] = 0;
        }
        int s1=scv[0], s2=scv[1], s3=scv[2];

        // dp[a][b][c] = 체력이 a,b,c인 scv를 죽이는 최소값
        dp = new int[s1+1][s2+1][s3+1];
        for (int[][] ints : dp) {
            for (int[] anInt : ints) {
                Arrays.fill(anInt, -1);
            }
        }

        findMin(s1, s2, s3, 0);
        System.out.println(result);

    }

    public static void findMin(int a, int b, int c, int count) {
        // 음수라면 0으로 만들기
        a = Math.max(0, a);
        b = Math.max(0, b);
        c = Math.max(0, c);

        // 모든 scv가 죽었을 경우
        if(a==0 & b==0 && c==0){
            result = Math.min(result, count);
            return;
        }

        // a,b,c 순서로 큰 수대로 입력
        int[] sorting = {a,b,c};
        Arrays.sort(sorting);
        c = sorting[0];
        b = sorting[1];
        a = sorting[2];

        // 이미 계산한 값에 대한 중복을 피하기 위해 방문 했다면 종료
        if(visited[a][b][c])
            return;
        else
            visited[a][b][c]=true;

        // 현재 계산 중인 count가 result보다 크다면 더 계산할 필요없이 바로 종료
        if(result<count)
            return;

        findMin(a-9, b-3, c-1, count+1);
        findMin(a-9, b-1, c-3, count+1);
        findMin(a-3, b-9, c-1, count+1);
        findMin(a-3, b-1, c-9, count+1);
        findMin(a-1, b-3, c-9, count+1);
        findMin(a-1, b-9, c-3, count+1);

        return;
    }
}
