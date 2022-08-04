import java.nio.Buffer;
import java.util.*;
import java.io.*;
//1. 결국 주어진 2개의 사람간에 이어진 선의 개수를 구하는 문제.
//2. 서로 친척관계라면 그 관계를 나타내주어야 한다.(그래프로 이어줘야함!)
//3. 서로 관계를 list로 표시

public class P_2644 {
    static int n, m, x, y, ans=0;
    static ArrayList<Integer>[] list;
    static boolean [] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); //전체 사람의 수
        list = new ArrayList[n+1];
        visited = new boolean[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine()); //관계의 수
        for(int i=1;i<=n;i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<m;i++){
            StringTokenizer s = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(s.nextToken());
            int child = Integer.parseInt(s.nextToken());
            list[parent].add(child);
            list[child].add(parent);
        }

        dfs(x, 0);
        if(ans>0){
            System.out.println(ans);
        }
        else{
            System.out.println(-1);
        }
    }
    //cnt는 전역변수로 선언하여 계속 증가시켜주면 안됨!
    //틀린 경로일 경우에는 cnt를 다시 감소시켜주어야하는데 전역변수이면 그냥 감소가 안되고 계속 증가한다!
    //그래서 반드시 cnt를 dfs의 파라미터로 같이 넘겨주어야함!!
    static void dfs(int v, int cnt){
        if(v==y){
            ans = cnt;
            return;
        }
        visited[v] = true;
        for(int i=0;i<list[v].size();i++){
            if(!visited[list[v].get(i)]){
                dfs(list[v].get(i), cnt+1);
            }
        }
    }
}
