//import java.util.*;
//import java.io.*;
//
//public class P_1987 {
//    static int R, C; //행과 열 입력받기
//    static Character [][] map;
//    static boolean [][] visited;
//    static int len = 1;
//    static int max = Integer.MIN_VALUE;
//    static int [][] mv = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //상하좌우
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        R = Integer.parseInt(st.nextToken());
//        C = Integer.parseInt(st.nextToken());
//        map = new Character[R][C];
//        visited = new boolean[R][C];
//
//        for(int i=0;i<R;i++) {
//            String s = br.readLine();//한 줄씩 읽어서
//            for(int j=0;j<C;j++) {
//                map[i][j] = s.charAt(j);
//            }
//        }
//        visited[0][0] = true;
//        dfs(0, 0, len, map[0][0] + "");
//        System.out.println(max);
//    }
//
//    static public void dfs(int x, int y, int length, String alpha) {
//        max = Math.max(max, length);
//        for(int k = 0;k<4;k++) {
//            int nr = x + mv[k][0];
//            int nc = y + mv[k][1];
//
//            if(nr<R && nr >=0 && nc < C && nc >=0) {
//                if(!visited[nr][nc]) {
//                    if(!alpha.contains(map[nr][nc] + "")) {
//                        visited[nr][nc] = true;
////                        alpha = alpha + map[nr][nc] + "";
//                        dfs(nr, nc, length + 1, alpha + map[nr][nc] + "");
////                        alpha = alpha.substring(0, alpha.length()-1);
//                        visited[nr][nc] = false;
//                        System.out.println(alpha);
//                    }
//                }
//
//            }
//        }
//    }
//}
