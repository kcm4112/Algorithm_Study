## 🧩 풀이
1. 대표적으로 인접리스트를 만들어야하는 문제같다.

2. 주어진 네트워크 상태를 따라서 그래도 인접리스트를 구현한다.

3. BFS알고리즘으로 인접리스트를 1번부터 탐색한다.

4. 원소를 하나씩 큐에 집어넣을 때마다 count를 1씩 증가시킨다.

5. BFS가 종료되었을 때의 count값을 출력한다.

---

## 🧩 전체코드

```java
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
```

---
## 🧩 Review
대표적인 인접리스트를 구현하여 BFS탐색하는 문제였다.

 

문제에서 원하는 대로만 구현하면 답이 나오는 문제.
