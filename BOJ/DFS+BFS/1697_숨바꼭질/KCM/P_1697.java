import java.util.*;
import java.io.*;

class Where{
    int point=0;
    int sec=0;
    Where(int p, int s){
        point = p;
        sec = s;
    }
}
public class P_1697 {
    static int N,K;
    static Queue<Where> q = new LinkedList<>();
    static boolean [] visited = new boolean[100001];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int ans = bfs(N,K);
        System.out.println(ans);
    }

    static int bfs(int n, int k){
        q.add(new Where(n,0));
        visited[n] = true;
        while(!q.isEmpty()){
            Where locate = q.remove();
            visited[locate.point] = true;
            if(locate.point == k){
                return locate.sec;
            }
            else{
                int v1 = 2*locate.point;
                int v2 = locate.point-1;
                int v3 = locate.point+1;

                if(v1>=0 && v1 <= 100000 && !visited[v1]){
                    q.add(new Where(v1, locate.sec+1));
                    visited[v1] = true;
                }
                if(v2>=0 && v2 <= 100000 && !visited[v2]){
                    q.add(new Where(v2, locate.sec+1));
                    visited[v2] = true;
                }
                if(v3>=0 && v3 <= 100000 && !visited[v3]){
                    q.add(new Where(v3, locate.sec+1));
                    visited[v3] = true;
                }
            }

        }
        return 0;
    }
}
