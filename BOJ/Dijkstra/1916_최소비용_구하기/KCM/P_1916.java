import java.util.*;
import java.io.*;

class Bus implements Comparable<Bus>{
    int end = 0;
    int weight = 0;
    Bus(int e, int w){
        end = e;
        weight = w;
    }

    @Override
    public int compareTo(Bus b){
        return weight - b.weight;
    }
}

public class P_1916 {
    static int N,M;
    static ArrayList<Bus> [] list;
    static boolean[] checked;
    static int [] dist;
    static PriorityQueue<Bus> q = new PriorityQueue<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        list = new ArrayList[N+1];
        for(int i=0;i<=N;i++){
            list[i] = new ArrayList<>();
        }
        checked = new boolean[N+1];
        dist = new int[N+1];

        //가장 큰 정수값으로 초기화 하기.
        Arrays.fill(dist, Integer.MAX_VALUE);

        //인접리스트 구현하기(그래프 생성 과정.)
        for(int i=0;i<M;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list[start].add(new Bus(end, d));
        }

        StringTokenizer s = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(s.nextToken());
        int fin = Integer.parseInt(s.nextToken());
//        System.out.printf("%d %d\n", start, fin);
        dijkstra(start);
        System.out.println(dist[fin]);
    }
    static void dijkstra(int start){
        q.add(new Bus(start,0));
        dist[start] = 0; //반드시 처음 출발지점의 거리는 0으로 할당해주고 시작해야함!
        while(!q.isEmpty()){
            Bus v = q.remove();

            if(checked[v.end]){
                continue;
            }
            else{
                checked[v.end] = true;
            }
            for(int i=0;i<list[v.end].size();i++){
                int next = list[v.end].get(i).end; //다음 목적지. 처음 list[v.end] = list[1]이고 1번 인접리스트의 첫 번째원소의 end 값을 가져온다!
                int value = list[v.end].get(i).weight;
//                System.out.printf("%d %d\n", next, value);
                if(!checked[next] && dist[next] > dist[v.end] + value){
                    dist[next] = dist[v.end] + value;
                    q.add(new Bus(next, dist[next]));
                }
            }
        }
    }
}
