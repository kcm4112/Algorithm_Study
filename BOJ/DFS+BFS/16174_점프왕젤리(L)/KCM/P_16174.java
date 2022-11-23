//import java.util.*;
//import java.io.*;
//class Point {
//    int row = 0;
//    int col = 0;
//
//    Point(int r, int c) {
//        row = r;
//        col = c;
//    }
//}
//public class P_16174 {
//    static int N;
//    static int [][] map;
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
//        map = new int [N][N];
//        StringTokenizer st;
//
//        for(int i=0;i<N;i++) {
//            st = new StringTokenizer(br.readLine());
//            for(int j=0;j<N;j++) {
//                map[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
//        dfs(0, 0); // (0,0위치에서 출발한다.)
//
//    }
//    static public void dfs(int v1, int v2) {
//        Queue<Point> q = new LinkedList<>();
//        boolean [][] visited = new boolean[N][N];
//        q.add(new Point(v1, v2));
//        while(!q.isEmpty()) {
//            Point v = q.remove();
//            if(v.row == N-1 && v.col == N-1) { //도착지에 오면 print
//                System.out.println("HaruHaru");
//                return;
//            }
//            //아래쪽, 오른쪽으로 map[v.row][v.col]의 값만큼 이동해야한다.
//            //그러나 선언된 배열의 크기를 벗어나서는 안됨.
//
//            if(map[v.row][v.col] != 0) { //현재 위치가 도착지점이 아닌데 map[v.row][v.col]의 값이 0이라면 시행할 필요 없음.
//                //아래쪽으로 이동하는 경우
//                int nr_b = v.row + map[v.row][v.col];
//                int nc_b = v.col;
//                if(nr_b < N && nc_b < N && !visited[nr_b][nc_b]) { //가야할 위치가 배열의 인덱스를 벗어나서는 안됨!
//                    visited[nr_b][nc_b] = true;
//                    q.add(new Point(nr_b, nc_b));
//                }
//
//                //오른쪽으로 이동하는 경우
//                int nr_r = v.row;
//                int nc_r = v.col + map[v.row][v.col];
//                if(nr_r < N && nc_r < N && !visited[nr_r][nc_r]) { //가야할 위치가 배열의 인덱스를 벗어나서는 안됨!
//                    visited[nr_r][nc_r] = true;
//                    q.add(new Point(nr_r, nc_r));
//                }
//            }
//        }
//        System.out.println("Hing");
//        return;
//    }
//}
