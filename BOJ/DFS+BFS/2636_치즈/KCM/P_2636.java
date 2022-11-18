//import java.io.*;
//import java.util.*;
//class Posi {
//    int row;
//    int col;
//    Posi(int r, int c) {
//        row = r;
//        col = c;
//    }
//}
//public class P_2636 {
//    static int N,M;
//    static int [][] map;
//    static int cheese = 0;
//    static int cnt = 0;
//    static int time = 0;
//    static int remain = 0;
//    static int [][] mv = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        map = new int [N][M];
//
//        for(int i=0;i<N;i++) {
//            st = new StringTokenizer(br.readLine());
//            for(int j=0; j<M; j++) {
//                int value = Integer.parseInt(st.nextToken());
//                map[i][j] = value;
//                if(value==1) {
//                    cheese++;
//                }
//            }
//        }
//
////        for(int [] a : map) {
////            for(int b : a) {
////                System.out.printf("%d ", b);
////            }
////            System.out.println();
////        }
//        while(cheese != 0) {
//            time++;
//            remain = cheese;
//            bfs(0, 0);
//        }
//
//        System.out.println(time);
//        System.out.println(remain);
//
//    }
//
//    static public void bfs(int r, int c) {
//        Queue<Posi> q = new LinkedList<>();
//        boolean [][] visited = new boolean[N][M];
//        q.add(new Posi(r, c));
//
//        while(!q.isEmpty()) {
//            Posi v = q.remove();
//
//            for(int i=0; i<4; i++) {
//                int nr = v.row + mv[i][0];
//                int nc = v.col + mv[i][1];
//
//                if(nr >= 0 && nr < N && nc >= 0 && nc < M) {
//                    if(!visited[nr][nc]) {
//                        if(map[nr][nc] == 1) {
//                            map[nr][nc] = 0;
//                            visited[nr][nc] = true;
//                            cheese--;
//                        }
//                        else {
//                            visited[nr][nc] = true;
//                            q.add(new Posi(nr, nc));
//                        }
//                    }
//
//                }
//            }
//        }
//    }
//}
