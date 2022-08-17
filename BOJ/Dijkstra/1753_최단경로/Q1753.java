package ShortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
    int index;
    int distance;

    public Node(int i, int d){
        this.index = i;
        this.distance = d;
    }

    @Override
    public int compareTo(Node o) {
        if(this.distance < o.distance)
            return -1;
        return 1;
    }
}

public class Q1753 {
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    public static final int INF = (int)1e9;
    public static int[] distance = new int[20001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();
        st = new StringTokenizer(str, " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        for(int i=0;i<=V;++i)
            graph.add(new ArrayList<>());
        Arrays.fill(distance, INF);

        for(int i=0;i<E;++i){
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Node(end, cost));
        }

        Dijkstra(K);
        for (int i=1;i<=V;++i) {
            if(distance[i]==INF)
                System.out.println("INF");
            else
                System.out.println(distance[i]);
        }
    }

    public static void Dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while(!pq.isEmpty()){
            Node node = pq.poll();
            int cur = node.index;
            int dist = node.distance;

            if(distance[cur]<dist) continue;

            for(int i=0;i<graph.get(cur).size();++i){
                int cost = distance[cur] + graph.get(cur).get(i).distance;
                if (cost < distance[graph.get(cur).get(i).index]) {
                    distance[graph.get(cur).get(i).index] = cost;
                    pq.offer(new Node(graph.get(cur).get(i).index, cost));
                }
            }
        }
    }
}
