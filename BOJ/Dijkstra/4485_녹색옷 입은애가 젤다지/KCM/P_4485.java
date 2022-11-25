//import java.util.*;
//import java.io.*;
//
//// 각 위치에서 상.하.좌.우로 갈 때의 값을 최신화하는데 가게될 위치 > 현재위치 + 가게될 위치 일 때만 초기화한다.
// 맨 처음 dist배열은 0,0 위치 뺴고 모두 INF로 초기화한다.
//class Point {
//    int row;
//    int col;
//    Point(int r, int c) {
//        row = r;
//        col = c;
//    }
//}
//public class P_4485 {
//    static int T, N;
//    static int [][] map;
//    static boolean [][] visited;
//    static int [][] dist;
//    static int [][] mv = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //상하좌우
//    static Queue<Point> q = new LinkedList<>();
//    static int cnt = 0;
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        while(true) { //입력이 0이 아닐 경우만 진행
//            cnt ++;
//            N = Integer.parseInt(br.readLine());
//            if(N == 0) {
//                break;
//            }
//            else {
//                map = new int[N][N];
//                dist = new int[N][N];
//                //map 입력과정
//                for(int i=0;i<N;i++) {
//                    StringTokenizer st = new StringTokenizer(br.readLine());
//                    for(int j=0;j<N;j++) {
//                        map[i][j] = Integer.parseInt(st.nextToken());
//                        dist[i][j] = Integer.MAX_VALUE; //Arrays.fill은 1차원 배열에서만 사용 가능.
//                    }
//                }
//                dist[0][0] = map[0][0];
//                solve(0, 0);
//                System.out.println("Problem " + cnt + ": " + dist[N-1][N-1]);
//            }
//        }
//    }
//    public static void solve (int r, int c) {
//        q.add(new Point(r, c));
//
//        while(!q.isEmpty()) {
//            Point v = q.remove();
//            for(int i=0;i<4;i++) {
//                int nr = v.row + mv[i][0];
//                int nc = v.col + mv[i][1];
//
//                if(nr < N && nc < N && nr >= 0 && nc >= 0) {
//                    if(dist[nr][nc] > dist[v.row][v.col] + map[nr][nc]) {
//                        dist[nr][nc] = dist[v.row][v.col] + map[nr][nc];
//                        q.add(new Point(nr, nc));
//                    }
//                }
//            }
//        }
//    }
//}
