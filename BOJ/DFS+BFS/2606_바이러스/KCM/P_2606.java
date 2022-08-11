import java.util.*;
import java.io.*;

public class P_2606 {
    static ArrayList<Integer> [] list;
    static boolean [] visited;
    static int N,M,cnt=0;
    static Queue<Integer> q = new LinkedList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        list = new ArrayList[N+1];
        visited = new boolean[N+1];

        for(int i=1;i<=N;i++){
            list[i] = new ArrayList<>();
        }
        //인접리스트 생성하는 과정.
        for(int i=0;i<M;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            list[v1].add(v2);
            list[v2].add(v1);
        }
        bfs(1);
        System.out.println(cnt);
    }
    static void bfs(int n){
        q.add(n);
        visited[n] = true;

        while(!q.isEmpty()){
            int v = q.remove();
            for(int i=0;i<list[v].size();i++){
                if(!visited[list[v].get(i)]){
                    q.add(list[v].get(i));
                    visited[list[v].get(i)] = true;
                    cnt++;
                }
            }
        }
    }
}
