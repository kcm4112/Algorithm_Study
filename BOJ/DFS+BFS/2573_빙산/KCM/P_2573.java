//import java.util.*;
//import java.io.*;
////class Point{
////    int row = 0;
////    int col = 0;
////    Point(int r, int c){
////        row = r;
////        col = c;
////    }
////}
//public class P_2573 {
//    static int [][] map;
//    static boolean [][] visited;
//    static int [][] mv = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //상하좌우 이동.
//    static int N;
//    static int M;
//    static  int year;
//    static  int num;
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        map = new int [N][M]; //빙산
//        //초기 빙산 상태 입력
//        for(int i=0;i<N;i++){
//            StringTokenizer s = new StringTokenizer(br.readLine());
//            for(int j=0;j<M;j++){
//                map[i][j] = Integer.parseInt(s.nextToken());
//            }
//        }
//        //빙산이 2개인지 체크 -> 아니라면 높이 줄이기 과정을 무한반복!!
//        year = 0;
//        while(true){
//            visited = new boolean[N][M]; // 매년 빙산의 모습이 바뀌므로, visited맵을 초기화해준다!
//            num = 0;
//            for(int i=0;i<N;i++){
//                for(int j=0;j<M;j++){
//                    if(map[i][j]!=0 && !visited[i][j]){
//                        bfs(i,j);
//                        num++;
//                    }
//                }
//            }
//            if(num > 1){
//                break;
//            }
//            if(num==0){
//                break;
//            }
//            int [][] newmap = new int[N][M];
//            for(int i=0;i<N;i++){
//                for(int j=0;j<M;j++){
//                    if(map[i][j]>0){
//                        int num_zero=0;
//                        for(int k=0;k<4;k++){
//                            int nnr = i + mv[k][0];
//                            int nnc = j + mv[k][1];
//                            if(map[nnr][nnc]==0){ //주변에 0개수 count!
//                                num_zero++;
//                            }
//                        }
//                        int new_num = map[i][j] - num_zero;
//                        if(new_num<0){ //0개수를 뺏는데 음수면 그냥 0으로 업데이트!
//                            newmap[i][j] = 0;
//                        }
//                        else{
//                            newmap[i][j] = new_num;
//                        }
//                    }
//                }
//            }
//            map = newmap.clone();
//            year++; //빙산을 녹인후 1년을 ++ 해준다.
//        }
//        if(num == 0){
//            System.out.println(0);
//        }
//        else{
//            System.out.println(year);
//        }
//    }
//    static void bfs(int r, int c){
//        Queue<Point> q = new LinkedList<>();
//        visited[r][c] = true;
//        q.add(new Point(r, c));
//
//        while(!q.isEmpty()){
//            //먼저 빙산을 모두 탐색한다. 만약 한 번의 bfs 호출을 통해 모든 빙산이 있는 부분이 true가 되면 모든 빙산이 상하좌우로 이어져있다는 뜻이므로 빙산은 1개!
//            Point v = q.remove();
//            for(int i=0;i<4;i++){
//                int nr = v.row + mv[i][0];
//                int nc = v.col + mv[i][1];
//                if(nr>=0 && nr<N && nc>=0 && nc<M){
//                    if(map[nr][nc] > 0 && !visited[nr][nc]){
//                        q.add(new Point(nr, nc));
//                        visited[nr][nc] = true;
//                    }
//                }
//            }
//        }
//    }
//}
