package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1600_2 {
    static int N, M, K;
    static int[][] map;
    static int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] dir_horse = {{-1, -2}, {-2, -1}, {1, 2}, {2, 1}, {-1, 2}, {-2, 1}, {1, -2}, {2, -1}};
    static int[][][] distance;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        /*
        0 : 평지
        1 : 장애물
         */
        map = new int[N][M];
        visited = new boolean[N][M][K+1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        BFS();

    }

    static void BFS() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0); // y
        queue.add(0); // x
        queue.add(0); // 말처럼 이동한 횟수
        queue.add(0); // 전체 이동 횟수
        visited[0][0][0] = true;


        while (!queue.isEmpty()) {
            int y = queue.poll();
            int x = queue.poll();
            int k = queue.poll();
            int cnt = queue.poll();

            // 끝에 도달했을 경우
            if (y == N - 1 && x == M - 1) {
                System.out.println(cnt);
                return;
            }

            for (int cand = 0; cand < 4; cand++) {
                int ny = y + dir[cand][0];
                int nx = x + dir[cand][1];

                // 범위를 벗어나는 경우
                if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
                // 장애물인 경우
                if (map[ny][nx] == 1) continue;
                // 이미 방문한 경우
                if (visited[ny][nx][k]) continue;

                queue.add(ny);
                queue.add(nx);
                queue.add(k);
                queue.add(cnt + 1); // 이동 횟수 1 증가
                visited[ny][nx][k] = true;
            }

            // 만약 k가 주어진 K와 같다면 말처럼 뛸 수 없다.
            if (k == K) {
                continue;
            }

            for (int cand = 0; cand < 8; cand++) {
                int ny = y + dir_horse[cand][0];
                int nx = x + dir_horse[cand][1];
                int next = k + 1;
                int dist = cnt;

                if(ny<0 || nx<0 || ny>=N || nx>=M) continue;
                if(map[ny][nx] == 1) continue;
                if(visited[ny][nx][next]) continue;

                queue.add(ny);
                queue.add(nx);
                queue.add(next);
                queue.add(dist + 1);
                visited[ny][nx][next] = true;

            }
        }

        System.out.println(-1);
    }

}
