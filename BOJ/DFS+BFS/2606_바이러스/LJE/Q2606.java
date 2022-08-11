package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q2606 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String str;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());
        for(int i=0;i<=N;++i)
            graph.add(new ArrayList<>());

        for(int i=0;i<E;++i){
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        boolean[] visited = new boolean[N+1];
        System.out.println(DFS(1, graph, visited, 0));
    }

    public static int DFS(int start, ArrayList<ArrayList<Integer>> graph, boolean[] visited, int count){
        visited[start] = true;
        for(int next:graph.get(start)){
            if(!visited[next]) {
                count++;
                count = DFS(next, graph, visited, count);
            }
        }

        return count;
    }
}
