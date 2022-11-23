////리프노드의 총 depth를 다 더한 개수가 짝수 -> 성원 패배, 홀수 -> 성원 승리
////dfs를 통해 리프 노드의 개수를 리프 노드의 depth를 다 더하여 홀/짝을 구분하여 Yes or No 출력하기
//
//import java.util.*;
//import java.io.*;
//
//public class P_15900 {
//    static int N;
//    static int cnt = 0;
//    static boolean [] leaf; //리프노드이면 false, 아니면 true로 저장
//    static boolean [] visited;
//    static ArrayList<Integer> [] list;
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//        N = Integer.parseInt(br.readLine());
//        leaf = new boolean[N+1];
//        list = new ArrayList[N+1];
//
//        for(int i = 1;i<=N;i++) { //list배열 초기화
//            list[i] = new ArrayList<>();
//        }
//
//        for(int i=0;i<N-1;i++) {
//            st = new StringTokenizer(br.readLine());
//            int a = Integer.parseInt(st.nextToken());
//            int b = Integer.parseInt(st.nextToken());
//            list[a].add(b);
//            list[b].add(a);
//        }
//        visited = new boolean[N+1];
//        visited[1] = true;
//        dfs(1, 0);
////        System.out.println(cnt);
//        if(cnt%2 == 0) { //리프노드의 개수가 짝수이면
//            System.out.println("No");
//        }
//        else {
//            System.out.println("Yes");
//        }
//    }
//
//    public static void dfs(int cur, int depth) {
//        visited[cur] = true;
//        for(int i=0;i<list[cur].size();i++) {
//            int v = list[cur].get(i);
//            if(!visited[v]) {
//                dfs(v, depth+1);
//            }
//        }
//        if(cur != 1 && list[cur].size() == 1) { //만약 루트노드가 아닌데, 그 인접리스트의 사이즈가 1이면 리프노드이다.
//            cnt = cnt + depth;
//        }
//    }
//}
