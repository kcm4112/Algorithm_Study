//import java.io.*;
//import java.util.*;
//
//public class P_18352 {
//    static int N, M, K, X;
//    static ArrayList<Integer> [] list;
//    static int [] dist;
//    static boolean [] visited;
//    static Queue<Integer> q = new LinkedList<>();
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        N = Integer.parseInt(st.nextToken());
//        list = new ArrayList[N+1];
//        dist = new int[N+1];
//        visited = new boolean[N+1];
//        Arrays.fill(dist, Integer.MAX_VALUE); //INF값으로 초기화
//        M = Integer.parseInt(st.nextToken());
//        K = Integer.parseInt(st.nextToken());
//        X = Integer.parseInt(st.nextToken());
////        System.out.printf("%d %d %d %d /n", N, M, K, X);
//        for(int i=1;i<=N;i++) {
//            list[i] = new ArrayList<>();
//        }
//
//        for(int i=0;i<M;i++) {
//            st = new StringTokenizer(br.readLine());
//            int start = Integer.parseInt(st.nextToken());
//            int end = Integer.parseInt(st.nextToken());
//            list[start].add(end);
//        }
//
//        diskstra(X);
//        int cnt=0;
//        for(int i=1;i<=N;i++) {
//            if(dist[i] == K) {
//                System.out.println(i);
//                cnt++;
//            }
//        }
//        if(cnt==0) {
//            System.out.println(-1);
//        }
//    }
//
//    public static void diskstra(int x) {
//        q.add(x);
//        dist[x] = 0;
//
//        while(!q.isEmpty()) {
//            int v = q.remove();
//
//            if(visited[v]) {
//                continue;
//            }
//            else {
//                visited[v] = true;
//            }
//
//            for(int i=0;i<list[v].size();i++) {
//                int end = list[v].get(i);
//                int weight = 1;
//
//                if(dist[end] > dist[v] + weight) {
//                    dist[end] = dist[v] + weight;
//                    q.add(end);
//                }
//            }
//
//        }
//
//    }
//}
