//import java.io.*;
//import java.util.*;
//
////각 위치에서 상하좌우로 이동할 때 벽을 몇개 부숴야 하는지 최신화 해야한다.
////단, 다음위치까지 벽 부순 개수(dist배열에 저장됨) > 현재위치까지 벽 부순 개수 + 이동할 위치까지 가는데 추가적으로 부숴야하는 벽 개수일 때만 최신화해준다!
////dist배열은 처음에 INF로 초기화해준다.
//class Point {
//    int row = 0;
//    int col = 0;
//    Point(int r, int c) {
//        row = r;
//        col = c;
//    }
//}
//public class P_1261 {
//    static int N,M;
//    static int [][] map;
//    static int [][] dist;
//    static Queue<Point> q = new LinkedList<>();
//    static int [][] mv = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        M = Integer.parseInt(st.nextToken());
//        N = Integer.parseInt(st.nextToken());
//        map = new int[N][M];
//        dist = new int[N][M];
//        for(int i=0;i<N;i++) {
//            String s = br.readLine();
//            for(int j=0;j<M;j++) {
//                map[i][j] = Integer.parseInt(s.charAt(j) + "");
//                dist[i][j] = Integer.MAX_VALUE;
//            }
//        }
//        dist[0][0] = map[0][0];
//        dijkstra(0, 0);
//        System.out.println(dist[N-1][M-1]);
//
//
//    }
//
//    public static void dijkstra(int r, int c) {
//        q.add(new Point(r, c));
//
//        while(!q.isEmpty()) {
//            Point v = q.remove();
//            for(int i=0;i<4;i++) {
//                int nr = v.row + mv[i][0];
//                int nc = v.col + mv[i][1];
//
//                if(nr < N && nc < M && nr >= 0 && nc >= 0) {
//                    if(dist[nr][nc] > dist[v.row][v.col] + map[nr][nc]) {
//                        dist[nr][nc] = dist[v.row][v.col] + map[nr][nc];
//                        q.add(new Point(nr, nc));
//                    }
//                }
//            }
//        }
//    }
//}
