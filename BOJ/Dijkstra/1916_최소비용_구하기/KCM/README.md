## 🧩 풀이
1. 출발지에서 도착지까지 인접 노드를 거쳐 갈 수 있는 최단 경로를 구하는 문제이다.

2. 도착지가 같지만 비용이 다른 입력이 여러개(또는 엄청 많이) 주어질 수 있으므로, 우선순위큐를 구현하여 while문을 적게 돌림으로써 시간 초과를 방지하였다.

3. 한 번 거쳐간 노드는 checked 표시를 해주었다.

---

## 🧩 전체 코드
```java
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
```

---
## 🧩 Review
우선순위큐를 사용해야하는 이유.

 

>예를 들어, 정답 경로가 1-2-4-5이라고 가정하자.

>만약, 내가 우선순위큐를 사용하지 않아서 1-4-5 경로를 먼저 탐색하여 dist배열을 최신화 했다고 하면, 이 다음에 1-2-4-5경로는 이미 4번 노드가 방문한 것으로 되어있기 때문에 탐색하지 못한다!! 그래서 답을 찾을 수 없다.

 


>또한, 우선순위큐를 사용하면 거쳐가는 노드를 정할 때, 최단 경로를 우선 기준으로하여 정하기 때문에 시간도 줄일 수 있다.

제발 다익스트라를 풀 때는 방문처리와 우선순위큐를 사용할지 말지 신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.신중하게 생각하자.
