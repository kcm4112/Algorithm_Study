package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q15900 {
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static boolean[] visited = new boolean[500001];
    public static int depth_sum = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for(int i=0;i<=N;++i)
            graph.add(new ArrayList<>());

        for(int i=0;i<N-1;++i){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        DFS(1, 0);
        if(depth_sum%2!=0)
            System.out.println("Yes");
        else
            System.out.println("No");

    }

    public static void DFS(int v, int count){
        visited[v] = true;
        for(int i=0;i<graph.get(v).size();++i){
            int y = graph.get(v).get(i);
            if(!visited[y]) {
                DFS(y, count+1);
            }
        }
        if(v!=1 && graph.get(v).size()==1)
            depth_sum += count;
    }

}