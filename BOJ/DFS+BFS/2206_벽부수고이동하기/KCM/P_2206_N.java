//import java.io.*;
//import java.util.*;
//
//
//class Pos {
//    int row,col,dis,cnt;
//    Pos(int x, int y, int c, int w) {
//        row = x;
//        col = y;
//        dis = c;
//        cnt = w;
//    }
//}
//public class P_2206_N {
//    static int N,M;
//    static int [][] map;
//    static int [][] visited;
//    static int [][] mv = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        map = new int[N][M];
//        visited = new int [N][M];
//
//        for(int i=0;i<N;i++) {
//            String s = br.readLine();
//            for(int j=0;j<M;j++) {
//                int value = Integer.parseInt(s.charAt(j) + "");
//                map[i][j] = value;
//                visited[i][j] = Integer.MAX_VALUE;
//            }
//        }
//
////        for(int [] a: map) {
////            for(int b : a) {
////                System.out.printf("%d ",b);
////            }
////            System.out.println();
////        }
//        bfs(0, 0);
//    }
//
//    static public void bfs(int r, int c) {
//        Queue<Pos> q = new LinkedList<>();
//        q.add(new Pos(r, c, 1, 0));
//
//        while(!q.isEmpty()) {
//            Pos v = q.remove();
//            if(v.row == N-1 && v.col == M-1) {
//                System.out.println(v.dis);
//                return;
//            }
//            for(int a = 0;a<4;a++) {
//                int nr = v.row + mv[a][0];
//                int nc = v.col + mv[a][1];
//
//                if(nr >= 0 && nr < N && nc >= 0 && nc < M) {
//                    if(visited[nr][nc] > v.cnt) { // 갈 수 있는 곳 1차 통과
//                        if(map[nr][nc]==0) { //갈 수 있는 곳이라면
//                            q.add(new Pos(nr, nc, v.dis + 1, v.cnt));
//                            //만약 cnt==0인 놈이 통과했다는 걸 모른다면 그 길을 갔을 때, 최단경로가 아니게 된다.
//                            //그래서 위에서 visited[nr][nc] > v.cnt 를 해주어서 만약 0 > 1 이 나왔을 때는 아직 길을 뚫어본적 없는 놈이 통과해야 맞는 것임.
//                            visited[nr][nc] = v.cnt;
//
//
//                        }
//                        else { //갈 수 없는 곳이라면
//                            if(v.cnt<1) { //아직까지 벽을 한 번도 안뚫었을 경우
//                                q.add(new Pos(nr, nc, v.dis+1, v.cnt + 1));
//                                visited[nr][nc] = v.cnt + 1;
//                            }
//                        }
//                    }
////                    else { // 벽인 곳.
////                        if(v.cnt<1) {
////                            q.add(new Pos(nr, nc, v.dis+1, v.cnt + 1));
////
////                        }
////                    }
//                }
//            }
//        }
//
//        System.out.println(-1);
//        return;
//    }
//}
