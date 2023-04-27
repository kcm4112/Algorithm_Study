package Graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q3197_2 {
    static int R, C;
    static char[][] map;
    static Point L1, L2;
    static boolean[][] visited;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();

        map = new char[R][C];
        visited = new boolean[R][C];
        Queue<Point> water_queue = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            map[i] = sc.next().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'L') {
                    if(L1 == null)
                        L1 = new Point(i, j);
                    else
                        L2 = new Point(i, j);
                }
                // 물인 지역은 water_queue에 넣기, 백조가 있는 곳도 물이다!!
                if (map[i][j] != 'X') {
                    water_queue.offer(new Point(i, j));
                }
            }
        }

        // 탐색 큐를 선언하고 첫 번째 백조의 위치 넣어주기
        Queue<Point> queue = new LinkedList<>();
        queue.offer(L1);
        visited[L1.y][L1.x] = true;

        int days = 0;
        boolean meet = false;

        while (true) {
            // 얼음이 녹아 다음 날 방문하게 될 탐색 지점이 들어가는 큐
            Queue<Point> nextQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                Point cur = queue.poll();
                int y = cur.y;
                int x = cur.x;

                // 백조를 만나게 되면 종료
                if (y == L2.y && x == L2.x) {
                    meet = true;
                    break;
                }

                for (int cand = 0; cand < 4; cand++) {
                    int ny = y + dir[cand][0];
                    int nx = x + dir[cand][1];

                    if(ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
                    if(visited[ny][nx]) continue;

                    visited[ny][nx] = true;

                    // 탐색 지점이 얼음인 경우, 다음 날 녹게되어 탐색을 해야하므로 nextQueue에 삽입
                    if (map[ny][nx] == 'X') {
                        nextQueue.offer(new Point(ny, nx));
                        continue;
                    }
                    // 탐색 지점이 물이라면, 현재 탐색 중인 지역이므로, queue에 삽입
                    queue.offer(new Point(ny, nx));
                }
            }

            // 두 백조가 만났다면, 종료
            if(meet)
                break;

            // 다음 날 탐색하게 될 지점이 들어있는 nextQueue를 현재 탐색하는 queue로 바꿔준다.
            queue = nextQueue;

            // 얼음 녹이기
            int size = water_queue.size();
            for (int i = 0; i < size; i++) {
                Point cur = water_queue.poll();

                for (int cand = 0; cand < 4; cand++) {
                    int ny = cur.y + dir[cand][0];
                    int nx = cur.x + dir[cand][1];

                    if(ny < 0 || nx < 0 || ny >= R || nx >= C) continue;

                    // 인접한 얼음을 발견하면, 물로 녹이고 water_queue에 넣기
                    if (map[ny][nx] == 'X') {
                        map[ny][nx] = '.';
                        water_queue.offer(new Point(ny, nx));
                    }
                }
            }

            days++;
        }

        System.out.println(days);

    }

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
