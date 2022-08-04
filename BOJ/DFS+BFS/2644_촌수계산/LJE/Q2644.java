package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q2644 {

    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static int[] dist;
    public static boolean[] visited;

    public static int findShortestPath(int start, int end){
        Queue<Integer> q = new LinkedList<>();
        dist[start] = 0;
        visited[start] = true;

        q.offer(start);
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int x : graph.get(cur)){
                if(!visited[x]){
                    dist[x] = dist[cur]+1;
                    if(x==end)
                        return dist[x];
                    q.offer(x);
                    visited[x] = true;
                }
            }
        }
        return -1;

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 전체 사람 수
        dist = new int[N+1];
        visited = new boolean[N+1];

        int p1 = sc.nextInt(), p2 = sc.nextInt(); // 촌수를 구해야 하는 두 사람

        for(int i=0;i<=N;++i)
            graph.add(new ArrayList<>());

        int count = sc.nextInt();
        for(int i=0;i<count;++i){
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        System.out.println(findShortestPath(p1, p2));
    }
}
