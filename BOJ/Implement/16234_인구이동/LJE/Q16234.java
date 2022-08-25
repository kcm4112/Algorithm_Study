package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class pair {
    int row, col;
    public pair(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }
}

public class Q16234 {
    static int N, L, R, count, result;
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };
    static int[][] map;
    static int[][] visited;
    static boolean check;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        count = 0;
        result = 0;
        check = true;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (check) {
            result++;
            check = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] != result) {
                        Bfs(i, j);
                    }
                }
            }
        }
        System.out.println(result-1);
    }

    static boolean isValid(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N)
            return false;
        return true;
    }

    static void Bfs(int row, int col) {
        visited[row][col] = result;
        int sum = map[row][col];
        Queue<pair> q = new LinkedList<>();
        Queue<pair> pos = new LinkedList<>();
        q.offer(new pair(row, col));
        pos.offer(new pair(row, col));
        while (!q.isEmpty()) {
            int q_row = q.peek().row;
            int q_col = q.peek().col;
            q.poll();
            for (int k = 0; k < 4; k++) {
                int next_row = q_row + dy[k];
                int next_col = q_col + dx[k];
                if (!isValid(next_row, next_col) || visited[next_row][next_col] == result
                        || Math.abs(map[q_row][q_col] - map[next_row][next_col]) < L
                        || Math.abs(map[q_row][q_col] - map[next_row][next_col]) > R)
                    continue;
                q.offer(new pair(next_row, next_col));
                pos.offer(new pair(next_row, next_col));
                sum += map[next_row][next_col];
                visited[next_row][next_col] = result;
                check = true;
            }
        }
        if (pos.size() == 0)
            return;
        int avg = sum / pos.size();
        while (!pos.isEmpty()) {
            map[pos.peek().row][pos.peek().col] = avg;
            pos.poll();
        }
    }
}