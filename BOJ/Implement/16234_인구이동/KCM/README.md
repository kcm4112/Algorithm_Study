## 🧩 풀이
1. 주어진 map을 인접 배열의 원소가 "L이상 R이하일 때"를 조건으로  bfs알고리즘을 통해 탐색한다.

2. bfs가 종료되면 큐에 넣었던 원소의 개수와 그 원소들의 합을 값으로 하여 평균값을 구해준 후, 큐에 담겼던 index의 값들을 업데이트 해준다.

3. 여기서 while구문 탈출 조건이 아주 중요한데, 만약 bfs탐색을 하며 인구이동이 일어나서 배열의 값을 update한 적이 없다면 isUnion값을 false로 바꿔준다. (이 말은 하루동안, 그 어느 지역에서도 인구이동에 일어나지 않았다는 뜻.)

4. 나는 flag라는 변수를 연합에 속한 나라의 개수로 지정하고, bfs탐색이 끝난 후, flag가 1보다 크다면 연합이 형성되었다는 뜻이므로 값의 update를 진행하였다.

> 같은 날에 update된 값이 같은 날의 map의 원소에 영향을 주어서는 안된다.

---
## 🧩 전체 코드
```java
import java.util.*;
import java.io.*;

class People{
    int row = 0;
    int col = 0;
    People(int v1, int v2){
        row = v1;
        col = v2;
    }
}
class Index{
    int v1=0;
    int v2=0;
    Index(int s1, int s2){
        v1 = s1;
        v2 = s2;
    }
}
public class P_16234 {
    static int N, L, R, day=0;
    static int flag = 1;
    static int [][] map;
    static boolean [][] visited;
    static int [][] mv = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int total = 0;
    static List<Index> list;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];
        //맵 구성하기.
        for(int i=0;i<N;i++){
            StringTokenizer s = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(s.nextToken());
            }
        }

        while(true){
            boolean isUnion = false;
            visited = new boolean[N][N];
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    flag=1;
                    total = 0;
                    if(!visited[i][j]){ //원래 bfs시에는 인구이동이 안됐는데, 값이 update되어 for문을 돌다가 값자기 인구이동이 되는 경우 방지.
                        bfs(i,j);
                        if(flag>1){ //연합이 되었다면 숫자 바꿔주기.
                            int temp = total / flag;
                            for(int k=0;k<list.size();k++){
                                int rr = list.get(k).v1;
                                int cc = list.get(k).v2;
                                map[rr][cc] = temp;
                            }
                            isUnion = true; //오늘 하루 안에 연합이 만들어 졌다는 뜻.
                        }
                    }
                }
            }
            if(!isUnion){
                System.out.println(day);
                break;
            }
            day++;
        }
    }
    static void bfs(int r, int c){
        Queue<People> q = new LinkedList<>();
        list = new ArrayList<>();
        q.add(new People(r, c));
        visited[r][c] = true;
        list.add(new Index(r,c));
        total = total + map[r][c];

        while(!q.isEmpty()){
            People v = q.remove();
            for(int i=0;i<4;i++){
                int nr = v.row + mv[i][0];
                int nc = v.col + mv[i][1];
                if(nr>=0 && nc >=0 && nr<N && nc<N && !visited[nr][nc]){
                    int diff = Math.abs(map[v.row][v.col] - map[nr][nc]);
                    if(diff <= R && diff >= L){
                        visited[nr][nc] = true;
                        q.add(new People(nr, nc));
                        flag++;
                        total = total + map[nr][nc];
                        list.add(new Index(nr,nc));
                    }
                }
            }
        }
    }
}
```
---

## 🧩  Review
## 🚨주의할 점

- 모든 원소에 대하여 bfs를 탐색할 때, visited가 false인 지점에 대해서만 탐색을 해주어야 한다. 

예를 들어, 처음에는 국경이 열리지 않는 지역이었으나, 앞에서 탐색을하며 값이 update되어서 for문을 돌다가 현재 위치까지 왔는데 이제는 국경이 열리는 경우가 있을 수도 있다. 따라서, bfs를 한 세트 돌 때 한 번이라도 방문한 지역에 대해서는 반드시 visited를 true로 바꿔주어야 한다. (처음 설정된 map을 기준으로 하루동안 인구이동을 할 수 있는 지역을 찾아야하므로, 같은 날update된 값이 같은 날의 map에 영향을 주어서는 안됨!)

- 그 다음, 한 세트의 bfs탐색이 끝나면 다시 모든 인덱스를 false로 초기화시키고 다시 진행해야 한다. (하루가 지났으니 다시 처음부터!)

- 처음에 나는, flag 즉, 연합이 있는 경우, map에서 visited가 true로 바뀐지역에 대해서 다 값을 update해주었지만, 이는 계속적인 오류의 원인이되었다. 그 이유는 국경을 열 수 없는 지역이라도, bfs함수에 들어가는 순간 visited를 true로 바꾸어주고 while문을 돌기 때문에 인구이동이 일어날 수 없는 지역도 내가 update를 해주었기 때문이다.

 

 

조건이 많은 문제는 너무 힘들어!!!조건이 많은 문제는 너무 힘들어!!!조건이 많은 문제는 너무 힘들어!!!조건이 많은 문제는 너무 힘들어!!!조건이 많은 문제는 너무 힘들어!!!조건이 많은 문제는 너무 힘들어!!!조건이 많은 문제는 너무 힘들어!!!조건이 많은 문제는 너무 힘들어!!!조건이 많은 문제는 너무 힘들어!!!조건이 많은 문제는 너무 힘들어!!!조건이 많은 문제는 너무 힘들어!!!조건이 많은 문제는 너무 힘들어!!!조건이 많은 문제는 너무 힘들어!!!조건이 많은 문제는 너무 힘들어!!!조건이 많은 문제는 너무 힘들어!!!조건이 많은 문제는 너무 힘들어!!!
