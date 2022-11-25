//import java.io.*;
//import java.util.*;
//
//class Where {
//    int point = 0;
//    int sec = 0;
//
//    Where(int p, int s) {
//        point = p;
//        sec = s;
//    }
//}
//public class P_13549 {
//    static int N,K;
//    static int res = Integer.MAX_VALUE;
//    static boolean [] visited = new boolean[100001];
//    static Queue<Where> q = new LinkedList<>();
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken()); //수빈이 위치
//        K = Integer.parseInt(st.nextToken()); //동생 위치
//
//        dijkstra(N, K);
//        System.out.println(res);
//
//    }
//
//    public static void dijkstra(int n, int k) {
//        q.add(new Where(n, 0));
//
//        while(!q.isEmpty()) {
//            Where v = q.remove();
//            visited[v.point] = true;
//
//            if(v.point == k) {
//                res = Math.min(res, v.sec);
//            }
//            else {
//                //수빈이가 갈 수 있는 경우의수
//                int c1 = v.point - 1; //X-1위치
//                int c2 = v.point + 1; //X+1위치
//                int c3 = v.point * 2; //2*X위치
//
//                //순간이동이 아닌 경우는 +1초 해주고, 순간이동은 그냥 v.sec 그대로 넘겨준다.
//                if (c1>=0 && c1 <= 100000 && !visited[c1]) {
//                    q.add(new Where(c1, v.sec+1));
//                }
//
//                if (c2>=0 && c2 <= 100000 && !visited[c2]) {
//                    q.add(new Where(c2, v.sec+1));
//                }
//
//                if (c3>=0 && c3 <= 100000 && !visited[c3]) {
//                    q.add(new Where(c3, v.sec));
//                }
//            }
//        }
//    }
//}
