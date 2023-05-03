package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1520 {
    static int R, C, ans;
    static int[][] graph, dp;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        ans = 0;

        graph = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[R][C];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }

        int ans = DFS(new Point(0, 0));
        System.out.println(ans);
    }

    public static int DFS(Point point) {
        int y = point.y;
        int x = point.x;

        if (y == R - 1 && x == C - 1) {
            return 1;
        }

        if (dp[y][x] != -1) {
            return dp[y][x];
        }

        dp[y][x] = 0;
        for (int cand = 0; cand < 4; cand++) {
            int ny = y + dir[cand][0];
            int nx = x + dir[cand][1];

            if (ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
            if(graph[y][x] <= graph[ny][nx]) continue;

            dp[y][x] += DFS(new Point(ny, nx));
        }

        return dp[y][x];
    }

    static class Point{
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
