package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q4485{

    static class point implements Comparable<point> {

        int row, col, cost;

        public point(int row, int col, int cost) {
            super();
            this.row = row;
            this.col = col;
            this.cost = cost;
        }

        @Override
        public int compareTo(point o) {
            return this.cost - o.cost;
        }

    }

    static int N;
    static int[][] map;
    static int[][] distance;
    static int[] dy = { 0, 1, -1, 0 };
    static int[] dx = { 1, 0, 0, -1 };

    static boolean isValid(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= N)
            return false;
        return true;
    }

    public static int dijkstra() {
        PriorityQueue<point> pq = new PriorityQueue<point>();
        distance[0][0] = map[0][0];
        pq.offer(new point(0, 0, map[0][0]));

        while (!pq.isEmpty()) {
            point p = pq.poll();

            for (int k = 0; k < 4; k++) {
                int nextRow = p.row + dy[k];
                int nextCol = p.col + dx[k];

                if (isValid(nextRow, nextCol)) {
                    if (distance[nextRow][nextCol] > distance[p.row][p.col] + map[nextRow][nextCol]) {
                        distance[nextRow][nextCol] = distance[p.row][p.col] + map[nextRow][nextCol];
                        pq.offer(new point(nextRow, nextCol, distance[nextRow][nextCol]));
                    }
                }
            }
        }
        return distance[N - 1][N - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int cnt = 0;

        while (true){
            N = Integer.parseInt(br.readLine());

            if (N == 0)
                break;

            map = new int[N][N];
            distance = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
            cnt++;
            sb.append("Problem " + cnt + ": " + dijkstra() + "\n");
        }

        System.out.println(sb);
        br.close();
    }

}