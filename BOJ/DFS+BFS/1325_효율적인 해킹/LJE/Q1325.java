package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q1325 {
    public static ArrayList<Integer>[] list;
    public static int[] weight;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N+1];
        for(int i=1;i<N+1;++i){
            list[i] = new ArrayList<>();
        }

        weight = new int[N+1];
        visited = new boolean[N+1];
        for(int i=0;i<M;++i){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
        }

        for(int i=1;i<N+1;++i){
            visited = new boolean[N+1];
            DFS(i);
        }

        int max=0;
        for(int i=1;i<N+1;++i){
            max=Math.max(max, weight[i]);
        }

        for(int i=1;i<N+1;++i){
            if(max==weight[i])
                System.out.print(i+" ");
        }

    }
    public static void DFS(int n){
        visited[n] = true;
        for (int index : list[n]) {
            if(!visited[index]){
                weight[index]++;
                DFS(index);
            }
        }
    }
}