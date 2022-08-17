package ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node>{
    int index;
    int distance;

    public Node(int index, int distance){
        this.index = index;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node o) {
        if(this.distance < o.distance)
            return -1;

        return 1;
    }
}

public class Q1916{
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    public static int[] dist = new int[1001];
    public static final int INF = (int)1e9;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        for(int i=0;i<=N;++i)
            graph.add(new ArrayList<Node>());

        for(int i=0;i<M;++i){
            String str = br.readLine();
            st = new StringTokenizer(str, " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Node(end, cost));
        }

        String str = br.readLine();
        st = new StringTokenizer(str, " ");
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        Arrays.fill(dist, INF);
        dijkstra(start);

        System.out.println(dist[end]);
    }

    public static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(start, 0));
        dist[start]=0;
        while(!pq.isEmpty()){
            Node node = pq.poll();
            int d = node.distance;
            int cur = node.index;

            if(dist[cur]<d) continue;

            for(int i=0;i<graph.get(cur).size();++i){
                int cost = dist[cur] + graph.get(cur).get(i).distance;
                if(cost < dist[graph.get(cur).get(i).index]){
                    dist[graph.get(cur).get(i).index] = cost;
                    pq.offer(new Node(graph.get(cur).get(i).index, cost));
                }
            }
        }
    }
}