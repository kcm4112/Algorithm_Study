## 🧩 풀이
1. 4번 노드는 최종 도착만 가능하다. 거쳐가는 것은 불가능하다.

2. 거쳐갈 수 없는 노드들을 큐에 넣어주지 않는 처리만 해주면 매우 간단한 문제다.

3. 다만, 분기점의 개수가 최대 30만개이고, 이동 시간이 최대 10만인것을 고려하면 int타입의 dist배열로는 답을 찾을 수 없을 수도 있다.

4. 간선의 방향은 "양방향"이므로 인접리스트를 구현할 때, start와 finish노드 양쪽에 객체를 생성해야 함을 잊지 말자!

5. 코드의 주석을 같이 보며 이해를 하면 간단하다. 문제가 그리 어렵지 않으니 코드를 참고합시다!!
---

## 🧩 전체 코드
```java
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
```
---

### 🧩 Review
"🧩풀이" 에 적어놓은 주의사항만 유의하여 문제를 풀면 어렵지 않은 문제였다.

항상 문제를 잘 읽고 type에 의해 틀릴 수 있는 경우를 잘 체크하자!
 
