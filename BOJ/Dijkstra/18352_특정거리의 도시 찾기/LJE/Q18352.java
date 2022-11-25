package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node18352 implements Comparable<Node18352>{
    int index;
    int distance = 1;

    public Node18352(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node18352 o) {
        if(this.distance< o.distance)
            return -1;
        return 1;
    }
}

public class Q18352 {
    public static int N, M, K, X;
    public static ArrayList<ArrayList<Node18352>> graph = new ArrayList<>();
    public static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 도시 개수
        M = Integer.parseInt(st.nextToken()); // 간선 개수
        K = Integer.parseInt(st.nextToken()); // 거리 정보
        X = Integer.parseInt(st.nextToken()); // 출발 도시

        distance = new int[N+1];
        Arrays.fill(distance, (int)1e9);
        for(int i=0;i<=N;++i)
            graph.add(new ArrayList<>());

        for(int i=0;i<M;++i){
            st = new StringTokenizer(br.readLine());
            graph.get(Integer.parseInt(st.nextToken())).add(new Node18352(Integer.parseInt(st.nextToken()), 1));
        }

        Dijkstra(X);

        int flag = 0;
        for(int i=1;i<=N;++i){
            if(distance[i]==K){
                System.out.println(i);
                flag = 1;
            }
        }

        if(flag==0)
            System.out.println(-1);
    }

    public static void Dijkstra(int start){
        PriorityQueue<Node18352> pq = new PriorityQueue<>();

        pq.offer(new Node18352(start, 0));
        distance[start] = 0;
        while(!pq.isEmpty()){
            Node18352 node = pq.poll();
            int dist = node.distance;
            int cur = node.index;

            if(distance[cur] < dist) continue;

            for(int i=0;i<graph.get(cur).size();++i){
                int cost = distance[cur] + graph.get(cur).get(i).distance;
                if(cost < distance[graph.get(cur).get(i).index]){
                    distance[graph.get(cur).get(i).index] =cost;
                    pq.offer(new Node18352(graph.get(cur).get(i).index, cost));
                }
            }
        }
    }
}
