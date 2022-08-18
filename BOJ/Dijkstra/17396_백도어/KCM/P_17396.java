import java.util.*;
import java.io.*;

class Place implements Comparable<Place>{
    int end = 0;
    long time = 0;
    Place(int e, long t){
        end = e;
        time = t;
    }
    @Override
    public int compareTo(Place p){
        if(time > p.time){
            return 1;
        }
        else{
            return -1;
        }
    }
}
public class P_17396 {
    static int N, M;
    static int [] pit_fall;
    static long [] dist;
    static PriorityQueue<Place> q = new PriorityQueue<>();
    static ArrayList<Place> [] list;
    static boolean [] checked;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        list = new ArrayList[N];
        dist = new long [N];
        checked = new boolean[N];
        Arrays.fill(dist, Long.MAX_VALUE);

        for(int i=0;i<N;i++){
            list[i] = new ArrayList<>();
        }

        M = Integer.parseInt(st.nextToken());
        pit_fall = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            pit_fall[i] = Integer.parseInt(st.nextToken());
        }
        //인접리스트생성
        for(int i=0;i<M;i++){
            StringTokenizer s = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(s.nextToken());
            int fin = Integer.parseInt(s.nextToken());
            int weight = Integer.parseInt(s.nextToken());
            //양방향 그래프이기때문에 아래와 같이 인접리스트를 양쪽에 다 생성해줘야함!
            list[start].add(new Place(fin, weight));
            list[fin].add(new Place(start,weight));
        }

        dijkstra(0);
        if(dist[N-1] == Long.MAX_VALUE){
            System.out.println(-1);
        }
        else{
            System.out.println(dist[N-1]);
        }

    }
    static void dijkstra(int s){
        q.add(new Place(s, 0));
        dist[s] = 0;

        while(!q.isEmpty()){
            Place v = q.remove();
            if(checked[v.end]){
                continue;
            }
            checked[v.end] = true;
            for(int i=0;i<list[v.end].size();i++){
                int next = list[v.end].get(i).end;
                long w = list[v.end].get(i).time;
                if(next != N-1 && pit_fall[next] == 1){ //만약 넥서스지점이 아닌데 다음 목적지가 통과할 수 없다면 그냥 패스
                    continue;
                }
                if(dist[next] > dist[v.end] + w){
                    dist[next] = dist[v.end] + w;
                    q.add(new Place(next, dist[next]));
                }
            }
        }
    }
}
