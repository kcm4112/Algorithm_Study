package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q2580 {
    static int[][] graph = new int[9][9];
    static ArrayList<Point> zeroPoint = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if(graph[i][j] == 0)
                    zeroPoint.add(new Point(i, j));
            }
        }

        rec_func(0);

        for (int[] ints : graph) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    static boolean rec_func(int count) {
        if (count == zeroPoint.size()) {
            return true;
        }
        else {
            int y = zeroPoint.get(count).y;
            int x = zeroPoint.get(count).x;

            for (int number = 1; number < 10; number++) {
                if (isPossible(y, x, number)) {
                    graph[y][x] = number;
                    if(rec_func(count + 1))
                        return true;
                    else
                        graph[y][x] = 0;
                }
            }
        }

        return false;
    }

    static boolean isPossible(int y, int x, int number) {
        // 가로(ROW), 세로(COL) 방향을 확인
        for (int i = 0; i < 9; i++) {
            if(graph[y][i] == number)
                return false;
            if(graph[i][x] == number)
                return false;
        }
        // 3x3 영역에서 number를 넣을 수 있는가?
        int range_y = (y/3)*3;
        int range_x = (x/3)*3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(graph[range_y+i][range_x+j] == number)
                    return false;
            }
        }

        return true;
    }

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

}
