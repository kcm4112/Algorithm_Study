## 🧩 풀이
1. finaldist[i] = i로 초기화시킨다.

2. 0부터 다익스트라 알고리즘을 실행한다.

3. 만약 finaldist[start지점] 에서 지름길이 있을 때, 그냥 그 도착지점까지의 거리를 더한 것과, 현재지점에서 지름길로 가는 길이를 비교한 후, 만약 지름길로 가는 것이 더 짧다면, finaldist[도착지점]에 현재 finaldist[start]값에다가 지름길의 길이를 더하여 저장한다.

---

## 🧩 전체 코드
```java
import java.util.*;
import java.io.*;
class Path {
    int end = 0;
    int dist = 0;
    Path(int v1, int v2){
        end = v1;
        dist = v2;
    }
}
public class P_1446 {
    static int N,D;
    static ArrayList<Path> [] list;
    static int [] finaldist;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        list = new ArrayList[10001];
        finaldist = new int[10001];
        for(int i=0;i<10001;i++){
            finaldist[i] = i;
            list[i] = new ArrayList<>();
        }

        //리스트의 인덱스를 출발지점으로 하고, 그 값으로 도착지점과 이동거리 저장하기!
        for(int i=0;i<N;i++){
            StringTokenizer s = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(s.nextToken());
            int finish = Integer.parseInt(s.nextToken());
            int value = Integer.parseInt(s.nextToken());
            list[start].add(new Path(finish, value));
        }
        dijkstra(0); //출발지점 0부터 시작!
        System.out.println(finaldist[D]);
    }

    static void dijkstra(int s){
        if(s > D){
            return;
        }

        if(finaldist[s+1] > finaldist[s] + 1){
            finaldist[s+1] = finaldist[s] + 1;
        }
        for(int i=0;i<list[s].size();i++){
            //현재 위치에서 어떤 도착 지점까지 지름길로 가는 길이가, 지금까지 구한 그 도착지점까지의 최소거리보다 더 작다면?
            if(finaldist[s] + list[s].get(i).dist < finaldist[list[s].get(i).end]){
                finaldist[list[s].get(i).end] = finaldist[s] + list[s].get(i).dist;
            }
        }
        dijkstra(s+1);
    }
}
```
---

## 🧩 Review
여러 가지 풀이 방법이 존재할 것 같다.

다른 방법으로도 문제를 한 번 풀어봐야겠다.
