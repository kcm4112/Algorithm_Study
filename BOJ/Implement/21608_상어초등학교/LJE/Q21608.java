package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q21608 {
    static int N;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int[] order; // 학생들을 앉힐 순서
    static boolean[] check; // 각 인덱스에 해당하는 학생이 앉았는지 체크
    static int[][] seat; // 자리 배치도
    static boolean[][] visited;
    static ArrayList<Point>[] student_seat; // 해당 학새이 앉은 위치
    static ArrayList<Integer>[] like;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        // init
        like = new ArrayList[N*N + 1];
        student_seat = new ArrayList[N*N + 1];
        for (int i = 0; i < N*N+1; i++) {
            like[i] = new ArrayList<>();
            student_seat[i] = new ArrayList<>();
        }
        order = new int[N*N + 1];
        check = new boolean[N*N + 1];
        seat = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        for (int i = 1; i <= N*N; i++) {
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            order[i] = student;
            for (int j = 0; j < 4; j++) {
                like[student].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 1; i <= N*N; i++) {
            int cur_y = 0;
            int cur_x = 0;
            int cur = order[i];
            ArrayList<Integer> cur_like_seat = new ArrayList<>(); // cur이 좋아하면서 앉아있는 학생

            // 좋아하는 학생이 한 명이라도 앉았다면, 1
            for (int j = 0; j < 4; j++) {
                if (check[like[cur].get(j)]) {
                    cur_like_seat.add(like[cur].get(j));
                }
            }

            // 좋아하는 학생이 아무도 앉아있지 않은 경우
            if (cur_like_seat.size() == 0) {
                int max = Integer.MIN_VALUE;
                for (int y = 1; y <= N; y++) {
                    for (int x = 1; x <= N; x++) {
                        if(visited[y][x])
                            continue;

                        int count = countEmpty(y, x);
                        if (max < count) {
                            max = count;
                            cur_y = y;
                            cur_x = x;
                        }
                    }
                }
            }
            else {
                int max = 0;
                int empty = -1;
                for (int y = 1; y <= N; y++) {
                    for (int x = 1; x <= N; x++) {
                        if(visited[y][x])
                            continue;

                        int count = countLike(cur, y, x);
                        if (max <= count) {
                            if (max == count) {
                                int temp = countEmpty(y, x);
                                if (empty < temp) {
                                    empty = temp;
                                    cur_y = y;
                                    cur_x = x;
                                }
                            } else {
                                empty = countEmpty(y, x);
                                cur_y = y;
                                cur_x = x;
                            }
                            max = count;
                        }
                    }
                }
            }

            seat[cur_y][cur_x] = cur;
            visited[cur_y][cur_x] = true;
            check[cur] = true;
            student_seat[cur].add(new Point(cur_y, cur_x)); // cur이 앉은 위치 저장

        }

        System.out.println(getAnswer());

    }

    static int getAnswer() {
        int sum = 0;

        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                int count = 0;

                for (int i = 0; i < 4; i++) {
                    int ny = y + dir[i][0];
                    int nx = x + dir[i][1];
                    if (ny < 1 || nx < 1 || ny >= N + 1 || nx >= N + 1) continue;
                    if(like[seat[y][x]].contains(seat[ny][nx]))
                        count++;
                }

                switch (count) {
                    case 0:
                        sum += 0;
                        break;
                    case 1:
                        sum += 1;
                        break;
                    case 2:
                        sum += 10;
                        break;
                    case 3:
                        sum += 100;
                        break;
                    case 4:
                        sum += 1000;
                        break;
                    default:
                        break;
                }
            }
        }

        return sum;
    }

    static int countLike(int s, int y, int x) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int ny = y + dir[i][0];
            int nx = x + dir[i][1];

            if (ny < 1 || nx < 1 || ny >= N + 1 || nx >= N + 1) continue;

            if(like[s].contains(seat[ny][nx]))
                count++;
        }

        return count;
    }

    static int countEmpty(int y, int x) {
        int count = 0;

        for (int i = 0; i < 4; i++) {
            int ny = y + dir[i][0];
            int nx = x + dir[i][1];

            if (ny < 1 || nx < 1 || ny >= N + 1 || nx >= N + 1) continue;

            if(seat[ny][nx] == 0)
                count++;
        }

        return count;
    }

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

}
