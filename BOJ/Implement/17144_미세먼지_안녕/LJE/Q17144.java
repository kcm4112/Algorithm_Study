package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q17144 {
    static int R, C, T;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int[] cleaner = new int[2]; // 공기청정기의 행
    static int[][] graph, new_graph;
    static ArrayList<Point> dust = new ArrayList<>();
    static ArrayList<Point> next_dust = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        graph = new int[R][C];
        new_graph = new int[R][C];
        cleaner[0] = -1;
        for (int y = 0; y < R; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < C; x++) {
                int temp = Integer.parseInt(st.nextToken());
                graph[y][x] = temp;

                if (temp == -1) {
                    if(cleaner[0] == -1) {
                        cleaner[0] = y;
                    }
                    else {
                        cleaner[1] = y;
                    }
                    continue;
                }

                if (temp != 0) {
                    dust.add(new Point(y, x));
                }
            }
        }

        for (int i = 0; i < T; i++) {
            spread_dust();
            copy_graph();

            clean_dust();
            addNewDust();
        }

        int sum = 2;
        for (int[] ints : graph) {
            for (int anInt : ints) {
                sum += anInt;
            }
        }

        System.out.println(sum);

    }

    static void spread_dust() {
        for (int cand = 0; cand < dust.size(); cand++) {
            Point p = dust.get(cand);
            int y = p.y;
            int x = p.x;

            // 기존의 먼지가 사라진 경우
            if (graph[y][x] == 0) {
                continue;
            }

            // 4 방향 중 퍼져나간 방향의 개수
            int count = 0;

            for (int i = 0; i < 4; i++) {
                int ny = y + dir[i][0];
                int nx = x + dir[i][1];

                // 먼지가 범위를 벗어나는 경우
                if(!isPossible(ny, nx)) continue;
                // 공기청정기가 있거나 이미 먼지가 있는 경우
                if (graph[ny][nx] == -1) continue;

                count++;
                next_dust.add(new Point(ny, nx));
            }

            if (count > 0) {
                int dust = graph[y][x] / 5;

                for (Point next : next_dust) {
                    int ny = next.y;
                    int nx = next.x;
                    new_graph[ny][nx] += dust;
                }
                graph[y][x] -= dust * count;
            }
            next_dust.clear();
        }
    }

    static void copy_graph() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (new_graph[i][j] != 0 && new_graph[i][j] != -1) {
                    graph[i][j] += new_graph[i][j];
                }
            }
        }

        for (int y = 0; y < R; y++) {
            Arrays.fill(new_graph[y], 0);
        }
    }

    static void clean_dust() {
        // use cleaner[0]
        // down
        for (int y = cleaner[0] - 1; y > 0; y--) {
            graph[y][0] = graph[y - 1][0];
        }
        // left
        for (int x = 0; x < C -1; x++) {
            graph[0][x] = graph[0][x + 1];
        }
        // up
        for (int y = 0; y < cleaner[0]; y++) {
            graph[y][C - 1] = graph[y + 1][C - 1];
        }
        // right
        for (int x = C - 1; x > 1; x--) {
            graph[cleaner[0]][x] = graph[cleaner[0]][x - 1];
        }
        graph[cleaner[0]][1] = 0;

        // use cleaner[1]
        // up
        for (int y = cleaner[1] + 1; y < R - 1; y++) {
            graph[y][0] = graph[y + 1][0];
        }
        // left
        for (int x = 0; x < C - 1; x++) {
            graph[R - 1][x] = graph[R- 1][x + 1];
        }
        // down
        for (int y = R - 1; y > cleaner[1]; y--) {
            graph[y][C - 1] = graph[y - 1][C - 1];
        }
        // right
        for (int x = C - 1; x > 1; x--) {
            graph[cleaner[1]][x] = graph[cleaner[1]][x - 1];
        }
        graph[cleaner[1]][1] = 0;
    }

    static void addNewDust() {
        dust.clear();

        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                if(graph[y][x] == 0 || graph[y][x] == -1) continue;

                dust.add(new Point(y, x));
            }
        }
    }

    static boolean isPossible(int y, int x) {
        if (y < 0 || x < 0 || y >= R || x >= C) {
            return false;
        }
        return true;
    }

    static class Point{
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
