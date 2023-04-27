package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q6087 {
    static int R, C;
    static int end_y, end_x;
    static char[][] map;
    static int[][][] visited;
    static int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new int[4][R][C];

        int start_y = 0, start_x = 0;
        int flag = 0;
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if(map[i][j] == 'C'){
                    if (flag == 0) {
                        start_y = i;
                        start_x = j;
                        flag = 1;
                    } else {
                        end_y = i;
                        end_x = j;
                    }
                }
            }
        }

        for (int[][] ints : visited) {
            for (int[] anInt : ints) {
                Arrays.fill(anInt, Integer.MAX_VALUE);
            }
        }

        System.out.println(BFS(start_y, start_x, -5, -1));

    }

    static int BFS(int _y, int _x, int cur_dir, int mirror) {
        int result = Integer.MAX_VALUE;
        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.add(new Point(_y, _x, cur_dir, mirror)); // y 좌표, x 좌표, 현재의 방향, 설치된 거울의 개수

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            if (point.y == end_y && point.x == end_x) {
                result = Math.min(result, point.mirror);
                continue;
            }

            for (int cand = 0; cand < 4; cand++) {
                int ny = point.y + dir[cand][0];
                int nx = point.x + dir[cand][1];
                int nm;
                // 다음 방향이 이전과 같은 방향일 경우.
                if(point.dir == cand)
                    nm = point.mirror;
                else
                    nm = point.mirror + 1;

                if (ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
                if (Math.abs(point.dir - cand) == 2) continue;
                if (map[ny][nx] == '*') continue;

                // 방문했을 때, 더 적은 거울을 사용할 수 있는 경우
                if (visited[cand][ny][nx] > nm) {
                    // 새로운 거울 개수를 저장하고
                    visited[cand][ny][nx] = nm;
                    // 그 곳에서 다시 탐색 시작하기
                    queue.add(new Point(ny, nx, cand, nm));
                }
            }
        }

        return result;
    }

    static class Point implements Comparable<Point>{
        int y, x, dir, mirror;

        public Point(int y, int x, int dir, int mirror) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.mirror = mirror;
        }


        @Override
        public int compareTo(Point o) {
            return this.mirror - o.mirror;
        }
    }
}
