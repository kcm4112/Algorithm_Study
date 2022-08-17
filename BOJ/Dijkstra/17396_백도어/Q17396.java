//package ShortestPath;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.PriorityQueue;
//import java.util.StringTokenizer;
//
//class Node implements Comparable<Node>{
//    int index;
//    long distance;
//
//    public Node(int i, long d){
//        this.index = i;
//        this.distance = d;
//    }
//
//    @Override
//    public int compareTo(Node o) {
//        if(this.distance < o.distance)
//            return -1;
//        return 1;
//    }
//}
//
//public class Q17396 {
//    public static boolean[] go;
//    public static final long INF = Long.MAX_VALUE;
//    public static long[] distance;
//    public static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//
//        String str = br.readLine();
//        st = new StringTokenizer(str, " ");
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//
//        str = br.readLine();
//        st = new StringTokenizer(str, " ");
//        go = new boolean[N];
//        for(int i=0;i<N;++i){
//            if (Integer.parseInt(st.nextToken())==1) {
//                go[i]=false;
//            }
//            else
//                go[i]=true;
//        }
//
//        distance = new long[N];
//        Arrays.fill(distance, INF);
//
//        for(int i=0;i<N;++i)
//            graph.add(new ArrayList<>());
//
//        for(int i=0;i<M;++i){
//            str = br.readLine();
//            st = new StringTokenizer(str, " ");
//            int start = Integer.parseInt(st.nextToken());
//            int end = Integer.parseInt(st.nextToken());
//            int cost = Integer.parseInt(st.nextToken());
//
//            graph.get(start).add(new Node(end, cost));
//            graph.get(end).add(new Node(start, cost));
//        }
//
//        Dijkstra(0, N-1);
//
//        if(distance[N-1]==INF)
//            System.out.println(-1);
//        else
//            System.out.println(distance[N-1]);
//    }
//
//    public static void Dijkstra(int start, int end){
//        PriorityQueue<Node> pq = new PriorityQueue<>();
//        pq.offer(new Node(start, 0));
//        distance[start] = 0;
//
//        while(!pq.isEmpty()){
//            Node node = pq.poll();
//            int cur = node.index;
//            long dist = node.distance;
//
//            if(distance[cur]<dist) continue;
//
//            for(int i=0;i<graph.get(cur).size();++i){
//                if(!go[graph.get(cur).get(i).index]&&graph.get(cur).get(i).index!=end)
//                    continue;
//                long cost = distance[cur] + graph.get(cur).get(i).distance;
//                if(cost < distance[graph.get(cur).get(i).index]){
//                    distance[graph.get(cur).get(i).index] = cost;
//                    pq.offer(new Node(graph.get(cur).get(i).index, cost));
//                }
//            }
//        }
//    }
//}
