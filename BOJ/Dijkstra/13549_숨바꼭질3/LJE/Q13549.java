package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q13549 {
    public static int[] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        distance = new int[100001];
        Arrays.fill(distance, (int)1e9);

        Dijkstra(N);

        System.out.println(distance[K]);
    }

    public static void Dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int x;
            int cost;

            x = cur.index + 1;
            cost = cur.cost + 1;
            if(x<=100000 && cost<distance[x]){
                distance[x] = cost;
                pq.offer(new Node(x, cost));
            }

            x = cur.index - 1;
            cost = cur.cost + 1;
            if(0<=x && cost<distance[x]){
                distance[x] = cost;
                pq.offer(new Node(x, cost));
            }

            x = cur.index*2;
            cost = cur.cost;
            if(x<=100000 && cost<distance[x]){
                distance[x] = cost;
                pq.offer(new Node(x, cost));
            }
        }
    }

    static class Node implements Comparable<Node>{
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
