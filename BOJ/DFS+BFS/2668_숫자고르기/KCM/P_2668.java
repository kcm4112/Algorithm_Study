////위쪽을 x, 아래쪽을 f(x)라고 했을 때, f(x)가 가르키는 값 (v)으로 이동하고 또, f(v)가 가르키는 값으로 이동하다가
////f(k)의 값이 최초 출발숫자인 x와 같으면 x를 정답에 추가한다.
//
//import java.util.*;
//import java.io.*;
//
//public class P_2668 {
//    static int N, answer;
//    static int [] list;
//    static boolean [] visited;
//    static ArrayList<Integer> res = new ArrayList<>();
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
//        visited = new boolean[N+1];
//        list = new int [N+1];
//        for(int i=1;i<=N;i++) { //예시와 같은 구조 만들기.
//            list[i] = Integer.parseInt(br.readLine());
//        }
//
//        for(int i=1;i<=N;i++) {
//            answer = i; //최초의 x값을 전역으로 저장하고, dfs과정 중 f(x)의 값이 answer와 같아지면 그 떄의 answer값을 정답에 추가하기.
//            visited[i] = true;
//            dfs(i);
//            visited[i] = false;
//        }
//
//        System.out.println(res.size());
//        Collections.sort(res);
//        for(int i=0;i<res.size();i++) {
//            System.out.println(res.get(i));
//        }
//    }
//    static public void dfs(int x) {
//        if(list[x] == answer) {
//            res.add(answer);
//        }
//
//        if(!visited[list[x]]) { //만약 가야할 목적지가 방문했던 곳이 아니라면?
//            visited[list[x]] = true;
//            dfs(list[x]);
//            visited[list[x]] = false;
//        }
//    }
//}
